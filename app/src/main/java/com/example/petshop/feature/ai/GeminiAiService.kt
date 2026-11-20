package com.example.petshop.feature.ai

import android.util.Log
import com.example.petshop.BuildConfig
import com.example.petshop.data.model.ChatMessage
import com.example.petshop.data.model.Product
import com.example.petshop.data.model.RecommendedProduct
import com.example.petshop.data.model.sampleProducts
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object GeminiAiService {

    private const val TAG = "GeminiAiService"
    private const val GEMINI_ENDPOINT =
        "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent"

    private var cachedProducts: List<Product> = emptyList()

    /**
     * Tải danh sách sản phẩm cửa hàng từ Firestore
     */
    suspend fun fetchStoreProducts(): List<Product> = withContext(Dispatchers.IO) {
        if (cachedProducts.isNotEmpty()) {
            return@withContext cachedProducts
        }

        try {
            val db = FirebaseFirestore.getInstance()
            val snapshot = db.collection("products").get().result
            val list = snapshot?.documents?.mapNotNull { doc ->
                doc.toObject(Product::class.java)?.copy(id = doc.id)
            } ?: emptyList()

            if (list.isNotEmpty()) {
                cachedProducts = list
                return@withContext list
            }
        } catch (e: Exception) {
            Log.e(TAG, "Lỗi đọc danh sách sản phẩm từ Firestore", e)
        }

        cachedProducts = sampleProducts
        return@withContext sampleProducts
    }

    /**
     * Gửi tin nhắn đến Gemini API hoặc chuyển sang Local Fallback
     */
    suspend fun sendMessage(userQuery: String): ChatMessage = withContext(Dispatchers.IO) {
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val products = fetchStoreProducts()
        val apiKey = BuildConfig.GEMINI_API_KEY.trim()

        if (apiKey.isBlank()) {
            Log.w(TAG, "GEMINI_API_KEY rỗng, chuyển sang Local Fallback")
            return@withContext generateLocalFallbackResponse(userQuery, products, currentTime)
        }

        try {
            val systemContext = buildSystemContext(products)
            val fullPrompt = "$systemContext\n\nNgười dùng hỏi: $userQuery"

            val jsonPayload = JSONObject().apply {
                val contents = JSONArray().apply {
                    val contentObj = JSONObject().apply {
                        put("role", "user")
                        val parts = JSONArray().apply {
                            put(JSONObject().apply { put("text", fullPrompt) })
                        }
                        put("parts", parts)
                    }
                    put(contentObj)
                }
                put("contents", contents)
            }

            val url = URL(GEMINI_ENDPOINT)
            val conn = (url.openConnection() as HttpURLConnection).apply {
                requestMethod = "POST"
                setRequestProperty("Content-Type", "application/json; charset=utf-8")
                setRequestProperty("x-goog-api-key", apiKey)
                connectTimeout = 10000
                readTimeout = 12000
                doOutput = true
            }

            OutputStreamWriter(conn.outputStream, "UTF-8").use { os ->
                os.write(jsonPayload.toString())
                os.flush()
            }

            val responseCode = conn.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val responseString = BufferedReader(InputStreamReader(conn.inputStream, "UTF-8")).use {
                    it.readText()
                }

                val replyText = parseGeminiResponse(responseString)
                if (replyText.isNotBlank()) {
                    val recommendations = matchRecommendedProducts(userQuery + " " + replyText, products)
                    return@withContext ChatMessage(
                        id = "msg_${System.currentTimeMillis()}",
                        sender = "bot",
                        text = replyText,
                        timestamp = currentTime,
                        recommendedProducts = recommendations.ifEmpty { null },
                        footerNote = "< Gợi ý tối ưu tự động từ Gemini AI & Avocado PetShop"
                    )
                }
            } else {
                Log.e(TAG, "Gemini API trả về mã lỗi HTTP: $responseCode")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Lỗi gọi Gemini API, tự động dùng Local Fallback", e)
        }

        return@withContext generateLocalFallbackResponse(userQuery, products, currentTime)
    }

    private fun buildSystemContext(products: List<Product>): String {
        val sb = StringBuilder()
        sb.append("Bạn là Avocado PetBot - Trợ lý AI tư vấn dinh dưỡng và đồ dùng thú cưng thân thiện của Avocado PetShop.\n")
        sb.append("Dưới đây là danh sách các sản phẩm đang có sẵn tại cửa hàng:\n")

        products.take(10).forEachIndexed { index, p ->
            sb.append("${index + 1}. [${p.title}] - Thương hiệu: ${p.brand} | Giá: ${p.currentPrice}")
            p.oldPrice?.let { sb.append(" (Giá cũ: $it)") }
            p.discountPercent?.let { sb.append(" (Giảm $it)") }
            sb.append(" | Loại thú cưng: ${if (p.petType == "dog") "Chó" else "Mèo"}")
            p.badge?.let { sb.append(" | Đặc điểm: $it") }
            sb.append("\n")
        }

        sb.append("\nYêu cầu:\n")
        sb.append("- Trả lời thân thiện bằng tiếng Việt xưng 'mình' và gọi 'bạn' hoặc 'Sen'.\n")
        sb.append("- Trả lời tư vấn ngắn gọn, hữu ích (khoảng 2-4 câu).\n")
        sb.append("- Ưu tiên tiến cử đúng các sản phẩm có trong danh sách cửa hàng ở trên nếu phù hợp với nhu cầu của người dùng.\n")

        return sb.toString()
    }

    private fun parseGeminiResponse(jsonString: String): String {
        try {
            val jsonObject = JSONObject(jsonString)
            val candidates = jsonObject.optJSONArray("candidates")
            if (candidates != null && candidates.length() > 0) {
                val candidate = candidates.getJSONObject(0)
                val content = candidate.optJSONObject("content")
                val parts = content?.optJSONArray("parts")
                if (parts != null && parts.length() > 0) {
                    return parts.getJSONObject(0).optString("text", "")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Lỗi parse JSON phản hồi từ Gemini", e)
        }
        return ""
    }

    private fun generateLocalFallbackResponse(
        query: String,
        products: List<Product>,
        timestamp: String
    ): ChatMessage {
        val lowerQuery = query.lowercase(Locale.getDefault())

        val (responseText, matchedProds) = when {
            lowerQuery.contains("hạt") || lowerQuery.contains("lông") || lowerQuery.contains("đạm") || lowerQuery.contains("mượt") -> {
                "Dạ ở giai đoạn này bé đang cần nguồn hạt giàu Omega-3, Omega-6 và tỷ lệ đạm dễ tiêu hóa (>36%) giúp phát triển khung xương và hỗ trợ mượt lông. Dưới đây là các dòng hạt phù hợp nhất kèm ưu đãi của cửa hàng:" to
                        products.filter { it.title.contains("Hạt", ignoreCase = true) || it.brand.contains("ROYAL", ignoreCase = true) || it.brand.contains("ORIEN", ignoreCase = true) }
            }
            lowerQuery.contains("cát") || lowerQuery.contains("bụi") || lowerQuery.contains("vệ sinh") -> {
                "Cửa hàng hiện có các dòng cát đậu nành Tofu hữu cơ vón nhanh, cực kỳ ít bụi, thơm dịu và an toàn xả trực tiếp vào bồn cầu cho bé cưng:" to
                        products.filter { it.title.contains("Cát", ignoreCase = true) || it.brand.contains("CATURE", ignoreCase = true) }
            }
            lowerQuery.contains("súp") || lowerQuery.contains("churu") || lowerQuery.contains("pate") || lowerQuery.contains("thưởng") -> {
                "Các dòng súp thưởng và pate thơm ngon giúp kích thích vị giác cực tốt cho các bé kén ăn. Bạn tham khảo ngay nhé:" to
                        products.filter { it.title.contains("Súp", ignoreCase = true) || it.title.contains("Pate", ignoreCase = true) || it.brand.contains("CIAO", ignoreCase = true) }
            }
            lowerQuery.contains("so sánh") -> {
                "Dưới đây là thông tin so sánh chi tiết các dòng hạt dinh dưỡng bán chạy nhất tại Avocado PetShop về tỷ lệ đạm và mức giá ưu đãi:" to
                        products.take(2)
            }
            else -> {
                "Dạ Avocado PetBot xin chào bạn! Cửa hàng đang có rất nhiều dòng thức ăn, đồ chơi và phụ kiện cao cấp cho bé cưng. Dưới đây là các sản phẩm được yêu thích nhất:" to
                        products.take(2)
            }
        }

        val recommended = (if (matchedProds.isNotEmpty()) matchedProds else products.take(2)).take(2).map { p ->
            RecommendedProduct(
                id = p.id,
                badge = p.discountPercent ?: p.badge,
                brandTag = if (p.brand.isNotEmpty()) "🛡 ${p.brand}" else "🏠 Chính hãng",
                title = p.title,
                subtitle = "Sản phẩm chất lượng cao dành cho ${if (p.petType == "dog") "chó" else "mèo"}",
                price = p.currentPrice,
                oldPrice = p.oldPrice,
                discount = p.discountPercent
            )
        }

        return ChatMessage(
            id = "msg_${System.currentTimeMillis()}",
            sender = "bot",
            text = responseText,
            timestamp = timestamp,
            recommendedProducts = recommended,
            footerNote = "< Được tối ưu theo nhu cầu của bé cưng"
        )
    }

    private fun matchRecommendedProducts(
        textContext: String,
        products: List<Product>
    ): List<RecommendedProduct> {
        val lowerText = textContext.lowercase(Locale.getDefault())

        val matched = products.filter { p ->
            lowerText.contains(p.title.lowercase(Locale.getDefault())) ||
                    lowerText.contains(p.brand.lowercase(Locale.getDefault())) ||
                    (p.title.contains("Hạt", true) && lowerText.contains("hạt")) ||
                    (p.title.contains("Cát", true) && lowerText.contains("cát")) ||
                    (p.title.contains("Súp", true) && lowerText.contains("súp"))
        }.take(2)

        val finalList = if (matched.isNotEmpty()) matched else products.take(2)

        return finalList.map { p ->
            RecommendedProduct(
                id = p.id,
                badge = p.discountPercent ?: p.badge,
                brandTag = if (p.brand.isNotEmpty()) "🛡 ${p.brand}" else "🏠 Chính hãng",
                title = p.title,
                subtitle = "Sản phẩm tốt dành cho ${if (p.petType == "dog") "chó" else "mèo"}",
                price = p.currentPrice,
                oldPrice = p.oldPrice,
                discount = p.discountPercent
            )
        }
    }
}

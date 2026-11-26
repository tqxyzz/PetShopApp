package com.example.petshop.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object UserAccountUtils {

    /**
     * Lấy ma_tai_khoan của người dùng hiện tại từ Firestore collection "accounts" theo Email.
     * Nếu người dùng chưa đăng nhập hoặc không tìm thấy, tự động fallback an toàn về "acc_002".
     */
    fun fetchCurrentAccountId(onResult: (String) -> Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userEmail = currentUser?.email?.trim()

        if (!userEmail.isNullOrBlank()) {
            try {
                val db = FirebaseFirestore.getInstance()
                db.collection("accounts")
                    .whereEqualTo("email", userEmail)
                    .get()
                    .addOnSuccessListener { snapshot ->
                        if (snapshot != null && !snapshot.isEmpty) {
                            val accId = snapshot.documents.first().getString("ma_tai_khoan")
                            if (!accId.isNullOrBlank()) {
                                onResult(accId)
                                return@addOnSuccessListener
                            }
                        }
                        onResult("acc_002")
                    }
                    .addOnFailureListener {
                        onResult("acc_002")
                    }
            } catch (e: Exception) {
                onResult("acc_002")
            }
        } else {
            onResult("acc_002")
        }
    }
}

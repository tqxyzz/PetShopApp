package com.example.petshop.data.model

data class BrandItem(
    val id: String,
    val logoText: String,
    val name: String,
    val country: String
)

val sampleBrands = listOf(
    BrandItem("b1", "RC", "Royal Canin", "Pháp"),
    BrandItem("b2", "CIAO", "Inaba Ciao", "Nhật Bản"),
    BrandItem("b3", "WHIS", "Whiskas", "Mỹ"),
    BrandItem("b4", "PEDI", "Pedigree", "Mỹ")
)

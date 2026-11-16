package com.example.petshop.data.model

data class VoucherItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val hsd: String,
    val isSaved: Boolean = false
)

val sampleVouchers = listOf(
    VoucherItem("v1", "GIẢM 50.000đ", "Đơn từ 299.000đ", "HSD: 30/11", false),
    VoucherItem("v2", "FREESHIP 0đ", "Đơn từ 149.000đ • Freeship Extra", "HSD: 30/11", true)
)

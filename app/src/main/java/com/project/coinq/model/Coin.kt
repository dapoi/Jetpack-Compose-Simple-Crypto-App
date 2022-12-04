package com.project.coinq.model

data class Coin(
    val id: Int,
    val name: String,
    val desc: String,
    val tags: List<String>
)

package com.example.dibuild.model

data class CalculatedResults(
    val results: List<CalculatedResult>
)

data class CalculatedResult(
    val name: String,
    val value: String,
    val unit: String,
)
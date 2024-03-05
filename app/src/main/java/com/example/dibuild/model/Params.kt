package com.example.dibuild.model


data class ParamsBlock(
    val name: String,
    val params: List<Param>,
)

data class Param(
    val name: String,
    val value: String,
    val unit: String,
)
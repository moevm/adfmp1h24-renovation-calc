package com.example.dibuild.model


data class ParamsBlock(
    val name: String,
    val params: List<Param>,
)

data class Param(
    // Название параметра
    val name: String,
    // Значение параметра
    val value: String,
    // Единицы измерения параметра
    val unit: String,
    // Функция для отображения ввода
    val onValueChanged:(String) -> Unit = {},
)

data class Help(
    val info: String
)
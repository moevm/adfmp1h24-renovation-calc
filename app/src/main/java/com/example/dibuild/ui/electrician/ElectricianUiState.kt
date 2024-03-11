package com.example.dibuild.ui.electrician

data class ElectricianUiState(
    var socket_num: String = "0",
    var switch_num: String = "0",
    var wire_length: String = "0.0",
    var wire_price: String = "0.0",
    var duct_length: String = "0.0",
    var duct_price: String = "0.0",
    var socket_price: String = "0.0",
    var switch_price: String = "0.0",

    var wire_total: Double = 0.0,
    var duct_total: Double = 0.0,
    var socket_total: Double = 0.0,
    var switch_total: Double = 0.0,

    var total: Double = 0.0,
)

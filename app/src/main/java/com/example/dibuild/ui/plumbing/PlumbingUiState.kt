package com.example.dibuild.ui.plumbing

data class PlumbingUiState (
    var pipe_length: String = "0.0",
    var pipe_price: String = "0.0",
    var valve_num: String = "0",
    var valve_price: String = "0.0",
    var meter_price: String = "0.0",
    var filter_price: String = "0.0",

    var pipe_total: Double = 0.0,
    var valve_total: Double = 0.0,

    var total: Double = 0.0,
)
package com.example.dibuild.ui.laminate

data class LaminateUiState (
    var room_length: String = "0.0",
    var room_width: String = "0.0",
    var board_length: String = "0.0",
    var board_width: String = "0.0",
    var board_num: String = "0",
    var board_price: String = "0.0",

    var room_square: Double = 0.0,
    var laminate_square: Double = 0.0,
    var laminate_num: Int = 0,
    var laminate_accurate_num: Double = 0.0,
    var excess: Double = 0.0,

    var total: Double = 0.0,
)
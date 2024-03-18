package com.example.dibuild.ui.wallpapers

data class WallpapersUiState(
    var room_length: String = "0.0",
    var room_width: String = "0.0",
    var room_height: String = "0.0",

    var roll_length: String = "0.0",
    var roll_width: String = "0.0",
    var roll_price: String = "0.0",

    var gluepackage_weight : String = "0",
    var gluepackage_price : String = "0.0",
    var glue_consumption : String = "0",

    var walls_square: Double = 0.0,

    var rolls_accurate_num: Double = 0.0,
    var glupackages_accurate_num: Double = 0.0,

    var rolls_num: Int = 0,
    var rolls_excess: Double = 0.0,
    var rolls_total: Double = 0.0,

    var gluepackages_num: Int = 0,
    var glue_excess: Double = 0.0,
    var glue_total: Double = 0.0,

    var total: Double = 0.0,
)

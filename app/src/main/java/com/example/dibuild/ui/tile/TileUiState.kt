package com.example.dibuild.ui.tile

data class TileUiState(
    var room_length: String = "0.0",
    var room_width: String = "0.0",

    var tile_length: String = "0.0",
    var tile_width: String = "0.0",
    var tile_package_num: String = "0",
    var tile_price: String = "0.0",

    var gluepackage_weight : String = "0",
    var gluepackage_price : String = "0.0",
    var glue_consumption : String = "0",

    var room_square: Double = 0.0,
    var package_square: Double = 0.0,
    var packages_accurate_num: Double = 0.0,
    var glupackages_accurate_num: Double = 0.0,

    var packages_num: Int = 0,
    var tile_excess: Double = 0.0,
    var tile_total: Double = 0.0,

    var gluepackages_num: Int = 0,
    var glue_excess: Double = 0.0,
    var glue_total: Double = 0.0,

    var total: Double = 0.0,
)

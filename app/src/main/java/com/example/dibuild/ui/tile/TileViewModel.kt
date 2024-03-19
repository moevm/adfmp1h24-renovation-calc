package com.example.dibuild.ui.tile

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.ceil

class TileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(TileUiState())
    val uiState: StateFlow<TileUiState> = _uiState.asStateFlow()

    fun countTotal() {
        //Площадь комнаты
        val room_total_square =
            _uiState.value.room_length.toDouble() * _uiState.value.room_width.toDouble()
        _uiState.value = uiState.value.copy(room_square = room_total_square)

        //Площадь одной упаковки
        val package_total_square =
            _uiState.value.tile_length.toDouble() * _uiState.value.tile_width.toDouble() * _uiState.value.tile_package_num.toDouble()
        _uiState.value = uiState.value.copy(package_square = package_total_square)

        //Количество упаковок неокругленное
        val packages_total_accurate_num =
            _uiState.value.room_square / _uiState.value.package_square
        _uiState.value = uiState.value.copy(packages_accurate_num = packages_total_accurate_num)

        //Количество упаковок
        val packages_total_num =
            ceil(packages_total_accurate_num).toInt()
        _uiState.value = uiState.value.copy(packages_num = packages_total_num)

        // Стоимость плитки
        val tile_total_price =
            _uiState.value.packages_num * _uiState.value.package_square * _uiState.value.tile_price.toDouble()
        _uiState.value = uiState.value.copy(tile_total = tile_total_price)

        // Излишек
        val total_excess =
            _uiState.value.packages_num - _uiState.value.packages_accurate_num
        _uiState.value = uiState.value.copy(tile_excess = total_excess)


        // Количество упаковок клея неокругленное
        val glupackages_total_accurate_num =
            _uiState.value.room_square * _uiState.value.glue_consumption.toDouble() / _uiState.value.gluepackage_weight.toDouble()
        _uiState.value = uiState.value.copy(glupackages_accurate_num = glupackages_total_accurate_num)


        // Количество упаковок клея
        val glupackages_total_num =
            ceil(glupackages_total_accurate_num).toInt()
        _uiState.value = uiState.value.copy(gluepackages_num = glupackages_total_num)

        // Стоимость клея
        val glu_total_price =
            _uiState.value.gluepackages_num * _uiState.value.gluepackage_price.toDouble()
        _uiState.value = uiState.value.copy(glue_total = glu_total_price)

        // Излишек клея
        val glu_total_excess =
            _uiState.value.gluepackages_num - _uiState.value.glupackages_accurate_num
        _uiState.value = uiState.value.copy(glue_excess = glu_total_excess)

        // Итоговая стоимость
        val total_price =
            _uiState.value.tile_total + _uiState.value.glue_total
        _uiState.value = uiState.value.copy(total = total_price)
    }

    fun clearValues() {
        _uiState.value = uiState.value.copy(
            room_length = "0.0",
            room_width = "0.0",
            tile_length = "0.0",
            tile_width = "0.0",
            tile_package_num = "0",
            tile_price = "0.0",
            gluepackage_weight = "0",
            gluepackage_price = "0.0",
            glue_consumption = "0",
        )
    }

    fun updateRoomLength(newLength: String) {
        _uiState.value = uiState.value.copy(room_length = newLength)
    }

    fun updateRoomWidth(newWidth: String) {
        _uiState.value = uiState.value.copy(room_width = newWidth)
    }

    fun updateTileLength(newLength: String) {
        _uiState.value = uiState.value.copy(tile_length = newLength)
    }

    fun updateTileWidth(newWidth: String) {
        _uiState.value = uiState.value.copy(tile_width = newWidth)
    }

    fun updateTilePackageNum(newNum: String) {
        _uiState.value = uiState.value.copy(tile_package_num = newNum)
    }

    fun updateTilePrice(newPrice: String) {
        _uiState.value = uiState.value.copy(tile_price = newPrice)
    }

    fun updateGluepackageWeight(newWeight: String) {
        _uiState.value = uiState.value.copy(gluepackage_weight = newWeight)
    }

    fun updateGluepackagePrice(newPrice: String) {
        _uiState.value = uiState.value.copy(gluepackage_price = newPrice)
    }

    fun updateGlueConsumption(newConsumption: String) {
        _uiState.value = uiState.value.copy(glue_consumption = newConsumption)
    }


    fun validate(): Pair<Boolean, List<String>> {
        val validateResults = mapOf(
            "Длина комнаты" to (_uiState.value.room_length.toDoubleOrNull() != null && _uiState.value.room_length.toDouble() > 0.0),
            "Ширина комнаты" to (_uiState.value.room_width.toDoubleOrNull() != null && _uiState.value.room_width.toDouble() > 0.0),
            "Длина одной плитки" to (_uiState.value.tile_length.toDoubleOrNull() != null && _uiState.value.tile_length.toDouble() > 0.0),
            "Ширина одной плитки" to (_uiState.value.tile_width.toDoubleOrNull() != null && _uiState.value.tile_width.toDouble() > 0.0),
            "Количество плиток в упаковке" to (_uiState.value.tile_package_num.toIntOrNull() != null && _uiState.value.tile_package_num.toInt() > 0),
            "Цена за упаковку" to (_uiState.value.tile_price.toDoubleOrNull() != null && _uiState.value.tile_price.toDouble() > 0.0),
            "Масса упаковки клея" to (_uiState.value.gluepackage_weight.toDoubleOrNull() != null && _uiState.value.gluepackage_weight.toDouble() > 0.0),
            "Цена упаковки клея" to (_uiState.value.gluepackage_price.toDoubleOrNull() != null && _uiState.value.gluepackage_price.toDouble() > 0.0),
            "Расход клея" to (_uiState.value.glue_consumption.toDoubleOrNull() != null && _uiState.value.glue_consumption.toDouble() > 0.0),
        )


        if (validateResults.containsValue(false)) {
            return Pair(false, validateResults.filter { (_, value) -> !value }.keys.toList())
        } else {
            return Pair(true, emptyList())
        }
    }

    fun getTileCalcHistory(): List<String> {
        return listOf(
            "Плитка",
            "Длина комнаты = %s м".format(_uiState.value.room_length),
            "Ширина комнаты = %s м".format(_uiState.value.room_width),
            "Длина одной плитки = %s м".format(_uiState.value.tile_length),
            "Ширина одной плитки = %s м".format(_uiState.value.tile_width),
            "Количество плиток в упаковке = %s шт".format(_uiState.value.tile_package_num),
            "Цена = %s ₽/м^2".format(_uiState.value.tile_price),
            "Масса упаковки клея = %s г".format(_uiState.value.gluepackage_weight),
            "Цена упаковки клея = %s ₽".format(_uiState.value.gluepackage_price),
            "Расход клея = %s г/м^2".format(_uiState.value.glue_consumption),

            "Площадь комнаты = %s м * %s м = %.2f м^2".format(
                _uiState.value.room_length,
                _uiState.value.room_width,
                _uiState.value.room_square
            ),
            "Площадь одной упаковки = %s м * %s м * %s шт = %.2f м^2".format(
                _uiState.value.tile_length,
                _uiState.value.tile_width,
                _uiState.value.tile_package_num,
                _uiState.value.package_square
            ),
            "Количество упаковок (округлено) = %.2f м^2 / %.2f м^2 = %s шт".format(
                _uiState.value.room_square,
                _uiState.value.package_square,
                _uiState.value.packages_num
            ),
            "Стоимость плитки = %s шт * %.2f м^2 * %.2f ₽ = %.2f ₽".format(
                _uiState.value.packages_num,
                _uiState.value.package_square,
                _uiState.value.tile_price.toDouble(),
                _uiState.value.tile_total
            ),
            "Излишек плитки = %s шт - %.2f шт = %.2f шт".format(
                _uiState.value.packages_num,
                _uiState.value.packages_accurate_num,
                _uiState.value.tile_excess
            ),
            "Количество упаковок клея (округлено) = %.2f м^2 * %s г/м^2 / %s г = %s шт".format(
                _uiState.value.room_square,
                _uiState.value.glue_consumption,
                _uiState.value.gluepackage_weight,
                _uiState.value.gluepackages_num
            ),
            "Стоимость клея = %s шт * %.2f ₽ = %.2f ₽".format(
                _uiState.value.gluepackages_num,
                _uiState.value.gluepackage_price.toDouble(),
                _uiState.value.glue_total,
            ),
            ("Излишек клея = %s шт - %.2f шт = %.2f шт").format(
                _uiState.value.gluepackages_num,
                _uiState.value.glupackages_accurate_num,
                _uiState.value.glue_excess
            ),
            "Итоговая стоимость = %.2f ₽ + %.2f ₽ = %.2f ₽".format(
                _uiState.value.tile_total,
                _uiState.value.glue_total,
                _uiState.value.total
            )
        )
    }

}
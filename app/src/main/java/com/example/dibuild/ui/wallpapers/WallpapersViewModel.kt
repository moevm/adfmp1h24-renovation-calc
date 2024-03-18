package com.example.dibuild.ui.wallpapers

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.ceil

class WallpapersViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WallpapersUiState())
    val uiState: StateFlow<WallpapersUiState> = _uiState.asStateFlow()

    fun countTotal() {
        //Площадь стен
        val walls_total_square =
            2 * (_uiState.value.room_length.toDouble() + _uiState.value.room_width.toDouble()) * _uiState.value.room_height.toDouble()
        _uiState.value = uiState.value.copy(walls_square = walls_total_square)

        //Количество рулонов неокругленное
        val rolls_total_accurate_num =
            _uiState.value.walls_square / (_uiState.value.roll_length.toDouble() * _uiState.value.roll_width.toDouble())
        _uiState.value = uiState.value.copy(rolls_accurate_num = rolls_total_accurate_num)

        //Количество упаковок
        val rolls_total_num =
            ceil(rolls_total_accurate_num).toInt()
        _uiState.value = uiState.value.copy(rolls_num = rolls_total_num)

        // Стоимость рулонов
        val rolls_total_price =
            _uiState.value.rolls_num * _uiState.value.roll_price.toDouble()
        _uiState.value = uiState.value.copy(rolls_total = rolls_total_price)

        // Излишек рулонов
        val rolls_total_excess =
            _uiState.value.rolls_num - _uiState.value.rolls_accurate_num
        _uiState.value = uiState.value.copy(rolls_excess = rolls_total_excess)


        // Количество упаковок клея неокругленное
        val glupackages_total_accurate_num =
            _uiState.value.walls_square * _uiState.value.glue_consumption.toDouble() / _uiState.value.gluepackage_weight.toDouble()
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
            _uiState.value.rolls_total + _uiState.value.glue_total
        _uiState.value = uiState.value.copy(total = total_price)
    }

    fun clearValues() {
        _uiState.value = uiState.value.copy(
            room_length = "0.0",
            room_width = "0.0",
            room_height = "0.0",
            roll_length = "0.0",
            roll_width = "0.0",
            roll_price = "0.0",
            gluepackage_weight = "0.0",
            gluepackage_price = "0.0",
            glue_consumption = "0.0",
        )
    }

    fun updateRoomLength(newLength: String) {
        _uiState.value = uiState.value.copy(room_length = newLength)
    }

    fun updateRoomWidth(newWidth: String) {
        _uiState.value = uiState.value.copy(room_width = newWidth)
    }

    fun updateRoomHeight(newHeight: String) {
        _uiState.value = uiState.value.copy(room_height = newHeight)
    }

    fun updateRollLength(newLength: String) {
        _uiState.value = uiState.value.copy(roll_length = newLength)
    }

    fun updateRollWidth(newWidth: String) {
        _uiState.value = uiState.value.copy(roll_width = newWidth)
    }

    fun updateRollPrice(newPrice: String) {
        _uiState.value = uiState.value.copy(roll_price = newPrice)
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
            "Длина комнаты" to (_uiState.value.room_length.toDoubleOrNull() != null && _uiState.value.room_length.toDouble() >= 0.0),
            "Ширина комнаты" to (_uiState.value.room_width.toDoubleOrNull() != null && _uiState.value.room_width.toDouble() >= 0.0),
            "Высота комнаты" to (_uiState.value.room_height.toDoubleOrNull() != null && _uiState.value.room_height.toDouble() >= 0.0),
            "Длина рулона" to (_uiState.value.roll_length.toDoubleOrNull() != null && _uiState.value.roll_length.toDouble() > 0.0),
            "Ширина рулона" to (_uiState.value.roll_width.toDoubleOrNull() != null && _uiState.value.roll_width.toDouble() > 0.0),
            "Цена за рулон" to (_uiState.value.roll_price.toDoubleOrNull() != null && _uiState.value.roll_price.toDouble() >= 0.0),
            "Масса упаковки клея" to (_uiState.value.gluepackage_weight.toDoubleOrNull() != null && _uiState.value.gluepackage_weight.toDouble() >= 0.0),
            "Цена упаковки клея" to (_uiState.value.gluepackage_price.toDoubleOrNull() != null && _uiState.value.gluepackage_price.toDouble() >= 0.0),
            "Расход клея" to (_uiState.value.glue_consumption.toDoubleOrNull() != null && _uiState.value.glue_consumption.toDouble() >= 0.0),
        )


        if (validateResults.containsValue(false)) {
            return Pair(false, validateResults.filter { (_, value) -> !value }.keys.toList())
        } else {
            return Pair(true, emptyList())
        }
    }

    fun getTileCalcHistory(): List<String> {
        return listOf(
            "Обои",
            "Длина комнаты = %s м".format(_uiState.value.room_length),
            "Ширина комнаты = %s м".format(_uiState.value.room_width),
            "Высота комнаты = %s м".format(_uiState.value.room_height),
            "Длина рулона = %s м".format(_uiState.value.roll_length),
            "Ширина рулона = %s м".format(_uiState.value.roll_width),
            "Цена рулона = %s ₽/м^2".format(_uiState.value.roll_price),
            "Масса упаковки клея = %s г".format(_uiState.value.gluepackage_weight),
            "Цена упаковки клея = %s ₽".format(_uiState.value.gluepackage_price),
            "Расход клея = %s г/м^2".format(_uiState.value.glue_consumption),

            "Площадь всех стен комнаты = 2 * (%s м + %s м) * %s = %.2f м^2".format(
                _uiState.value.room_length,
                _uiState.value.room_width,
                _uiState.value.room_height,
                _uiState.value.walls_square
            ),
            "Количество рулонов (округлено) = %.2f м^2 / (%s м * %s м)= %s шт".format(
                _uiState.value.walls_square,
                _uiState.value.roll_length,
                _uiState.value.roll_width,
                _uiState.value.rolls_num
            ),
            "Стоимость рулонов = %s шт * %.2f ₽ = %.2f ₽".format(
                _uiState.value.rolls_num,
                _uiState.value.roll_price.toDouble(),
                _uiState.value.rolls_total
            ),
            "Излишки рулонов = %s шт - %.2f шт = %.2f шт".format(
                _uiState.value.rolls_num,
                _uiState.value.rolls_accurate_num,
                _uiState.value.rolls_excess
            ),
            "Количество упаковок клея (округлено) = %.2f м^2 * %s г/м^2 / %s г = %s шт".format(
                _uiState.value.walls_square,
                _uiState.value.glue_consumption,
                _uiState.value.gluepackage_weight,
                _uiState.value.gluepackages_num
            ),
            "Стоимость клея = %s шт * %.2f ₽ = %.2f ₽".format(
                _uiState.value.gluepackages_num,
                _uiState.value.gluepackage_price.toDouble(),
                _uiState.value.glue_total,
            ),
            ("Излишки клея = %s шт - %.2f шт = %.2f шт").format(
                _uiState.value.gluepackages_num,
                _uiState.value.glupackages_accurate_num,
                _uiState.value.glue_excess
            ),
            "Итоговая стоимость = %.2f ₽ + %.2f ₽ = %.2f ₽".format(
                _uiState.value.rolls_total,
                _uiState.value.glue_total,
                _uiState.value.total
            )
        )
    }

}
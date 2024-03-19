package com.example.dibuild.ui.laminate

import android.health.connect.datatypes.units.Length
import androidx.lifecycle.ViewModel
import com.example.dibuild.ui.plumbing.PlumbingUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.ceil

class LaminateViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LaminateUiState())
    val uiState: StateFlow<LaminateUiState> = _uiState.asStateFlow()

    fun countTotal() {
        //Площадь комнаты
        val room_total_square =
            _uiState.value.room_length.toDouble() * _uiState.value.room_width.toDouble()
        _uiState.value = uiState.value.copy(room_square = room_total_square)

        //Площадь одной упаковки
        val laminate_total_square =
            _uiState.value.board_length.toDouble() * _uiState.value.board_width.toDouble() * _uiState.value.board_num.toDouble()
        _uiState.value = uiState.value.copy(laminate_square = laminate_total_square)

        //Количество упаковок неокругленное
        val laminate_total_accurate_num =
            _uiState.value.room_square / _uiState.value.laminate_square
        _uiState.value = uiState.value.copy(laminate_accurate_num = laminate_total_accurate_num)

        //Количество упаковок
        val laminate_total_num =
            ceil(laminate_total_accurate_num).toInt()
        _uiState.value = uiState.value.copy(laminate_num = laminate_total_num)

        // Итоговая стоимость
        val total_price =
            _uiState.value.laminate_num * _uiState.value.laminate_square * _uiState.value.board_price.toDouble()
        _uiState.value = uiState.value.copy(total = total_price)

        // Излишек
        val total_excess =
            _uiState.value.laminate_num - _uiState.value.laminate_accurate_num
        _uiState.value = uiState.value.copy(excess = total_excess)
    }

    fun clearValues() {
        _uiState.value = uiState.value.copy(
            room_length = "0.0",
            room_width = "0.0",
            board_length = "0.0",
            board_width = "0.0",
            board_num = "0",
            board_price = "0.0",
        )
    }

    fun updateRoomLength(newLength: String) {
        _uiState.value = uiState.value.copy(room_length = newLength)
    }

    fun updateRoomWidth(newWidth: String) {
        _uiState.value = uiState.value.copy(room_width = newWidth)
    }

    fun updateBoardLength(newLength: String) {
        _uiState.value = uiState.value.copy(board_length = newLength)
    }

    fun updateBoardWidth(newWidth: String) {
        _uiState.value = uiState.value.copy(board_width = newWidth)
    }

    fun updateBoardNum(newNum: String) {
        _uiState.value = uiState.value.copy(board_num = newNum)
    }

    fun updateBoardPrice(newPrice: String) {
        _uiState.value = uiState.value.copy(board_price = newPrice)
    }


    fun validate(): Pair<Boolean, List<String>> {
        val validateResults = mapOf(
            "Длина комнаты" to (_uiState.value.room_length.toDoubleOrNull() != null && _uiState.value.room_length.toDouble() > 0.0),
            "Ширина комнаты" to (_uiState.value.room_width.toDoubleOrNull() != null && _uiState.value.room_width.toDouble() > 0.0),
            "Длина доски" to (_uiState.value.board_length.toDoubleOrNull() != null && _uiState.value.board_length.toDouble() > 0.0),
            "Ширина доски" to (_uiState.value.board_width.toDoubleOrNull() != null && _uiState.value.board_width.toDouble() > 0.0),
            "Количество в упаковке" to (_uiState.value.board_num.toIntOrNull() != null && _uiState.value.board_num.toInt() > 0),
            "Цена" to (_uiState.value.board_price.toDoubleOrNull() != null && _uiState.value.board_price.toDouble() > 0.0),
            )


        if (validateResults.containsValue(false)) {
            return Pair(false, validateResults.filter { (_, value) -> !value }.keys.toList())
        } else {
            return Pair(true, emptyList())
        }
    }

    fun getLaminateCalcHistory(): List<String> {
        return listOf(
            "Ламинат",
            "Длина комнаты = %s м".format(_uiState.value.room_length),
            "Ширина комнаты = %s м".format(_uiState.value.room_width),
            "Длина доски = %s м".format(_uiState.value.board_length),
            "Ширина доски = %s м".format(_uiState.value.board_width),
            "Количество в упаковке = %s шт".format(_uiState.value.board_num),
            "Цена = %s ₽/м".format(_uiState.value.board_price),
            "Площадь комнаты = %s м * %s м = %.2f м^2".format(
                _uiState.value.room_length,
                _uiState.value.room_width,
                _uiState.value.room_square
            ),
            "Площадь одной упаковки = %s м * %s м * %s шт = %.2f м^2".format(
                _uiState.value.board_length,
                _uiState.value.board_width,
                _uiState.value.board_num,
                _uiState.value.laminate_square
            ),
            "Количество упаковок (округлено) = %s м^2 / %.2f м^2 = %s шт".format(
                _uiState.value.room_square,
                _uiState.value.laminate_square,
                _uiState.value.laminate_num
            ),
            "Итоговая стоимость = %s шт * %.2f м^2 * %.2f ₽ = %.2f ₽".format(
                _uiState.value.laminate_num,
                _uiState.value.laminate_square,
                _uiState.value.board_price.toDouble(),
                _uiState.value.total
            ),
            "Излишек = %s шт - %.2f шт = %.2f шт".format(
                _uiState.value.laminate_num,
                _uiState.value.laminate_accurate_num,
                _uiState.value.excess
            )
        )
    }

}
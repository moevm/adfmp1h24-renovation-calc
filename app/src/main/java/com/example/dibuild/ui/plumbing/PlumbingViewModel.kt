package com.example.dibuild.ui.plumbing;

import android.health.connect.datatypes.units.Length
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlumbingViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PlumbingUiState())
    val uiState: StateFlow<PlumbingUiState> = _uiState.asStateFlow()

    fun countTotal() {
        //Стоимость труб
        val pipe_total_price =
            _uiState.value.pipe_length.toDouble() * _uiState.value.pipe_price.toDouble()
        _uiState.value = uiState.value.copy(pipe_total = pipe_total_price)

        //Стоимость вентиля
        val valve_total_price =
            _uiState.value.valve_num.toDouble() * _uiState.value.valve_price.toDouble()
        _uiState.value = uiState.value.copy(valve_total = valve_total_price)

        val total_price =
            _uiState.value.pipe_total + _uiState.value.valve_total + _uiState.value.meter_price.toDouble() + _uiState.value.filter_price.toDouble()
        _uiState.value = uiState.value.copy(total = total_price)
    }

    fun clearValues() {
        _uiState.value = uiState.value.copy(
            pipe_length = "0.0",
            pipe_price = "0.0",
            valve_num = "0",
            valve_price = "0.0",
            meter_price = "0.0",
            filter_price = "0.0",
        )
    }

    fun updatePipeLength(newLength: String) {
        _uiState.value = uiState.value.copy(pipe_length = newLength)
    }

    fun updatePipePrice(newPrice: String) {
        _uiState.value = uiState.value.copy(pipe_price = newPrice)
    }

    fun updateValveNum(newNum: String) {
        _uiState.value = uiState.value.copy(valve_num = newNum)
    }

    fun updateValvePrice(newPrice: String) {
        _uiState.value = uiState.value.copy(valve_price = newPrice)
    }

    fun updateMeterPrice(newPrice: String) {
        _uiState.value = uiState.value.copy(meter_price = newPrice)
    }

    fun updateFilterPrice(newPrice: String) {
        _uiState.value = uiState.value.copy(filter_price = newPrice)
    }


    fun validate(): Pair<Boolean, List<String>> {
        val validateResults = mapOf(
            "Длина труб" to (_uiState.value.pipe_length.toDoubleOrNull() != null && _uiState.value.pipe_length.toDouble() >= 0.0),
            "Цена труб" to (_uiState.value.pipe_price.toDoubleOrNull() != null && _uiState.value.pipe_price.toDouble() >= 0.0),
            "Количество вентилей" to (_uiState.value.valve_num.toIntOrNull() != null && _uiState.value.valve_num.toInt() >= 0),
            "Цена вентиля" to (_uiState.value.valve_price.toDoubleOrNull() != null && _uiState.value.valve_price.toDouble() >= 0.0),
            "Цена счетчика" to (_uiState.value.meter_price.toDoubleOrNull() != null && _uiState.value.meter_price.toDouble() >= 0.0),
            "Цена фильтра" to (_uiState.value.filter_price.toDoubleOrNull() != null && _uiState.value.filter_price.toDouble() >= 0.0),
        )


        if (validateResults.containsValue(false)) {
            return Pair(false, validateResults.filter { (_, value) -> !value }.keys.toList())
        } else {
            return Pair(true, emptyList())
        }
    }

    fun getPlumbingCalcHistory(): List<String> {
        return listOf(
            "Сантехника",
            "Длина труб = %s м".format(_uiState.value.pipe_length),
            "Цена труб = %s ₽/м".format(_uiState.value.pipe_price),
            "Количество вентилей = %s шт".format(_uiState.value.valve_num),
            "Цена вентиля = %s ₽/м".format(_uiState.value.valve_price),
            "Цена счетчика = %s ₽/м".format(_uiState.value.meter_price),
            "Цена фильтра = %s ₽/м".format(_uiState.value.filter_price),
            "Стоимость труб = %s м * %s ₽ = %.2f₽".format(
                _uiState.value.pipe_length,
                _uiState.value.pipe_price,
                _uiState.value.pipe_total
            ),
            "Стоимость вентилей = %s шт * %s ₽ = %.2f ₽".format(
                _uiState.value.valve_num,
                _uiState.value.valve_price,
                _uiState.value.valve_total
            ),
            "Итоговая стоимость = %.2f ₽ + %.2f ₽ + %.2f ₽ + %.2f ₽ = %.2f ₽".format(
                _uiState.value.pipe_total,
                _uiState.value.valve_total,
                _uiState.value.meter_price.toDouble(),
                _uiState.value.filter_price.toDouble(),
                _uiState.value.total
            ),
        )
    }

}

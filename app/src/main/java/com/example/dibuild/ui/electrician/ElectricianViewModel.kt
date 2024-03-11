package com.example.dibuild.ui.electrician

import android.health.connect.datatypes.units.Length
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ElectricianViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ElectricianUiState())
    val uiState: StateFlow<ElectricianUiState> = _uiState.asStateFlow()

    fun countTotal() {
        //Стоимость провода
        val wire_total_price =
            _uiState.value.wire_length.toDouble() * _uiState.value.wire_price.toDouble()
        _uiState.value = uiState.value.copy(wire_total = wire_total_price)

        //Стоимость короба
        val duct_total_price =
            _uiState.value.duct_length.toDouble() * _uiState.value.duct_price.toDouble()
        _uiState.value = uiState.value.copy(duct_total = duct_total_price)

        //Стоимость выключателей
        val switch_total_price =
            _uiState.value.switch_num.toInt() * _uiState.value.switch_price.toDouble()
        _uiState.value = uiState.value.copy(switch_total = switch_total_price)

        //Стоимость розеток
        val socket_total_price =
            _uiState.value.socket_num.toInt() * _uiState.value.socket_price.toDouble()
        _uiState.value = uiState.value.copy(socket_total = socket_total_price)

        val total_price =
            _uiState.value.duct_total + _uiState.value.socket_total + _uiState.value.wire_total + _uiState.value.switch_total
        _uiState.value = uiState.value.copy(total = total_price)
    }

    fun updateSocketNum(newNum: String) {
        _uiState.value = uiState.value.copy(socket_num = newNum)
    }

    fun updateSwitchNum(newNum: String) {
        _uiState.value = uiState.value.copy(switch_num = newNum)
    }

    fun updateWireLength(newLength: String) {
        _uiState.value = uiState.value.copy(wire_length = newLength)
    }

    fun updateWirePrice(newPrice: String) {
        _uiState.value = uiState.value.copy(wire_price = newPrice)
    }

    fun updateDuctLength(newLength: String) {
        _uiState.value = uiState.value.copy(duct_length = newLength)
    }

    fun updateDuctPrice(newPrice: String) {
        _uiState.value = uiState.value.copy(duct_price = newPrice)
    }

    fun updateSocketPrice(newPrice: String) {
        _uiState.value = uiState.value.copy(socket_price = newPrice)
    }

    fun updateSwitchPrice(newPrice: String) {
        _uiState.value = uiState.value.copy(switch_price = newPrice)
    }


    fun validate(): Pair<Boolean, List<String>> {
        val validateResults = mapOf(
            "Количество розеток" to (_uiState.value.socket_num.toIntOrNull() != null && _uiState.value.socket_num.toInt() >= 0),
            "Количество выключателей" to (_uiState.value.switch_num.toIntOrNull() != null && _uiState.value.switch_num.toInt() >= 0),
            "Длина провода" to (_uiState.value.wire_length.toDoubleOrNull() != null && _uiState.value.wire_length.toDouble() >= 0.0),
            "Цена провода" to (_uiState.value.wire_price.toDoubleOrNull() != null && _uiState.value.wire_price.toDouble() >= 0.0),
            "Длина пластикового короба" to (_uiState.value.duct_length.toDoubleOrNull() != null && _uiState.value.duct_length.toDouble() >= 0.0),
            "Цена пластикового короба" to (_uiState.value.duct_price.toDoubleOrNull() != null && _uiState.value.duct_price.toDouble() >= 0.0),
            "Цена розетки" to (_uiState.value.socket_price.toDoubleOrNull() != null && _uiState.value.socket_price.toDouble() >= 0.0),
            "Цена выключателя" to (_uiState.value.switch_price.toDoubleOrNull() != null && _uiState.value.switch_price.toDouble() >= 0.0),
        )


        if (validateResults.containsValue(false)) {
            return Pair(false, validateResults.filter { (_, value) -> !value }.keys.toList())
        } else {
            return Pair(true, emptyList())
        }
    }

}
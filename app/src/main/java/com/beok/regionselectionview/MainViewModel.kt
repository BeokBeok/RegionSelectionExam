package com.beok.regionselectionview

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _city = MutableStateFlow<List<Area>>(emptyList())
    val city: StateFlow<List<Area>> get() = _city

    fun fetch() {
        _city.value = listOf(
            Area("서울특별시"),
            Area("부산광역시"),
            Area("대구광역시"),
            Area("인천광역시"),
            Area("광주광역시"),
            Area("대전광역시"),
            Area("울산광역시"),
            Area("세종특별자치시"),
            Area("경기도"),
            Area("강원도"),
            Area("충청북도"),
            Area("충청남도"),
            Area("전라북도"),
            Area("전라남도"),
            Area("경상북도"),
            Area("포항시"),
            Area("경상남도"),
            Area("진주시"),
            Area("제주특별자치도"),
        )
    }

    fun onClick(area: Area) {
        _city.value = _city.value
            .map {
                if (it.name == area.name) {
                    return@map area.copy(isSelected = !it.isSelected)
                }
                it
            }
    }
}



















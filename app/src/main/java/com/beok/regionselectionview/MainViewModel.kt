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

    private val _district = MutableStateFlow<List<Area>>(emptyList())
    val district: StateFlow<List<Area>> get() = _district

    fun fetchCity() {
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

    fun fetchDistrict() {
        _district.value = listOf(
            Area("전체"),
            Area("강남구"),
            Area("강동구"),
            Area("강북구"),
            Area("강서구"),
            Area("관악구"),
            Area("광진구"),
            Area("구로구"),
            Area("금천구"),
            Area("노원구"),
            Area("도봉구"),
            Area("동대문구"),
            Area("동작구"),
            Area("마포구"),
            Area("서대문구"),
            Area("서초구"),
            Area("성동구"),
            Area("성북구"),
            Area("송파구"),
            Area("양천구"),
            Area("영등포구"),
            Area("용산구"),
            Area("은평구"),
            Area("종로구"),
            Area("중구"),
            Area("중랑구"),
        )
    }

    fun onClick(scope: AreaScope, area: Area) {
        when (scope) {
            AreaScope.CITY -> {
                _city.value = _city.value
                    .map {
                        if (it.name == area.name) {
                            fetchDistrict()
                            return@map area.copy(isSelected = !it.isSelected)
                        }
                        it
                    }
            }
            AreaScope.DISTRICT -> {
                _district.value = _district.value
                    .map {
                        if (it.name == area.name) {
                            return@map area.copy(isSelected = !it.isSelected)
                        }
                        it
                    }
            }
        }
    }
}

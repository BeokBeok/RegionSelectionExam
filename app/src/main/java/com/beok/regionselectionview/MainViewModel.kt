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

    private val wholeCity = mutableListOf(
        Area("서울특별시"),
        Area("부산광역시"),
    )

    private val seoul = mutableListOf(
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

    private val busan = mutableListOf(
        Area("전체"),
        Area("중구"),
        Area("서구"),
        Area("동구"),
        Area("영도구"),
        Area("부산진구"),
        Area("동래구"),
        Area("남구"),
        Area("북구"),
        Area("해운대구"),
        Area("사하구"),
        Area("금정구"),
        Area("강서구"),
        Area("연제구"),
        Area("수영구"),
        Area("사상구"),
        Area("기장군"),
    )

    private val currentCity = mutableListOf<Area>()
    private val selectAreaList = mutableSetOf<SelectArea>()

    fun fetchCity() {
        _city.value = wholeCity
    }

    fun fetchDistrict(city: Area) {
        _district.value = when (city.name) {
            "서울특별시" -> seoul.map { area ->
                if (selectAreaList.any { selectArea ->
                        selectArea.district == area.name
                    }) {
                    area.copy(isSelected = true)
                } else {
                    area
                }
            }
            "부산광역시" -> busan
            else -> emptyList()
        }
    }

    fun onClick(scope: AreaScope, area: Area) {
        when (scope) {
            AreaScope.CITY -> {
                _city.value = _city.value
                    .map {
                        if (it.name == area.name) {
                            fetchDistrict(area)
                            currentCity.run {
                                clear()
                                add(area)
                            }
                            return@map area.copy(isSelected = !it.isSelected)
                        }
                        it.copy(isSelected = false)
                    }
            }
            AreaScope.DISTRICT -> {
                _district.value = _district.value
                    .map {
                        if (area.name == "전체") {
                            selectAreaList.add(SelectArea(currentCity.first().name, area.name))
                        }
                        if (it.name == area.name) {
                            return@map area.copy(isSelected = !it.isSelected)
                        }
                        it.copy(isSelected = false)
                    }
            }
        }
    }
}

package com.rocknhoney.matcheslistingapp.features.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rocknhoney.matcheslistingapp.core.api.repository.MatchesRepositoryInterface
import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val gson: Gson,
    private val matchesRepository: MatchesRepositoryInterface,
) : ViewModel() {

    private val _match = MutableStateFlow<MatchViewEntity?>(null)
    val match: StateFlow<MatchViewEntity?> = _match

    init {
        savedStateHandle.get<String>("match")?.let {
            _match.value = gson.fromJson(it, MatchViewEntity::class.java)
        }
    }

    fun onFavIconClicked(match: MatchViewEntity?) {
        viewModelScope.launch(Dispatchers.IO) {
            match?.let {
                if (match.isFavourite == true) {
                    matchesRepository.deleteMatch(match)
                } else {
                    matchesRepository.insertMatch(match)
                }
            }
            _match.value = match?.copy(isFavourite = match.isFavourite != true)
        }
    }
}
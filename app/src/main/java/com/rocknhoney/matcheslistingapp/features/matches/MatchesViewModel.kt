package com.rocknhoney.matcheslistingapp.features.matches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rocknhoney.matcheslistingapp.core.api.repository.MatchesRepositoryInterface
import com.rocknhoney.matcheslistingapp.core.data.enums.ScreenState
import com.rocknhoney.matcheslistingapp.core.data.model.Match
import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesRepository: MatchesRepositoryInterface,
    private val gson: Gson
) : ViewModel() {

    private val _matches = MutableStateFlow<List<List<MatchViewEntity?>?>?>(emptyList())
    val matches: StateFlow<List<List<MatchViewEntity?>?>?> = _matches

    private val _screenState = MutableStateFlow(ScreenState.LOADING)
    val screenState: StateFlow<ScreenState> = _screenState

    private val compositeDisposable = CompositeDisposable()

    private var matchesFromService: List<Match?>? = null

    init {
        getPosts()
    }

    fun getPosts() {
        _screenState.value = ScreenState.LOADING
        viewModelScope.launch(Dispatchers.IO) {
            delay(1500)
            val disposable = matchesRepository.getMatches()
                .observeOn(Schedulers.single())
                .subscribe({ posts ->
                    val favMatches =
                        matchesRepository.getFavouriteMatchesFromLocal().map { it.i }
                    matchesFromService = posts.data
                    _matches.value = mapToMatchViewEntityList(posts.data, favMatches)
                        ?.sortedBy { it?.d }
                        ?.filter { it?.sc?.st == 5 }
                        ?.groupBy { it?.to?.i }
                        ?.values?.toList()
                    _screenState.value = ScreenState.SUCCESS
                }, { throwable ->
                    _screenState.value = ScreenState.ERROR
                })
            compositeDisposable.add(disposable)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun convertMatchToJson(match: MatchViewEntity?): String {
        return gson.toJson(match, MatchViewEntity::class.java)
            .replace("/", "")
    }

    private fun mapToMatchViewEntityList(
        matches: List<Match?>?,
        favMatches: List<Int?>
    ): List<MatchViewEntity?>? {
        return matches?.mapNotNull { match ->
            if (favMatches.contains(match?.i)) {
                match?.toMatchViewEntity()?.copy(isFavourite = true)
            } else {
                match?.toMatchViewEntity()
            }
        }
    }

    fun onFavIconClicked(chosenMatch: MatchViewEntity?) {
        val updatedMatchesList = _matches.value?.map { innerList ->
            innerList?.map { match ->
                if (match?.i == chosenMatch?.i) {
                    viewModelScope.launch(Dispatchers.IO) {
                        match?.let {
                            if (match.isFavourite == true) {
                                matchesRepository.deleteMatch(match)
                            } else {
                                matchesRepository.insertMatch(match)
                            }
                        }
                    }
                    match?.copy(isFavourite = match.isFavourite != true)
                } else {
                    match
                }
            }
        }
        _matches.value = updatedMatchesList
    }

    fun refreshList() {
        viewModelScope.launch(Dispatchers.IO) {
            val favMatches =
                matchesRepository.getFavouriteMatchesFromLocal().map { it.i }
            val updatedMatchesList = _matches.value?.map { innerList ->
                innerList?.map { match ->
                    if (favMatches.contains(match?.i)) {
                        match?.copy(isFavourite = true)
                    } else {
                        match?.copy(isFavourite = false)
                    }
                }
            }
            _matches.value = updatedMatchesList
        }
    }
}
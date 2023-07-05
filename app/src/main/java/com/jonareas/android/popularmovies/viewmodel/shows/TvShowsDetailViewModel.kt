package com.jonareas.android.popularmovies.viewmodel.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.model.repository.MovieRepository
import com.jonareas.android.popularmovies.model.repository.TvShowsRepository
import com.jonareas.android.popularmovies.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class TvShowsDetailViewModel @Inject constructor(
    private val TvShowRepository: TvShowsRepository,
    private val dispatchers: DispatcherProvider
) :
    ViewModel() {

    private var _tv = MutableLiveData<TvShow>()
    val tv : LiveData<TvShow> = _tv

    fun getTvShow(id : Int) : Unit = fetchTvShowById(id)

    private fun fetchTvShowById(id : Int) {

        viewModelScope.launch(dispatchers.io) {
            try {
                TvShowRepository.fetchTvShowById(id).collectLatest { selectedShow ->
                    _tv.postValue(selectedShow)
                }
            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }

    }

}

package com.jonareas.android.popularmovies.model.repository

import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.network.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieService: MovieService) :
    MovieRepository {

    override suspend fun fetchPopularMoviesFlow(): Flow<List<Movie>> =
        flow {
            emit(movieService.fetchPopularMovies().movies)
        }

    override suspend fun fetchComingSoonMoviesFlow(): Flow<List<Movie>> =
        flow {
            emit(movieService.fetchUpcomingMovies().movies)
        }

    override suspend fun fetchNowPlayingMoviesFlow(): Flow<List<Movie>> =
    flow {
        emit(movieService.fetchNowPlayingMovies().movies)
    }

    override suspend fun fetchMovieByIdFlow(id: Int): Flow<Movie> =
        flow {
            emit(movieService.fetchMovieById(id))
        }

    override suspend fun fetchTopRatedMoviesFlow(): Flow<List<Movie>> =
        flow {
            emit(movieService.fetchTopRatedMovies().movies)
        }
}

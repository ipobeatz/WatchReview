package com.jonareas.android.popularmovies.network.service

import com.jonareas.android.popularmovies.model.entities.Movie
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.network.response.GetTvShowsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowsService {

    @GET(value = "tv/popular")
    suspend fun fetchPopularTvShows() : GetTvShowsResponse

    @GET(value = "tv/top_rated")
    suspend fun fetchTopRatedTvShows() : GetTvShowsResponse

    @GET(value = "tv/on_the_air")
    suspend fun fetchOnTheAirTvShows() : GetTvShowsResponse

    @GET(value = "series_id")
    suspend fun fetchMovieByIdFlow(@Path("series_id") tvId : Int) : TvShow

    @GET(value = "tv/airing_today")
    suspend fun fetchAiringTodayTvShows() : GetTvShowsResponse

}
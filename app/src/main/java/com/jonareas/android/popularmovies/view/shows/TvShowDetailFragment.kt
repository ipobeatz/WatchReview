package com.jonareas.android.popularmovies.view.shows

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jonareas.android.popularmovies.R
import com.jonareas.android.popularmovies.databinding.FragmentMovieDetailBinding
import com.jonareas.android.popularmovies.databinding.FragmentTvShowDetailBinding
import com.jonareas.android.popularmovies.model.entities.TvShow
import com.jonareas.android.popularmovies.view.movies.MovieDetailFragmentArgs
import com.jonareas.android.popularmovies.viewmodel.movies.MovieDetailViewModel
import com.jonareas.android.popularmovies.viewmodel.shows.TvShowsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

@AndroidEntryPoint
class TvShowDetailFragment : Fragment() {


    private val viewModel: TvShowsDetailViewModel by viewModels()
    private val navArgs: TvShowDetailFragmentArgs by navArgs()

    private var _binding: FragmentTvShowDetailBinding? = null
    private val binding: FragmentTvShowDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTvShowDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTvShow(navArgs.showId)
        initObservers()

    }
    private fun initObservers(): Unit = binding.run {

        lifecycleOwner = this@TvShowDetailFragment.viewLifecycleOwner

        viewModel.tv.observe(viewLifecycleOwner) { tv ->
            binding.tv = tv
        }

        // API isteği başarılı oldu mu, kontrol etmek için log ekle
        if (tv != null) {
            Log.d(TAG, "API isteği başarılı")
        } else {
            Log.e(TAG, "API isteği başarısız")
        }




    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}


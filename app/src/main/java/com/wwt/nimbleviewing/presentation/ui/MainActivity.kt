package com.wwt.nimbleviewing.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.wwt.nimbleviewing.core.NimbleActivity
import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.data.util.DataState
import com.wwt.nimbleviewing.databinding.ActivityScrollingBinding
import com.wwt.nimbleviewing.presentation.ui.main.adapter.AlbumAdapter
import com.wwt.nimbleviewing.presentation.ui.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : NimbleActivity<ActivityScrollingBinding, MainViewModel>() {
    override fun initViewModel(): MainViewModel {
        val viewModel by viewModel<MainViewModel>()
        return viewModel
    }

    override fun initBinding() = ActivityScrollingBinding.inflate(layoutInflater)

    private lateinit var listAdapter: AlbumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAlbumList()
        setUpObserver()
    }

    private fun setAlbumList() {
        binding.rvAlbumList.apply {
            listAdapter = AlbumAdapter()
            adapter = listAdapter
        }
    }

    private fun setUpObserver() {
        viewModel.fetchAlbumList(networkHelper.isNetworkConnected())
        viewModel.album.observe(this,albumObserver)
    }

    private val albumObserver = Observer<DataState<List<Album>>> {
        when (it) {
            is DataState.Success -> {
                binding.progressBar.visibility = View.GONE
                renderList(it.data)
            }
            is DataState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is DataState.Error -> {
                binding.progressBar.visibility = View.GONE
            }
            else -> {
                binding.progressBar.visibility = View.GONE
            }

        }
    }


    private fun renderList(users: List<Album>) {
        listAdapter.addData(users)
    }

}
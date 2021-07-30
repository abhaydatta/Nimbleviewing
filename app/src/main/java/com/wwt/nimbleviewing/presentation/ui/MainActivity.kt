package com.wwt.nimbleviewing.presentation.ui

import android.os.Bundle
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
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    RecyclerView.VERTICAL
                )
            )
            adapter = listAdapter
        }
    }

    private fun setUpObserver() {
        viewModel.fetchAlbumList(networkHelper.isNetworkConnected())
        viewModel.album.observe(this, Observer {
            when (it) {
                is DataState.Success -> {
                    // progressBar.visibility = View.GONE
                    renderList(it.data)
                    // scrollingView.visibility = View.VISIBLE
                }
                is DataState.Loading -> {
                    // progressBar.visibility = View.VISIBLE
                    // scrollingView.visibility = View.GONE
                }
                else -> {

                }
            }
        })
    }

    private fun renderList(users: List<Album>) {
        listAdapter.addData(users)
    }

}
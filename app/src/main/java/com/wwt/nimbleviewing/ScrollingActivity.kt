package com.wwt.nimbleviewing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.data.util.Status
import com.wwt.nimbleviewing.databinding.ActivityScrollingBinding
import com.wwt.nimbleviewing.presentation.ui.main.adapter.AlbumAdapter
import com.wwt.nimbleviewing.presentation.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_scrolling.*
import org.koin.android.viewmodel.ext.android.viewModel

class ScrollingActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModel()
    private lateinit var listAdapter: AlbumAdapter

    //private val listAdapter: AlbumListAdapter by lazy { AlbumListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(ActivityScrollingBinding.inflate(LayoutInflater.from(this), null, false)) {
            setContentView(root)

            scrollingView.albumList.layoutManager = LinearLayoutManager(this@ScrollingActivity)
            listAdapter = AlbumAdapter(arrayListOf())
            scrollingView.albumList.addItemDecoration(
                DividerItemDecoration(
                    scrollingView.albumList.context,
                    (scrollingView.albumList.layoutManager as LinearLayoutManager).orientation
                )
            )
            scrollingView.albumList.adapter = listAdapter
        }
        setUpObserver()
    }

    private fun setUpObserver() {
        mainViewModel.album.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                   // progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    scrollingView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                   // progressBar.visibility = View.VISIBLE
                    scrollingView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                  //  progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(users: List<Album>) {
        listAdapter.addData(users)
        listAdapter.notifyDataSetChanged()
    }
}
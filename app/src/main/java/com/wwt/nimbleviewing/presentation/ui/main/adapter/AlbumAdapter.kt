package com.wwt.nimbleviewing.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wwt.nimbleviewing.R
import com.wwt.nimbleviewing.data.model.Album
import com.wwt.nimbleviewing.databinding.ListItemAlbumBinding
import kotlinx.android.synthetic.main.list_item_album.view.*

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.DataViewHolder>() {
    private val albums: MutableList<Album> = mutableListOf()

    class DataViewHolder(private val binding: ListItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.albumTitle.text = album.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ListItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(albums[position])

    fun addData(list: List<Album>) {
        albums.clear()
        albums.addAll(list)
        notifyDataSetChanged()
    }
}
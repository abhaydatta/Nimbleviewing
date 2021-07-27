package com.wwt.nimbleviewing.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wwt.nimbleviewing.R
import com.wwt.nimbleviewing.data.model.Album
import kotlinx.android.synthetic.main.list_item_album.view.*

class AlbumAdapter(
    private val albums: ArrayList<Album>
): RecyclerView.Adapter<AlbumAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            itemView.albumTitle.text = album.title
           /* Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_album, parent,
                false
            )
        )

    override fun getItemCount(): Int = albums.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(albums[position])

    fun addData(list: List<Album>) {
        albums.addAll(list)
    }
}
package com.example.vamatop100.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vamatop100.MainActivity
import com.example.vamatop100.R
import com.example.vamatop100.domain.entities.Album
import com.example.vamatop100.presentation.AlbumDetailFragment

private const val TAG = "AlbumAdapter"

class AlbumAdapter(private var albums: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.album_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(albums[position])
    }

    override fun getItemCount(): Int = albums.size

    @SuppressLint("NotifyDataSetChanged")
    fun setAlbums(incomingAlbums: List<Album>) {
        albums = incomingAlbums
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivAlbumImage: ImageView = itemView.findViewById(R.id.ivAlbumImage)
        private val tvAlbumTitle: TextView = itemView.findViewById(R.id.tvAlbumTitle)
        private val tvAlbumAuthor: TextView = itemView.findViewById(R.id.tvAlbumAuthor)
        private val cardContainer: CardView = itemView.findViewById(R.id.cardContainer)
        fun bind(album: Album) {
            Glide.with(itemView.context).load(album.albumCover).into(ivAlbumImage)
            tvAlbumTitle.text = album.name
            tvAlbumAuthor.text = album.artistName
            cardContainer.setOnClickListener {
                (it.context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container, AlbumDetailFragment.newInstance(album))
                    .addToBackStack(null).commit()
            }
        }

    }

}
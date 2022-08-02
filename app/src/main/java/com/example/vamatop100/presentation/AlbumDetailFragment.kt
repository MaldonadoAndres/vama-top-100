package com.example.vamatop100.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.vamatop100.databinding.FragmentAlbumDetailBinding
import com.example.vamatop100.domain.entities.Album
import java.time.LocalDate


private const val ARG_PARAM1 = "param1"

class AlbumDetailFragment : Fragment() {
    private var _binding: FragmentAlbumDetailBinding? = null
    private val binding get() = _binding!!
    private var album: Album? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            album = it.getParcelable(ARG_PARAM1) as Album?
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailBinding.inflate(inflater, container, false)
        Glide.with(this).load(album?.albumCover).into(binding.ivAlbumImage)
        binding.tvAlbumAuthor.text = album?.artistName
        binding.tvAlbumTitle.text = album?.name
        binding.chGenre.text = album?.realmGenres?.first { genre -> genre.name != "music" }?.name
        val date = LocalDate.parse(album?.releaseDate)
        binding.tvReleaseDate.text = "Released ${date.month} ${date.dayOfMonth}, ${date.year}"
        binding.btnVisit.setOnClickListener {
            val url: String? = album?.url
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "AlbumDetailFragment"

        @JvmStatic
        fun newInstance(album: Album) =
            AlbumDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, album)

                }
            }
    }
}
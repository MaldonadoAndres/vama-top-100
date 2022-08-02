package com.example.vamatop100.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vamatop100.databinding.FragmentAlbumListBinding
import com.example.vamatop100.presentation.adapters.AlbumAdapter
import com.example.vamatop100.presentation.viewModels.AlbumsViewModel

class AlbumListFragment : Fragment() {
    private var _binding: FragmentAlbumListBinding? = null
    private val binding get() = _binding!!
    private val albumViewModel: AlbumsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        setUpRecyclerview()
        albumViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = false
            albumViewModel.getAlbums(shouldRefresh = true)
        }
        binding.tvTryAgain.setOnClickListener {
            binding.swipeToRefresh.isRefreshing = false
            albumViewModel.getAlbums(shouldRefresh = true)
        }
        albumViewModel.getAlbums()
        return binding.root
    }

    private fun setUpRecyclerview() {
        val rv = binding.rvAlbums
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        rv.setHasFixedSize(true)
        val adapter = AlbumAdapter(emptyList())
        rv.adapter = adapter
        albumViewModel.albums.observe(viewLifecycleOwner) { albums ->
            binding.tryAgainContainer.visibility = if (albums.isEmpty()) View.VISIBLE else View.GONE
            adapter.setAlbums(albums)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
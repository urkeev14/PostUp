package com.example.postup.ui.fragment.posts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.postup.R
import com.example.postup.databinding.ActivityMainBinding
import com.example.postup.databinding.FragmentPostsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    val viewModel: PostsViewModel by viewModels()

    var _binding: FragmentPostsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.btnGoToDetails.setOnClickListener {
            val action = PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(0,0)
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}
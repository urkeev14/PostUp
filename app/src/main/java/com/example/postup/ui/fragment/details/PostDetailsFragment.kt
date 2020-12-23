package com.example.postup.ui.fragment.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.postup.R
import com.example.postup.databinding.FragmentPostDetailsBinding
import com.example.postup.ui.fragment.posts.PostsFragmentDirections
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.NonCancellable.cancel
import java.nio.file.Files.delete

@AndroidEntryPoint
class PostDetailsFragment : Fragment() {

    var _binding: FragmentPostDetailsBinding? = null
    val binding get() = _binding!!

    val viewModel: PostDetailsViewModel by viewModels()
    val navArgs: PostDetailsFragmentArgs by navArgs()
    var userId: Int = -1
    var postId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostDetailsBinding.inflate(inflater, container, false)

        hideRefreshButton()
        getFromNavArgs()
        loadData()
        observeData()

        return binding.root
    }


    private fun hideRefreshButton() {
        requireActivity().let {
            val refreshButton = it.findViewById<View>(R.id.action_refresh)
            refreshButton.visibility = View.INVISIBLE
            refreshButton.refreshDrawableState()
        }
    }


    fun getFromNavArgs(){
        userId = navArgs.userId
        postId = navArgs.postId
    }


    private fun loadData(){
        viewModel.loadData(userId, postId)
    }

    private fun observeData(){
        viewModel.user.observe(viewLifecycleOwner, Observer {
            binding.user = it
        })
        viewModel.post.observe(viewLifecycleOwner, Observer {
            binding.post = it
        })
    }

    override fun onResume() {
        super.onResume()

        binding.btnRemovePost.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.delete_post))
                .setMessage(resources.getString(R.string.delete_post_message))
                .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->

                }
                .setPositiveButton(resources.getString(R.string.delete)) { dialog, itemSelectedIndex ->
                    viewModel.deletePost(postId)
                    gotoPostFragment()
                }
                .show()
        }
    }

    private fun gotoPostFragment(){
        val action = PostDetailsFragmentDirections.actionPostDetailsFragmentToPostsFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        showRefreshButton()
        _binding = null
    }


    private fun showRefreshButton() {
        activity?.let {
            val refreshButton = it.findViewById<View>(R.id.action_refresh)
            refreshButton.visibility = View.VISIBLE
        }
    }

}
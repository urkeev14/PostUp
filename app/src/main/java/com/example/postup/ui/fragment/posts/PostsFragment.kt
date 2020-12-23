package com.example.postup.ui.fragment.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postup.R
import com.example.postup.databinding.FragmentPostsBinding
import com.example.postup.util.recyclerview.adapter.ItemPostRecyclerViewAdapter
import com.example.postup.util.recyclerview.adapter.listener.OnItemClickListener
import com.example.postup.util.recyclerview.decorator.ItemPostRecyclerViewDecorator
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class PostsFragment : Fragment(),
    OnItemClickListener {

    val viewModel: PostsViewModel by activityViewModels()

    var _binding: FragmentPostsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)

        configureLayout()
        observeViewModel()

        return binding.root
    }

    private fun configureLayout() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val adapter = ItemPostRecyclerViewAdapter(
            WeakReference(this)
        )
        val decorator = ItemPostRecyclerViewDecorator(16, 16)

        binding.rvPosts.also {
            it.layoutManager = layoutManager
            it.adapter = adapter
            it.addItemDecoration(decorator)
        }
    }

    private fun observeViewModel() {
        viewModel.list.observe(viewLifecycleOwner, Observer { list ->
            with(binding.rvPosts) {
                if (!list.isNullOrEmpty()) {
                    (adapter as ItemPostRecyclerViewAdapter).setList(list)
                    startLayoutAnimation()
                    Snackbar.make(requireView(), "Refreshing ...", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onItemClick(position: Int) {
        val post = viewModel.list.value?.get(position)

        PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(post!!.userId!!, post.id!!)
            .also { action ->
                findNavController().navigate(action)
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }


}
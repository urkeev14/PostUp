package com.example.postup.ui.fragment.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postup.databinding.FragmentPostsBinding
import com.example.postup.util.recyclerview.adapter.ItemPostRecyclerViewAdapter
import com.example.postup.util.recyclerview.adapter.listener.OnItemClickListener
import com.example.postup.util.recyclerview.decorator.ItemPostRecyclerViewDecorator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class PostsFragment : Fragment(),
    OnItemClickListener {

    val viewModel: PostsViewModel by viewModels()

    var _binding: FragmentPostsBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostsBinding.inflate(inflater, container, false)

        loadData()
        configureLayout()
        observeViewModel()

        return binding.root
    }

    private fun loadData() {
        viewModel.getPosts()
    }

    private fun configureLayout() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvPosts.also {
            it.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            it.adapter =
                ItemPostRecyclerViewAdapter(
                    WeakReference(this)
                )
            ItemPostRecyclerViewDecorator(16, 16).let { decoration ->
                it.addItemDecoration(decoration)
            }
        }
    }

    private fun observeViewModel() {
        viewModel.list.observe(viewLifecycleOwner, Observer { list ->
            with(binding.rvPosts) {
                if (!list.isNullOrEmpty())
                    (adapter as ItemPostRecyclerViewAdapter).setList(list)

            }
        })
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(requireContext(), "Hey $position", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}
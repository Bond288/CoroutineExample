package ru.tilk.coroutineexample.posts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.tilk.coroutineexample.base.presentation.UiState
import ru.tilk.coroutineexample.base.view.visible
import ru.tilk.coroutineexample.databinding.PostsFragmentBinding
import ru.tilk.coroutineexample.posts.domain.model.Post
import ru.tilk.coroutineexample.posts.presentation.MainViewModel

@AndroidEntryPoint
class PostsFragment: Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private val adapter = PostsAdapter()
    private var binding: PostsFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PostsFragmentBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.content?.adapter = adapter
        binding?.refreshContainer?.setOnRefreshListener { viewModel.load() }
        binding?.fail?.retyButton?.setOnClickListener { viewModel.load() }
        viewModel.load()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(this::getLifecycle,::onChangeState)
        viewModel.content.observe(this::getLifecycle, ::onChangeContent)
    }

    private fun onChangeState(state: UiState) {
        binding?.apply {
            fail.root.visible = state == UiState.FAIL
            progress.visible = state == UiState.PROGRESS
            content.visible = state == UiState.CONTENT
            refreshContainer.isRefreshing = false
        }
    }

    private fun onChangeContent(content: List<Post>) {
        adapter.content = content.toMutableList()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
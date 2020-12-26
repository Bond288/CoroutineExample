package ru.tilk.coroutineexample.posts.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.tilk.coroutineexample.base.presentation.UiState
import ru.tilk.coroutineexample.posts.domain.GetPostsUseCase
import ru.tilk.coroutineexample.posts.domain.model.Post

class MainViewModel @ViewModelInject constructor(private val getPostsUseCase: GetPostsUseCase) : ViewModel() {

	val state = MutableLiveData(UiState.PROGRESS)
	val content = MutableLiveData<List<Post>>()

	fun load() {
		state.value = UiState.PROGRESS
		viewModelScope.launch {
			getPostsUseCase()
				.catch { fail ->
					state.value = UiState.FAIL
					fail.printStackTrace()
				}
				.collect { result ->
					content.value = result
					state.value = UiState.CONTENT
				}
		}
	}
}
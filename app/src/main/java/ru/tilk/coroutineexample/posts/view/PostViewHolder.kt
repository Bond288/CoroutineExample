package ru.tilk.coroutineexample.posts.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tilk.coroutineexample.databinding.PostItemLayoutBinding
import ru.tilk.coroutineexample.posts.domain.model.Post

class PostViewHolder(private val binding: PostItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun make(parent: ViewGroup): PostViewHolder {
            val binding = PostItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PostViewHolder(binding)
        }
    }

    fun bind(post: Post) {
        binding.title.text = post.title
        binding.description.text = post.description
    }
}
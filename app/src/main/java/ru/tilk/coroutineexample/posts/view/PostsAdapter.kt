package ru.tilk.coroutineexample.posts.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.tilk.coroutineexample.posts.domain.model.Post

class PostsAdapter : RecyclerView.Adapter<PostViewHolder>() {

    var content = mutableListOf<Post>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder =
        PostViewHolder.make(parent)

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = content[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = content.size
}
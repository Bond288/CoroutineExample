package ru.tilk.coroutineexample.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.tilk.coroutineexample.R
import ru.tilk.coroutineexample.databinding.ActivityMainBinding
import ru.tilk.coroutineexample.posts.view.PostsFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        show(PostsFragment())
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun show(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commitAllowingStateLoss()
    }
}
package com.example.submisionawal.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submisionawal.R
import com.example.submisionawal.adapter.FavoriteAdapter
import com.example.submisionawal.data.response.ItemsItem
import com.example.submisionawal.ui.detail.DetailActivity

class FavoriteActivity : AppCompatActivity() {
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter { clickedItem ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra("favorite", clickedItem as Parcelable)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = findViewById(R.id.rvList3)
        recyclerView.adapter = favoriteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun observeViewModel() {
        favoriteViewModel.allFavoriteUsers.observe(this) { favoriteUsers ->
            val items = arrayListOf<ItemsItem>()
            favoriteUsers.map {
                val item = ItemsItem(login = it.username, avatarUrl = it.avatarUrl!!)
                items.add(item)
            }
            favoriteAdapter.submitList(items)
        }
        favoriteViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        findViewById<ProgressBar>(R.id.progressBar4).visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }
}
package com.example.submisionawal.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.submisionawal.R
import com.example.submisionawal.adapter.SectionsPagerAdapter
import com.example.submisionawal.data.database.FavoriteUser
import com.example.submisionawal.data.response.ItemsItem
import com.example.submisionawal.ui.favorite.FavoriteViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
    private lateinit var user: ItemsItem
    private lateinit var favoriteUserLiveData: LiveData<FavoriteUser>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        favoriteViewModelFactory = ViewModelProvider(this)[FavoriteViewModelFactory::class.java]

        val clickedItemUser = intent.getParcelableExtra<ItemsItem>("user")
        val clickedItemFavorite = intent.getParcelableExtra<ItemsItem>("favorite")

        val clickedItem = clickedItemUser ?: clickedItemFavorite


        if (clickedItem != null) {
            user = clickedItem
            val login = user.login ?: ""
            val avatarUrl = user.avatarUrl ?: ""
            Glide.with(this)
                .load(user.avatarUrl)
                .into(findViewById(R.id.imageView2))
            findViewById<TextView>(R.id.textView3).text = login

            detailViewModel.getDetailUser(login)
            setupViewPager(login)
            observeViewModel()
            setupFavoriteUserObserver(login)

            val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
            fab.setOnClickListener {
                toggleFavoriteUser(login, avatarUrl)
            }
        } else {
            Toast.makeText(this, "Error: Clicked item is null", Toast.LENGTH_SHORT).show()
            finish() 
        }
    }


    private fun toggleFavoriteUser(login: String, avatarUrl: String) {
        val favoriteUser = FavoriteUser(username = login, avatarUrl = avatarUrl)
        favoriteUserLiveData.value?.let { existingFavoriteUser ->
            favoriteViewModelFactory.delete(existingFavoriteUser)
        } ?: run {
            favoriteViewModelFactory.insert(favoriteUser)
        }
    }

    private fun setupViewPager(username: String) {
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val tabTitles = listOf(getString(R.string.following), getString(R.string.follower))

        val pagerAdapter = SectionsPagerAdapter(this, username)
        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        detailViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        detailViewModel.detailUser.observe(this) { detailUser ->
            detailUser?.let {
                val followingCount = it.following
                val followersCount = it.followers
                findViewById<TextView>(R.id.textView4).text = "$followingCount Following"
                findViewById<TextView>(R.id.textView5).text = "$followersCount Followers"
                findViewById<TextView>(R.id.textView6).apply {
                    text = (it.name ?: "").toString()
                    visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        findViewById<ProgressBar>(R.id.progressBar3).visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setupFavoriteUserObserver(username: String) {
        favoriteUserLiveData = favoriteViewModelFactory.getFavoriteUserByUsername(username)
        favoriteUserLiveData.observe(this) { favoriteUser ->
            val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)

            if (favoriteUser == null) {
                fab.setImageResource(R.drawable.baseline_favorite_border_24)
            } else {
                fab.setImageResource(R.drawable.baseline_favorite_24)
            }
        }
    }
}
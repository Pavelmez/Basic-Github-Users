package com.example.submisionawal.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submisionawal.adapter.UserAdapter
import com.example.submisionawal.data.preferences.SettingPreferences
import com.example.submisionawal.data.preferences.dataStore
import com.example.submisionawal.databinding.ActivityMainBinding
import com.example.submisionawal.ui.favorite.FavoriteActivity
import com.example.submisionawal.ui.settings.SettingsActivity
import com.example.submisionawal.ui.settings.SettingsViewModel
import com.example.submisionawal.ui.settings.SettingsViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var settingsViewModel: SettingsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val pref = SettingPreferences.getInstance(application.dataStore)
        settingsViewModel =
            ViewModelProvider(this, SettingsViewModelFactory(pref))[SettingsViewModel::class.java]

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    val username = textView.text.toString()
                    searchUser(username)
                    searchView.hide()
                    true
                }
            favoriteButton.setOnClickListener {
                favoriteActivity()
            }
            settingsButton.setOnClickListener {
                settingsActivity()
            }
        }
        initRecyclerView()
        observeViewModel()
        observeDarkMode()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)

        userAdapter = UserAdapter()
        binding.rvList.adapter = userAdapter
    }

    private fun searchUser(username: String) {
        mainViewModel.getUserList(username)
    }

    private fun observeViewModel() {
        mainViewModel.userList.observe(this) { userList ->
            userAdapter.submitList(userList)
        }

        mainViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun favoriteActivity() {
        val intent = Intent(this, FavoriteActivity::class.java)
        startActivity(intent)
    }

    private fun settingsActivity() {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    private fun observeDarkMode() {
        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}
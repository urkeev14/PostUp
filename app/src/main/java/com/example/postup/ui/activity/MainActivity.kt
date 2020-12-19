package com.example.postup.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.postup.R
import com.example.postup.databinding.ActivityMainBinding
import com.example.postup.ui.fragment.posts.PostsViewModel
import com.example.postup.util.constants.REFRESH_INTERVAL_NONE
import com.example.postup.util.runnable.OnPostRefreshListener
import com.example.postup.util.runnable.PostRefreshRunnable
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnPostRefreshListener {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    val viewModel: PostsViewModel by viewModels()
    var postRefreshRunnable: PostRefreshRunnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        setupToolbarNavigation()
    }

    private fun init() {
        postRefreshRunnable = PostRefreshRunnable(WeakReference(this))
    }

    private fun bind() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setContentView(binding.root)
    }

    private fun setupToolbarNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolbar)

        AppBarConfiguration(navController.graph).also {
            setupActionBarWithNavController(navController, it)
        }
    }

    override fun onResume() {
        super.onResume()

        postRefreshRunnable?.refresh()
    }

    override fun onRefresh() {
        viewModel.getPosts()
        postRefreshRunnable?.refresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                postRefreshRunnable?.refresh(REFRESH_INTERVAL_NONE)
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()

        postRefreshRunnable = null
    }
}
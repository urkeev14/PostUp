package com.example.postup.ui.activity

import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.postup.R
import com.example.postup.app.PostUp
import com.example.postup.databinding.ActivityMainBinding
import com.example.postup.repo.local.LocalRepositoryObserver
import com.example.postup.ui.fragment.posts.PostsViewModel
import com.example.postup.util.constants.REFRESH_IMMEDIATELY
import com.example.postup.util.runnable.OnPostRefreshListener
import com.example.postup.util.runnable.PostRefreshRunnable
import dagger.hilt.android.AndroidEntryPoint
import java.lang.ref.WeakReference

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    private val viewModel: PostsViewModel by viewModels()
    private var _postRefreshRunnable: PostRefreshRunnable? = null
    private val postRefreshRunnable get() = _postRefreshRunnable!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        bind()
        setupToolbarNavigation()
        loadPosts()
    }

    private fun init() {
        _postRefreshRunnable = PostRefreshRunnable(viewModel)
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

        AppBarConfiguration(navController.graph).also {config ->
            setupActionBarWithNavController(navController, config)
        }
    }

    private fun loadPosts(){
        viewModel.loadPosts()
    }

    override fun onResume() {
        super.onResume()
        observeCacheChange()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        PostUp.setConfigurationChanged()
        super.onConfigurationChanged(newConfig)
    }

    private fun observeCacheChange() {
        LocalRepositoryObserver._isModified.observe(this, Observer { isModified ->
            if (isModified)
                viewModel.loadCachedPosts()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_refresh -> {
                postRefreshRunnable.execute(REFRESH_IMMEDIATELY)
            }
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()

        _postRefreshRunnable = null
        viewModel.deleteCachedPosts()
    }
}
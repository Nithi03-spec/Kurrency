/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import io.github.nithi.kurrency.R
import io.github.nithi.kurrency.databinding.ActivityMainBinding
import io.github.nithi.kurrency.util.ext.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var appBarConfiguration: AppBarConfiguration

    // Lazy initialization to avoid blocking main thread
    private val navController by lazy {
        val navHostFragment =
            // Extension is not working, bug link: https://issuetracker.google.com/issues/142847973
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set content view immediately for faster startup
        setContentView(binding.root)

        // Move heavy initialization off main thread if needed
        initializeUI()

        // If you have heavy initialization (database, preferences, etc.), move them here:
        // lifecycleScope.launch(Dispatchers.IO) {
        //     initializeHeavyComponents()
        // }
    }

    private fun initializeUI() {
        // Keep UI setup on main thread but ensure it's lightweight
        setSupportActionBar(binding.toolbar.toolbar)

        // Initialize navigation configuration
        appBarConfiguration = AppBarConfiguration.Builder(R.id.currencyFragment).build()

        // Setup action bar - this should be fast
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    // Add this method if you have heavy initialization tasks
    private suspend fun initializeHeavyComponents() {
        // Example of heavy operations that should be moved off main thread:
        // - Database initialization
        // - SharedPreferences reading
        // - Network requests
        // - Large data processing

        withContext(Dispatchers.Main) {
            // Update UI after heavy operations complete
            // updateUIAfterInitialization()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Clean up any resources here to prevent leaks
        // Cancel ongoing coroutines, close streams, etc.
    }
}

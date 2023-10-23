package com.example.shoply

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.example.shoply.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerlayout: DrawerLayout = binding.drlayout
        toggle = ActionBarDrawerToggle(this, drawerlayout, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.shopbycategories -> {
                    Toast.makeText(applicationContext, "Shop by cat.", Toast.LENGTH_SHORT).show()
                }

                R.id.myorders -> {
                    Toast.makeText(applicationContext, "My orders", Toast.LENGTH_SHORT).show()
                }

                R.id.favourites -> {
                    Toast.makeText(applicationContext, "Favourites", Toast.LENGTH_SHORT).show()
                }

                R.id.faqs -> {
                    Toast.makeText(applicationContext, "FAQs", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }
        supportActionBar?.title = "Bosh sahifa"


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}

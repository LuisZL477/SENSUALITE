package com.example.sensualite

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar  // Correct import for Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class Inicio : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)  // Correctly use androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_Home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
       when(item.itemId){
           R.id.nav_Home -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, HomeFragment()).commit()

           R.id.nav_Vibradores -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, VibradoresFragment()).commit()

           R.id.nav_Dildos -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, DildosFragment()).commit()

           R.id.nav_Succionadores -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, SuccionadoresFragment()).commit()

           R.id.nav_Bolitas -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, BolitasFragment()).commit()

           R.id.nav_Masturbadores -> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, MasturbadoresFragment()).commit()

           R.id.nav_Extensiones-> supportFragmentManager.beginTransaction()
               .replace(R.id.fragment_container, ExtensionesFragment()).commit()

           R.id.nav_layout -> Toast.makeText(this,"Logout", Toast.LENGTH_SHORT).show()
       }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer((GravityCompat.START))
        }else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}

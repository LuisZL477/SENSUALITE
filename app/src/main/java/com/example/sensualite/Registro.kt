package com.example.sensualite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.sensualite.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("18","19","20","21","22","23","24","25","26","27","28"
            ,"29","30","31","32","33","34","35","36","37","38"
            ,"39","40","41","42","43","44","45","46","47","48"
            ,"49","50","51","52","53","54","55","56","57","58"
            ,"59","60","61","62","63","64","65","66","67","68"
            ,"69","70","71","72","73","74","75","76","77","78"
            ,"79","80","81","82","83","84","85","86","87","88"
            ,"89","90","91","92","93","94","95","96","97","98"
            ,"99","100")


        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        binding.edad.setAdapter(adapter)


        val button = findViewById<Button>(R.id.salir_login)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }
}
package com.example.sensualite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.sensualite.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf("19","20","21","22","23","24", "25", "26", "27", "29", "30", "31" , "32")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        binding.edad.setAdapter(adapter)


    }
}
package com.yasinsenel.izmireczaneuygulamasi.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import com.yasinsenel.izmireczaneuygulamasi.data.EczaneDataClassItem
import com.yasinsenel.izmireczaneuygulamasi.databinding.ActivitySelectAdressBinding

class SelectAdressActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectAdressBinding
    private val arrayBolge : ArrayList<String> = arrayListOf()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectAdressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences("com.yasinsenel.zmieczaneuygulamas", Context.MODE_PRIVATE)
        val intent = intent
        val asd = intent.getParcelableArrayListExtra<EczaneDataClassItem>("veri")
        asd.let {
            it!!.forEach {
               arrayBolge.add(it.Bolge!!)
            }
        }
        binding.apply {
            val adapter : ArrayAdapter<String> = ArrayAdapter(this@SelectAdressActivity,android.R.layout.simple_list_item_1,arrayBolge)
            spinner.adapter = adapter
        }
    }

    fun veriGonder(view : View){
        binding.apply {
            val positionItem = spinner.selectedItem.toString()
            val intent = Intent(this@SelectAdressActivity, MainActivity::class.java)
            sharedPreferences.edit().putString("kayit",positionItem).apply()
            startActivity(intent)
        }
    }
}
package com.yasinsenel.izmireczaneuygulamasi.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yasinsenel.izmireczaneuygulamasi.adapter.ListItemsAdapter
import com.yasinsenel.izmireczaneuygulamasi.databinding.ActivityMainBinding
import com.yasinsenel.izmireczaneuygulamasi.data.EczaneDataClass
import com.yasinsenel.izmireczaneuygulamasi.data.EczaneDataClassItem
import com.yasinsenel.izmireczaneuygulamasi.service.EczaneAPI
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private var list : ArrayList<EczaneDataClassItem> = arrayListOf()
    private val listItemsAdapter = ListItemsAdapter()
    private val BASE_URL = "https://openapi.izmir.bel.tr/api/"
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        //installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("com.yasinsenel.zmieczaneuygulamas", Context.MODE_PRIVATE)
        val getShared = sharedPreferences.getString("kayit","")
        setRetrofitSettings(getShared)

    }

    fun setRetrofitSettings(getLocation : String?){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EczaneAPI::class.java)
        binding.apply {
            progressBar.solidColor
        }
        val retrofitData = retrofit.getData()

        retrofitData.enqueue(object : Callback<EczaneDataClass>{
            override fun onResponse(
                call: Call<EczaneDataClass>,
                response: Response<EczaneDataClass>
            ) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    binding.apply {
                       responseBody?.forEach {
                            list.add(
                                EczaneDataClassItem(
                                it.Adi,
                                it.Adres,
                                it.Bolge,
                                it.BolgeAciklama,
                                it.BolgeId,
                                it.EczaneId,
                                it.IlceId,
                                it.LokasyonX,
                                it.LokasyonY,
                                it.Tarih,
                                it.Telefon,
                                it.UzaklikMetre)
                            )
                        }
                        progressBar.visibility = View.GONE
                        textLoading.visibility = View.GONE
                        rvList.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                        rvList.adapter = listItemsAdapter
                        listItemsAdapter.fillAdapter(list.filter { it.Bolge == getLocation } as ArrayList<EczaneDataClassItem>)
                        //listItemsAdapter.fillAdapter(list.filter { it.Bolge!!.contains(getLocation)} as ArrayList<EczaneDataClassItem>)
                        textView.text = "${getLocation} bölgesinde bulunan nöbetçi eczaneleri görüntülüyorsunuz."
                    }
                }
            }


            override fun onFailure(call: Call<EczaneDataClass>, t: Throwable) {
                println(t)
            }

        })
    }

    fun locationButton(view : View){
        val intent = Intent(this@MainActivity, SelectAdressActivity::class.java)
        intent.putParcelableArrayListExtra("veri",list)
        startActivity(intent)
    }
}


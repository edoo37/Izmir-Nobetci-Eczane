package com.yasinsenel.izmireczaneuygulamasi.service


import com.yasinsenel.izmireczaneuygulamasi.data.EczaneDataClass
import retrofit2.Call
import retrofit2.http.GET


interface EczaneAPI {
    @GET("ibb/nobetcieczaneler")
    fun getData() : Call<EczaneDataClass>
}
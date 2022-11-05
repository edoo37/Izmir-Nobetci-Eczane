package com.yasinsenel.izmireczaneuygulamasi.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EczaneDataClassItem(
    val Adi: String? = null,
    val Adres: String? = null,
    val Bolge: String? = null,
    val BolgeAciklama: String? = null,
    val BolgeId: Int? = null,
    val EczaneId: Int? = null,
    val IlceId: Int? = null,
    val LokasyonX: String? = null,
    val LokasyonY: String? = null,
    val Tarih: String? = null,
    val Telefon: String? = null,
    val UzaklikMetre: String? =null
) : Parcelable
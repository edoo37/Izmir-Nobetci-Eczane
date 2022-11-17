package com.yasinsenel.izmireczaneuygulamasi.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.yasinsenel.izmireczaneuygulamasi.data.EczaneDataClassItem
import com.yasinsenel.izmireczaneuygulamasi.R
import com.yasinsenel.izmireczaneuygulamasi.databinding.LayoutListItemsBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker

class ListItemsAdapter : RecyclerView.Adapter<ListItemsAdapter.Holder>() {
    private val items : ArrayList<EczaneDataClassItem> = arrayListOf()
    inner class Holder(val binding : LayoutListItemsBinding,val context : Context) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : EczaneDataClassItem){
            binding.apply{

                textName.text = data.Adi
                textAddress.text = data.Adres

                val mapPoint = GeoPoint(data.LokasyonX!!.toDouble(),data.LokasyonY!!.toDouble())
                mapView.setTileSource(TileSourceFactory.MAPNIK)
                val controller = mapView.controller

                controller.animateTo(mapPoint)
                controller.setZoom(19.5)
                val marker = Marker(mapView)
                marker.position = mapPoint
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_TOP)
                marker.icon = ContextCompat.getDrawable(mapView.context,
                    R.drawable.ic_baseline_location_on_24
                )
                marker.title= data.Adi
                mapView.overlays.add(marker)
                mapView.invalidate()
            }
        }

        fun dial(dialData : EczaneDataClassItem){
            binding.apply{
                imgPhone.setOnClickListener {
                    val getPhone = dialData.Telefon
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(getPhone)))
                    context.startActivity(intent)
                }
            }
        }
        fun navigationEcz(navData : EczaneDataClassItem){
            binding.apply {
                imgNavigation.setOnClickListener {
                    val getNavX = navData.LokasyonX
                    val getNavY = navData.LokasyonY
                    val intent = Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=${getNavX},${getNavY}(${navData.Adi})"))
                    intent.setPackage("com.google.android.apps.maps")
                    context.startActivity(intent)
                }
            }
        }
    }

    fun fillAdapter(itemsResponse : ArrayList<EczaneDataClassItem>){
        items.addAll(itemsResponse)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = LayoutListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        Configuration.getInstance().load(parent.context, PreferenceManager.getDefaultSharedPreferences(parent.context))
        return Holder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = items[position]
        holder.bind(data)
        holder.dial(data)
        holder.navigationEcz(data)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
package com.tekydevelop.android.smartoutletiot.presentation.adddevice

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tekydevelop.android.smartoutletiot.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var context: Context? = null
    private val deviceIconTypes = intArrayOf(
        R.drawable.ic_outlet_add,
        R.drawable.ic_lamp_03,
        R.drawable.ic_tv
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.device_pager_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return deviceIconTypes.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context!!)
            .load(deviceIconTypes[position])
            .into(holder.image)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}
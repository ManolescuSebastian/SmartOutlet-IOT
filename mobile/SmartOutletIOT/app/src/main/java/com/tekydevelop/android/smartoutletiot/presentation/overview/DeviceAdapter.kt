package com.tekydevelop.android.smartoutletiot.presentation.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tekydevelop.android.smartoutletiot.R
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData


class DeviceAdapter(
    private val onItemSelectListener: (View, DeviceData) -> Unit,
    private val onItemLongPressListener: (View, DeviceData) -> Unit
) : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    private var context: Context? = null
    private var deviceList: List<DeviceData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.device_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return deviceList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val device = deviceList[position]

        val typeIc : Int = when(device.type){
            0 -> R.drawable.ic_outlet
            1-> R.drawable.ic_lamp_03
            2-> R.drawable.ic_tv
            else -> R.drawable.ic_outlet
        }

        holder.statusIcon.setImageResource(typeIc)

        holder.name.text = device.name
        holder.rootItem.setOnClickListener { view ->
            device.isChecked = !device.isChecked

            if (device.isChecked) {
                holder.statusIcon.setColorFilter(ContextCompat.getColor(context!!, R.color.device_on))
            } else {
                holder.statusIcon.setColorFilter(ContextCompat.getColor(context!!, R.color.device_off))
            }
            onItemSelectListener.invoke(view, device)
        }

        holder.rootItem.setOnLongClickListener { view ->
            onItemLongPressListener.invoke(view, device)
            false
        }
    }


    fun updateDeviceList(deviceList: List<DeviceData>) {
        this.deviceList = deviceList
        notifyDataSetChanged()
    }

    fun clearList(){
        this.deviceList = emptyList()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statusIcon: ImageView = itemView.findViewById(R.id.status_icon)
        val name: TextView = itemView.findViewById(R.id.name)
        val rootItem: ConstraintLayout = itemView.findViewById(R.id.rootItem)
    }

}
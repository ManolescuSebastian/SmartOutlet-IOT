package com.tekydevelop.android.smartoutletiot.presentation.overview.bottom_sheet

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tekydevelop.android.smartoutletiot.R
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.presentation.adddevice.DeviceViewModel
import kotlinx.android.synthetic.main.device_options_bottom_sheet.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class DeviceBottomSheetFragment : BottomSheetDialogFragment() {

    private val viewModel: DeviceViewModel by sharedViewModel()
    private lateinit var device: DeviceData
    private var listener: OnBottomSheetActions? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.device_options_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initEvents()
    }

    fun setOnBottomSheetSelection(listener: OnBottomSheetActions) {
        this.listener = listener
    }

    fun selectedDevice(device: DeviceData) {
        this.device = device
    }

    private fun initData() {
        viewModel.validData.observe(viewLifecycleOwner, Observer {
            if(it == false){
                return@Observer
            }
            listener?.updateList()
            dismiss()
        })
    }

    private fun initEvents() {
        editDevice.setOnClickListener {
            listener?.editDevice(device)
            dismiss()
        }
        deleteDevice.setOnClickListener {
            confirmationDialog()
        }
    }

    private fun confirmationDialog() {
        AlertDialog.Builder(context!!)
            .setTitle(resources.getString(R.string.confirm))
            .setMessage(resources.getString(R.string.confirm_message))
            .setCancelable(false)
            .setPositiveButton(resources.getString(R.string.yes)) { dialog: DialogInterface, _: Int ->
                viewModel.deleteDevice(device.id)
                dialog.dismiss()
            }
            .setNegativeButton(resources.getString(R.string.no)) { dialog: DialogInterface, _: Int ->
                dialog.dismiss()
            }.show()
    }

}
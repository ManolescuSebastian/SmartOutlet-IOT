package com.tekydevelop.android.smartoutletiot.presentation.adddevice

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.tekydevelop.android.smartoutletiot.R
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.utils.Constants.ADD_DEVICE
import kotlinx.android.synthetic.main.add_device_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class AddDeviceActivity : AppCompatActivity() {

    private val viewModel: DeviceViewModel by viewModel()
    private var item: DeviceData? = null
    private var selectedType: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_device_layout)

        item = intent.getParcelableExtra("device")

        onToolbarInit(item == null)
        onDataInit()
        onEvents()

        if (item != null)
            initEditData(item)
    }

    private fun onToolbarInit(isEdit: Boolean) {
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = if (isEdit) {
            resources.getString(R.string.add_device)
        } else {
            resources.getString(R.string.edit_device)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun onDataInit() {
        viewModel.deviceData.observe(this, Observer {
            if (it != null) {
                val returnIntent = Intent()
                returnIntent.putExtra("result", ADD_DEVICE)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        })

        viewModel.error.observe(this, Observer {

        })

        deviceTypeViewPager.adapter = ViewPagerAdapter()
        deviceTypeViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                selectedType = position
            }
        })
    }

    private fun onEvents() {
        saveDevice.setOnClickListener {
            val device = deviceData()
            if (device != null && item == null) {
                viewModel.saveDevice(device)
            } else if (device != null && item != null) {
                viewModel.editDevice(device)
            }
        }
    }

    private fun initEditData(device: DeviceData?) {
        deviceName.append(device?.name)
        uniqueIdentifier.append(device?.uuid.toString())
        saveDevice.text = resources.getString(R.string.update_device)
        topDescription.setText(R.string.update_device_description)
    }

    private fun deviceData(): DeviceData? {
        val name: String = deviceName.text.toString()
        if (name.isEmpty()) {
            deviceNameError.visibility = View.VISIBLE
            return null
        }
        deviceNameError.visibility = View.GONE

        val uuid: String = uniqueIdentifier.text.toString()
        if (uuid.isEmpty()) {
            uniqueIdentifierError.visibility = View.VISIBLE
            return null
        }
        uniqueIdentifierError.visibility = View.GONE

        if (item != null) {
            return DeviceData(item!!.id, uuid.toInt(), name, selectedType)
        } else {
            return DeviceData(-1, uuid.toInt(), name, selectedType)
        }
    }

}
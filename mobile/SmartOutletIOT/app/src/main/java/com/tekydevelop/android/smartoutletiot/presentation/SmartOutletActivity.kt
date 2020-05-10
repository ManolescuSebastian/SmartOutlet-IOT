package com.tekydevelop.android.smartoutletiot.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.tekydevelop.android.smartoutletiot.R
import com.tekydevelop.android.smartoutletiot.domain.model.DeviceData
import com.tekydevelop.android.smartoutletiot.presentation.adddevice.AddDeviceActivity
import com.tekydevelop.android.smartoutletiot.presentation.gateway.GatewayFragment
import com.tekydevelop.android.smartoutletiot.presentation.navigation.OnNavigationListener
import com.tekydevelop.android.smartoutletiot.presentation.navigation.Screen
import com.tekydevelop.android.smartoutletiot.presentation.overview.OverviewFragment
import com.tekydevelop.android.smartoutletiot.presentation.overview.bottom_sheet.DeviceBottomSheetFragment
import com.tekydevelop.android.smartoutletiot.presentation.overview.bottom_sheet.OnBottomSheetActions
import com.tekydevelop.android.smartoutletiot.utils.Constants.ADD_DEVICE
import com.tekydevelop.android.smartoutletiot.utils.Constants.EDIT_DEVICE

class SmartOutletActivity : AppCompatActivity(),
    OnNavigationListener, OnBottomSheetActions {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.smart_outlet_activity)

        onInit()
    }

    private fun onInit() {
        selectFragment(Screen.OVERVIEW)
    }

    override fun selectFragment(fragment: Screen) {
        fragments(fragment)
    }

    private fun fragments(fragment: Screen) {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        val selectedFragment: Fragment = when (fragment) {
            Screen.OVERVIEW -> {
                if (currentFragment is OverviewFragment) {
                    return
                }
                updateActionBar(resources.getString(R.string.overview_title), false)
                OverviewFragment.newInstance()
            }

            Screen.GATEWAY -> {
                if (currentFragment is GatewayFragment) {
                    return
                }

                updateActionBar(resources.getString(R.string.gateway_title), true)
                GatewayFragment.newInstance()
            }
        }

        changeFragment(selectedFragment, supportFragmentManager)
        invalidateOptionsMenu()
    }

    private fun updateActionBar(title: String, showBackButton: Boolean) {
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(showBackButton)
    }

    private fun changeFragment(fragment: Fragment, supportFragmentManager: FragmentManager) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            .replace(R.id.container, fragment)
            .addToBackStack(fragment.tag)
            .commit()
    }

    /**
     * Bottom sheet device edit/delete
     */
    override fun selectDevice(device: DeviceData) {
        displayBottomSheetDeviceOptions(device)
    }

    private fun displayBottomSheetDeviceOptions(device: DeviceData) {
        val bottomSheetFragment = DeviceBottomSheetFragment()
        bottomSheetFragment.selectedDevice(device)
        bottomSheetFragment.setOnBottomSheetSelection(this)
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    override fun updateList() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.container)
        if (currentFragment is OverviewFragment) {
            currentFragment.refreshListData()
        }
    }

    /**
     * Device Add / Edit
     */
    override fun selectActivityAction() {
        startActivityForResult(Intent(this, AddDeviceActivity::class.java), ADD_DEVICE)
    }


    override fun editDevice(device: DeviceData) {
        val intent = Intent(this, AddDeviceActivity::class.java)
        intent.putExtra("device", device)
        startActivityForResult(intent, EDIT_DEVICE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_DEVICE || requestCode == EDIT_DEVICE
            && resultCode == Activity.RESULT_OK
        ) {
            updateList()
        }
    }

    /**
     * Option menu Gateway
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_outlet, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.gateway_item -> selectFragment(Screen.GATEWAY)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.gateway_item)
        item?.isVisible = supportActionBar?.title == resources.getString(R.string.overview_title)
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
            return
        }

        updateActionBar(resources.getString(R.string.overview_title), false)
        invalidateOptionsMenu()

        super.onBackPressed()
    }
}

package com.tekydevelop.android.smartoutletiot.presentation.overview

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tekydevelop.android.smartoutletiot.R
import com.tekydevelop.android.smartoutletiot.data.mapper.HttpCallFailureException
import com.tekydevelop.android.smartoutletiot.data.mapper.NoNetworkException
import com.tekydevelop.android.smartoutletiot.data.mapper.ServerUnreachableException
import com.tekydevelop.android.smartoutletiot.presentation.navigation.OnNavigationListener
import com.tekydevelop.android.smartoutletiot.presentation.navigation.Screen.GATEWAY
import com.tekydevelop.android.smartoutletiot.presentation.overview.bottom_sheet.OnBottomSheetActions
import kotlinx.android.synthetic.main.outlet_overview.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.net.ConnectException

private const val SPAN_COUNT = 2

class OverviewFragment : Fragment() {

    companion object {
        fun newInstance(): OverviewFragment {
            return OverviewFragment()
        }
    }

    private var listener: OnNavigationListener? = null
    private var bottomSheetListener: OnBottomSheetActions? = null
    private var deviceAdapter: DeviceAdapter? = null

    private val viewModel: OverviewViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        require(context is OnNavigationListener) { "Context must implement navigation listener" }
        require(context is OnBottomSheetActions) { "Context must implement bottom sheet listener" }
        listener = context
        bottomSheetListener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.outlet_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onInit()
        onInitVM()
        onEvents()
    }

    private fun onInit() {
        deviceAdapter = DeviceAdapter({ _, device ->
            viewModel.setOutletState(device)
        }, { _, device ->
            bottomSheetListener?.selectDevice(device)
        })
        deviceRecycler.layoutManager = GridLayoutManager(context, SPAN_COUNT)
        deviceRecycler.adapter = deviceAdapter
    }

    private fun onInitVM() {
        viewModel.loadGatewayDevices()
        viewModel.availableDevices.observe(viewLifecycleOwner, Observer {
            deviceAdapter?.updateDeviceList(it)
            swipeRefresh.isRefreshing = false
            gatewayIssueGroup.visibility = View.GONE

            if(it.isEmpty()){
                no_devices.visibility = View.VISIBLE
            }else{
                no_devices.visibility = View.GONE
            }
        })

        viewModel.deviceControl.observe(viewLifecycleOwner, Observer {
            //todo update device color only after response it's received
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            swipeRefresh.isRefreshing = false
            no_devices.visibility = View.GONE
            displayError(it)
        })
    }

    private fun onEvents() {
        addDevice.setOnClickListener {
            listener?.selectActivityAction()
        }

        swipeRefresh.setOnRefreshListener {
            refreshListData()
        }

        searchGateway.setOnClickListener {
            listener?.selectFragment(GATEWAY)
        }
    }

    fun refreshListData() {
        swipeRefresh.isRefreshing = true
        gatewayIssueGroup.visibility = View.GONE
        viewModel.loadGatewayDevices()
    }

    private fun displayError(error: Throwable) {
        when (error) {
            is NoNetworkException -> displayNoNetworkError()
            is ConnectException -> displayConnectionExceptionError()
            is ServerUnreachableException -> displayServerUnreachableError()
            is HttpCallFailureException -> displayCallFailedError()
            else -> displayGenericError(error)
        }
    }

    private fun displaySnackBar(message: String){
        val snack = Snackbar.make(overviewRoot,message,Snackbar.LENGTH_LONG)
        snack.show()
    }

    private fun displayNoNetworkError() {
        displaySnackBar("No internet connection")
    }

    private fun displayConnectionExceptionError() {
        deviceAdapter?.clearList()
        gatewayIssueGroup.visibility = View.VISIBLE
    }

    private fun displayServerUnreachableError() {
        displaySnackBar("Server connection issues")
    }

    private fun displayCallFailedError() {
        displaySnackBar("Device request issues")
    }

    private fun displayGenericError(error: Throwable) {
        displaySnackBar("Something went wrong: " + error.cause)
    }
}
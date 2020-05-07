package com.tekydevelop.android.smartoutletiot.presentation.gateway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.tekydevelop.android.smartoutletiot.R
import kotlinx.android.synthetic.main.gateway_layout.*
import org.koin.android.viewmodel.ext.android.viewModel


class GatewayFragment : Fragment() {

    companion object {
        fun newInstance(): GatewayFragment {
            return GatewayFragment()
        }
    }

    private val viewModel: GatewayViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gateway_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInit()
        onEvent()
    }

    private fun onInit() {
        viewModel.success.observe(viewLifecycleOwner, Observer {
            gatewayInfo.text = resources.getString(R.string.gateway_connection_success)
            gatewayIcon.setColorFilter(ContextCompat.getColor(context!!, R.color.activated))
        })

        viewModel.error.observe(viewLifecycleOwner, Observer {
            gatewayInfo.text = resources.getString(R.string.gateway_connection_failure)
            gatewayIcon.setColorFilter(ContextCompat.getColor(context!!, R.color.deactivated))
        })

    }

    private fun onEvent() {
        checkConnection.setOnClickListener {
            viewModel.checkGateway()
        }
    }

}
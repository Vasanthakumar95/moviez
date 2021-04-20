package com.example.moviez.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import com.example.moviez.R
import com.example.moviez.util.Constants.Companion.WV_URL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_booking.*

@AndroidEntryPoint
class BookingFragment : Fragment(R.layout.fragment_booking) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        load_url(WV_URL)

        ib_booking_back.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun load_url(url : String)
    {
        wv_booking.apply {
            this.settings.javaScriptEnabled = true
            this.settings.setUseWideViewPort(true)
            this.settings.setLoadWithOverviewMode(true)
            this.settings.setSupportZoom(true)
            this.settings.setBuiltInZoomControls(true)

            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }
}
package com.raisehumaneness.digest.ui.dialogs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import com.raisehumaneness.digest.databinding.DialogPhoneBinding
import com.raisehumaneness.digest.ui.base.BaseDialog
import com.raisehumaneness.digest.utils.Constants
import java.util.regex.Matcher
import java.util.regex.Pattern
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class PhoneDialog : BaseDialog<DialogPhoneBinding>(){

    private val requestCall = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setListeners()
        //setupObservers()

        setData()
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): DialogPhoneBinding =
        DialogPhoneBinding.inflate(layoutInflater)

    private fun setListeners(){
    }

    private fun setupObservers(){
    }

    private fun setData(){
        val phones = arguments?.getString(Constants.BUNDLE_PHONE_NUMBERS)?.split("#")

        if(!phones.isNullOrEmpty()) {
            binding.run {
                phone1.text = phones[0]
                phone2.text = phones[1]
                phone3.text = phones[2]
                phone4.text = phones[3]

                if(phones[0].replace("\\s".toRegex(), "").isDigitsOnly()) {
                    call1.setOnClickListener { makePhoneCall(phones[0].replace("\\s".toRegex(), "")) }
                    call1.visibility = View.VISIBLE
                }
                if(phones[1].replace("\\s".toRegex(), "").isDigitsOnly()) {
                    call2.setOnClickListener { makePhoneCall(phones[1].replace("\\s".toRegex(), "")) }
                    call2.visibility = View.VISIBLE
                }
                if(phones[2].replace("\\s".toRegex(), "").isDigitsOnly()) {
                    call3.setOnClickListener { makePhoneCall(phones[2].replace("\\s".toRegex(), "")) }
                    call3.visibility = View.VISIBLE
                }
                if(phones[3].replace("\\s".toRegex(), "").isDigitsOnly()) {
                    call4.setOnClickListener { makePhoneCall(phones[3].replace("\\s".toRegex(), "")) }
                    call4.visibility = View.VISIBLE
                }

            }
        }
    }

    private fun makePhoneCall(number:String) {
        if (number.trim { it <= ' ' }.isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    requestCall
                )
            } else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        }
    }

    private fun call(number: String){
        val intent = Intent(Intent.ACTION_CALL);
        intent.data = Uri.parse("tel:$number")
        startActivity(intent)
    }

    fun getPhones(s: String): ArrayList<String>? {
        val regex = "[0-9]"
        val array = ArrayList<String>()
        val pattern: Pattern = Pattern.compile(regex)
        val matcher: Matcher = pattern.matcher(s)
        while (matcher.find()) {
            array.add(s.substring(matcher.start(), matcher.end()))
        }
        return array
    }
}
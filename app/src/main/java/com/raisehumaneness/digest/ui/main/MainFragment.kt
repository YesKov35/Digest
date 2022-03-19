package com.raisehumaneness.digest.ui.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.text.isDigitsOnly
import androidx.navigation.fragment.findNavController

import com.raisehumaneness.digest.R
import com.raisehumaneness.digest.data.models.PhoneBookModel
import com.raisehumaneness.digest.data.repository.CountryRepository
import com.raisehumaneness.digest.databinding.FragmentMainBinding
import com.raisehumaneness.digest.ui.base.BaseFragment
import com.raisehumaneness.digest.utils.Constants
import com.raisehumaneness.digest.utils.Constants.requestCall

class MainFragment : BaseFragment<FragmentMainBinding>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        //setupObservers()
        setData()
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding =
        FragmentMainBinding.inflate(layoutInflater)

    private fun setListeners(){
        binding.run {
            navCountry.setOnClickListener {
                findNavController().navigate(R.id.digestPhoneBookFragment)
            }

            digestPhoneBook.setOnClickListener {
                findNavController().navigate(R.id.digestPhoneBookFragment)
            }

            digestMain.setOnClickListener {
                findNavController().navigate(R.id.digestListFragment)
            }
        }
    }

    private fun setupObservers(){
    }

    private fun setData(){
        val pos = sharedPrefs.getCountry()

        if(pos >= 0){
            binding.navCountry.visibility = View.GONE
            binding.countryView.visibility = View.VISIBLE
            setCountry(CountryRepository.instance.countryList[pos])
        }
    }

    private fun setCountry(country: PhoneBookModel){
        binding.country.textCountry.text = country.country
        binding.country.addCountry.setOnClickListener {
            findNavController().navigate(R.id.digestPhoneBookFragment)
        }

        binding.country.addCountry.text = getString(R.string.change_country)

        val phones = country.numbers.split("#")

        if(!phones.isNullOrEmpty()) {
            binding.country.run {
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
}
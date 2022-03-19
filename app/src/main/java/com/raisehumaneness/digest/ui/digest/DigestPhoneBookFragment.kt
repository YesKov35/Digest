package com.raisehumaneness.digest.ui.digest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.raisehumaneness.digest.R
import com.raisehumaneness.digest.adapters.DigestPhoneBookAdapter
import com.raisehumaneness.digest.data.models.PhoneBookModel
import com.raisehumaneness.digest.data.repository.CountryRepository
import com.raisehumaneness.digest.databinding.FragmentDigestPhoneBookBinding
import com.raisehumaneness.digest.ui.base.BaseFragment
import com.raisehumaneness.digest.utils.Constants

class DigestPhoneBookFragment : BaseFragment<FragmentDigestPhoneBookBinding>(){

    private lateinit var digestPhoneBookAdapter: DigestPhoneBookAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        //setupObservers()

        initRecycler()
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDigestPhoneBookBinding =
        FragmentDigestPhoneBookBinding.inflate(layoutInflater)

    private fun setListeners(){
        binding.editSearch.addTextChangedListener {
            if(!it.isNullOrEmpty()){
                searchData(it.toString())
            }else{
                digestPhoneBookAdapter.setData(CountryRepository.instance.countryList)
            }
        }
    }

    private fun setupObservers(){
    }

    private fun initRecycler() {
        with(binding) {
            digestPhoneBookAdapter = DigestPhoneBookAdapter().apply {
                listener = {
                    val bundle = Bundle()
                    bundle.putString(Constants.BUNDLE_PHONE_NUMBERS, it.numbers)
                    bundle.putString(Constants.BUNDLE_COUNTRY_NAME, it.country)
                    bundle.putInt(Constants.BUNDLE_COUNTRY_POS, it.position)
                    findNavController().navigate(R.id.phoneDialog, bundle)
                }

                setData(CountryRepository.instance.countryList)
            }

            listDigest.adapter = digestPhoneBookAdapter
        }
    }

    private fun searchData(search: String){
        val data = CountryRepository.instance.countryList

        val dataResult = emptyList<PhoneBookModel>().toMutableList()
        for(item in data)
            if(item.country.lowercase().contains(search))
                dataResult += item

        digestPhoneBookAdapter.setData(dataResult)
    }
}
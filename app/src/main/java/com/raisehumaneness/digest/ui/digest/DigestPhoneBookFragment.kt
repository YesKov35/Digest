package com.raisehumaneness.digest.ui.digest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.raisehumaneness.digest.R
import com.raisehumaneness.digest.adapters.DigestListAdapter
import com.raisehumaneness.digest.adapters.DigestPhoneBookAdapter
import com.raisehumaneness.digest.data.models.DigestModel
import com.raisehumaneness.digest.data.models.PhoneBookModel
import com.raisehumaneness.digest.databinding.FragmentDigestListBinding
import com.raisehumaneness.digest.databinding.FragmentDigestMessageBinding
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
                digestPhoneBookAdapter.setData(getData())
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
                    findNavController().navigate(R.id.phoneDialog, bundle)
                }

                setData(getData())
            }

            listDigest.adapter = digestPhoneBookAdapter
        }
    }

    private fun getData(): List<PhoneBookModel>{
        val dataResult = emptyList<PhoneBookModel>().toMutableList()
        val countryNames = resources.getStringArray(R.array.country_names)
        val phoneBook = resources.getStringArray(R.array.country_phones)

        for(i in countryNames.indices)
            dataResult += PhoneBookModel(countryNames[i], phoneBook[i])

        return dataResult
    }

    private fun searchData(search: String){
        val data = getData()

        val dataResult = emptyList<PhoneBookModel>().toMutableList()
        for(item in data)
            if(item.country.lowercase().contains(search))
                dataResult += item

        digestPhoneBookAdapter.setData(dataResult)
    }
}
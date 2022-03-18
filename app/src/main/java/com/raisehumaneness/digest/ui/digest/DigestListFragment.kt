package com.raisehumaneness.digest.ui.digest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.raisehumaneness.digest.R
import com.raisehumaneness.digest.adapters.DigestListAdapter
import com.raisehumaneness.digest.data.models.DigestModel
import com.raisehumaneness.digest.databinding.FragmentDigestBinding
import com.raisehumaneness.digest.ui.base.BaseFragment
import androidx.navigation.fragment.findNavController
import com.raisehumaneness.digest.databinding.FragmentDigestListBinding

class DigestListFragment : BaseFragment<FragmentDigestListBinding>(){

    private lateinit var digestListAdapter: DigestListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        //setupObservers()

        initRecycler()
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDigestListBinding =
        FragmentDigestListBinding.inflate(layoutInflater)

    private fun setListeners(){
        binding.editSearch.addTextChangedListener {
            if(!it.isNullOrEmpty()){
                searchData(it.toString())
            }else{
                digestListAdapter.setData(getData())
            }
        }
    }

    private fun setupObservers(){
    }

    private fun initRecycler() {
        with(binding) {
            digestListAdapter = DigestListAdapter().apply {
                listener = {
                    val bundle = Bundle()
                    bundle.putInt("digest_id", it.descId)

                    if(it.list)
                        findNavController().navigate(R.id.digestFragment, bundle)
                    else
                        findNavController().navigate(R.id.digestMessageFragment, bundle)
                }

                setData(getData())
            }

            listDigest.adapter = digestListAdapter
        }
    }

    private fun getData() = listOf(
        DigestModel(getString(R.string.digest_title_1), R.array.digest_desc_1),
        DigestModel(getString(R.string.digest_title_2), R.array.digest_desc_2),
        DigestModel(getString(R.string.digest_title_3), R.string.digest_desc_3, false)
    )

    private fun searchData(search: String){
        val data = getData()

        val dataResult = emptyList<DigestModel>().toMutableList()
        for(item in data)
            if(item.title.lowercase().contains(search))
                dataResult += item

        digestListAdapter.setData(dataResult)
    }
}
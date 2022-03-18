package com.raisehumaneness.digest.ui.digest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raisehumaneness.digest.adapters.DigestAdapter
import com.raisehumaneness.digest.databinding.FragmentDigestBinding
import com.raisehumaneness.digest.ui.base.BaseFragment

class DigestFragment : BaseFragment<FragmentDigestBinding>(){

    private lateinit var digestAdapter: DigestAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setListeners()
        //setupObservers()

        initRecycler()
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDigestBinding =
        FragmentDigestBinding.inflate(layoutInflater)

    private fun setListeners(){
    }

    private fun setupObservers(){
    }

    private fun initRecycler() {
        with(binding) {
            digestAdapter = DigestAdapter().apply {
                listener = {
                }

                val digestId = arguments?.getInt("digest_id")

                if(digestId != null)
                    setData(getData(digestId))
            }

            listDigest.adapter = digestAdapter
        }
    }

    private fun getData(id: Int): List<String> = resources.getStringArray(id).toList()
}
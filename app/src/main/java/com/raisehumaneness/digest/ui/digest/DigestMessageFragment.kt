package com.raisehumaneness.digest.ui.digest

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raisehumaneness.digest.R
import com.raisehumaneness.digest.databinding.FragmentDigestMessageBinding
import com.raisehumaneness.digest.ui.base.BaseFragment

class DigestMessageFragment : BaseFragment<FragmentDigestMessageBinding>(){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setListeners()
        //setupObservers()

        setData()
    }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentDigestMessageBinding =
        FragmentDigestMessageBinding.inflate(layoutInflater)

    private fun setListeners(){
    }

    private fun setupObservers(){
    }

    private fun setData(){
        val digestId = arguments?.getInt("digest_id")

        if(digestId != null)
            binding.textDesc.text = getString(digestId)
    }
}
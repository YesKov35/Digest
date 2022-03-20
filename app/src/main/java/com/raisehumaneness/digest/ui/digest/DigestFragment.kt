package com.raisehumaneness.digest.ui.digest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.raisehumaneness.digest.adapters.DigestAdapter
import com.raisehumaneness.digest.data.models.DigestCheckModel
import com.raisehumaneness.digest.databinding.FragmentDigestBinding
import com.raisehumaneness.digest.ui.base.BaseFragment
import java.lang.StringBuilder

class DigestFragment : BaseFragment<FragmentDigestBinding>(){

    private lateinit var digestAdapter: DigestAdapter
    private var checkList = ArrayList<String>()

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

                checkListener = {
                    saveData(it)
                }

                val digestId = arguments?.getInt("digest_id")

                if(digestId != null)
                    setData(getData(digestId))
            }

            listDigest.adapter = digestAdapter
        }
    }

    private fun saveData(digest: DigestCheckModel){
        checkList[digest.position] = digest.check.toString()

        val checkB = StringBuilder()
        for(check in checkList)
            checkB.append("${check}_")

        sharedPrefs.setDigestCheckList(arguments?.getInt("digest_id").toString(), checkB.toString())
    }

    private fun getData(id: Int): List<DigestCheckModel> {
        val digestList = emptyList<DigestCheckModel>().toMutableList()
        val list = resources.getStringArray(id).toList()


        val checkB = StringBuilder()
        if(sharedPrefs.getDigestCheckList(id.toString()).isNullOrEmpty()){
            for(i in list.indices){
                checkB.append("0_")
            }

            sharedPrefs.setDigestCheckList(id.toString(), checkB.toString())
        }

        checkList = ArrayList(sharedPrefs.getDigestCheckList(id.toString())?.split("_")!!)

        for(i in list.indices){
            digestList += DigestCheckModel(list[i], checkList[i].toBoolean(), i)
        }

        return digestList
    }
}
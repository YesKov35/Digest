package com.raisehumaneness.digest.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.raisehumaneness.digest.Application.Companion.prefs

abstract class BaseFragment<B : ViewBinding> : Fragment() {
    private var _viewBinding: B? = null
    protected val binding get() = checkNotNull(_viewBinding)

    protected val sharedPrefs get() = checkNotNull(prefs)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = initBinding(inflater, container)
        return binding.root
    }

    abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}
package com.raisehumaneness.digest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.raisehumaneness.digest.data.models.DigestModel
import com.raisehumaneness.digest.databinding.ItemDigestBinding
import com.raisehumaneness.digest.databinding.ItemDigestCheckBinding

class DigestAdapter : BaseAdapter<String, ItemDigestCheckBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemDigestCheckBinding.inflate(inflater, parent, false)

    //var descListener: ((data: Any) -> Unit)? = null

    override fun bindViewHolder(holder: ViewBindingHolder, data: String) {
        holder.binding {
            data.apply {
                checkDigest.text = data
            }
        }
    }
}
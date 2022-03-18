package com.raisehumaneness.digest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.raisehumaneness.digest.data.models.DigestModel
import com.raisehumaneness.digest.databinding.ItemDigestBinding
import com.raisehumaneness.digest.databinding.ItemDigestCheckBinding

class DigestListAdapter : BaseAdapter<DigestModel, ItemDigestBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemDigestBinding.inflate(inflater, parent, false)

    //var descListener: ((data: Any) -> Unit)? = null

    override fun bindViewHolder(holder: ViewBindingHolder, data: DigestModel) {
        holder.binding {
            data.apply {
                textTitle.text = title
            }
        }
    }
}
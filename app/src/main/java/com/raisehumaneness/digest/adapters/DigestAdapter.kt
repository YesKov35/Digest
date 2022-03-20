package com.raisehumaneness.digest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.raisehumaneness.digest.data.models.DigestCheckModel
import com.raisehumaneness.digest.data.models.DigestModel
import com.raisehumaneness.digest.databinding.ItemDigestBinding
import com.raisehumaneness.digest.databinding.ItemDigestCheckBinding

class DigestAdapter : BaseAdapter<DigestCheckModel, ItemDigestCheckBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemDigestCheckBinding.inflate(inflater, parent, false)

    var checkListener: ((data: DigestCheckModel) -> Unit)? = null

    override fun bindViewHolder(holder: ViewBindingHolder, data: DigestCheckModel) {
        holder.binding {
            data.apply {
                checkDigest.text = data.title
                checkDigest.isChecked = data.check
            }

            checkDigest.setOnClickListener {
                data.check = checkDigest.isChecked
                checkListener?.invoke(data)
            }
        }
    }
}
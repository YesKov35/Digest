package com.raisehumaneness.digest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.raisehumaneness.digest.data.models.PhoneBookModel
import com.raisehumaneness.digest.databinding.ItemDigestBinding

class DigestPhoneBookAdapter : BaseAdapter<PhoneBookModel, ItemDigestBinding>() {
    override fun getBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ) = ItemDigestBinding.inflate(inflater, parent, false)

    //var descListener: ((data: Any) -> Unit)? = null

    override fun bindViewHolder(holder: ViewBindingHolder, data: PhoneBookModel) {
        holder.binding {
            data.apply {
                textTitle.text = country
            }
        }
    }
}
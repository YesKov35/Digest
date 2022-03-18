package com.raisehumaneness.digest.data.models

data class DigestModel(
    var title: String,
    var descId: Int,
    var list: Boolean = true,
    var new: Boolean = false
)
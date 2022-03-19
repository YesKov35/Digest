package com.raisehumaneness.digest.data.repository

import com.raisehumaneness.digest.data.models.PhoneBookModel

class CountryRepository {

    var countryList: List<PhoneBookModel> = emptyList()

    companion object {
        val instance = CountryRepository()
    }
}

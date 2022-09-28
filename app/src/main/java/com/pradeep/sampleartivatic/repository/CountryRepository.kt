package com.pradeep.sampleartivatic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pradeep.sampleartivatic.api.CountryService
import com.pradeep.sampleartivatic.models.Country
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countryService: CountryService) {

    private val countryLiveData = MutableLiveData<Country>()

    val country: LiveData<Country>
        get() = countryLiveData

    suspend fun getCountryDetails() {
        val result = countryService.getCountryDetail()
        if (result.body() != null) {
            countryLiveData.postValue(result.body())
        }
    }
}
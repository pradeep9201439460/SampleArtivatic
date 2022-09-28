package com.pradeep.sampleartivatic.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pradeep.sampleartivatic.Response
import com.pradeep.sampleartivatic.api.CountryService
import com.pradeep.sampleartivatic.models.Country
import java.lang.Exception
import javax.inject.Inject

class CountryRepository @Inject constructor(private val countryService: CountryService) {

    private val countryLiveData = MutableLiveData<Response<Country>>()

    val country: LiveData<Response<Country>>
        get() = countryLiveData

    suspend fun getCountryDetails() {
        try {
            val result = countryService.getCountryDetail()
            if (result.body() != null) {
                countryLiveData.postValue(Response.Success(result.body()))
            }
        } catch (e: Exception) {
            countryLiveData.postValue(Response.Error(e.message.toString()))
        }
    }
}
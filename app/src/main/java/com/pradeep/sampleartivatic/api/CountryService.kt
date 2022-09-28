package com.pradeep.sampleartivatic.api

import com.pradeep.sampleartivatic.models.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryService {

    @GET("c4ab4c1c-9a55-4174-9ed2-cbbe0738eedf")
    suspend fun getCountryDetail(): Response<Country>
}
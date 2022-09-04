package com.baran.cryptocrazyapp.repository

import com.baran.cryptocrazyapp.model.Crypto
import com.baran.cryptocrazyapp.model.CryptoList
import com.baran.cryptocrazyapp.service.CryptoAPI
import com.baran.cryptocrazyapp.util.Constants.API_KEY
import com.baran.cryptocrazyapp.util.Constants.CALL_ATTRIBUTES
import com.baran.cryptocrazyapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api : CryptoAPI
) {

    suspend fun getCryptoList() : Resource<CryptoList> {
        val response = try {
            //api.getCryptoList(API_KEY)
            api.getCryptoList()
        }catch (e : Exception){
            return Resource.Error("Error from API when getList")
        }

        return Resource.Success(response)
    }

    suspend fun getCrypto(id : String) : Resource<Crypto> {
        val response = try {
            api.getCrypto(id, CALL_ATTRIBUTES )
            //api.getCrypto(API_KEY, id, CALL_ATTRIBUTES )
        }catch (e:Exception){
            return Resource.Error("Error from API when getCrypto item")
        }

        return Resource.Success(response)
    }
}
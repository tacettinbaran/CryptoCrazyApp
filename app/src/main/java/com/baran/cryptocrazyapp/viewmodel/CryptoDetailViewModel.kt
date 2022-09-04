package com.baran.cryptocrazyapp.viewmodel

import androidx.lifecycle.ViewModel
import com.baran.cryptocrazyapp.model.Crypto
import com.baran.cryptocrazyapp.repository.CryptoRepository
import com.baran.cryptocrazyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor( private val repository: CryptoRepository)  : ViewModel(){


    /*
    her birisinin ayrı bedelleri var

    1- ya bu fonsiyonu suspend yapıp kalan işlemeleri view içerisinde hallederi
    2- ya da viewModelScope burda açıp işlemleri burada devam ettiririz.
     */
   suspend fun  getCrypto(id : String) : Resource<Crypto>{
        return  repository.getCrypto(id)
    }
}
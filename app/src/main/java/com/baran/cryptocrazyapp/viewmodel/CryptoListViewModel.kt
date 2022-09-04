package com.baran.cryptocrazyapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baran.cryptocrazyapp.model.CryptoListItem
import com.baran.cryptocrazyapp.repository.CryptoRepository
import com.baran.cryptocrazyapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(private val repository: CryptoRepository) : ViewModel() {

    var cryptoList = mutableStateOf<List<CryptoListItem>>( listOf())
    var errorMessage = mutableStateOf("")
    var isLoading =mutableStateOf(false)

    private var initialCryptoList = listOf<CryptoListItem>()
    private var isSeracrhStarting = true
    init {
        loadCryptos()
    }

    fun serarchCryptoList(query : String){

        val  listToSearch = if (isSeracrhStarting){
            cryptoList.value
        }else{
            initialCryptoList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()){
                cryptoList.value = initialCryptoList
                isSeracrhStarting = true
                return@launch
            }
            val  results = listToSearch.filter {
                it.currency.contains(query.trim(), ignoreCase = true)
            }

            if (isSeracrhStarting){
                initialCryptoList = cryptoList.value
                isSeracrhStarting = false
            }
            cryptoList.value = results
        }
    }

    /*
    her birisinin ayrı bedelleri var

    1- ya bu fonsiyonu suspend yapıp kalan işlemeleri view içerisinde hallederi
    2- ya da viewModelScope burda açıp işlemleri burada devam ettiririz.
     */
    fun loadCryptos(){

        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCryptoList()
            Log.d("mesaj_result", result.data.toString())
            when(result) {
                is Resource.Success->{
                    val cryptoItems = result.data!!.mapIndexed{index, cryptoListItem ->
                        CryptoListItem(cryptoListItem.currency, cryptoListItem.price)
                    } as List<CryptoListItem>

                    errorMessage.value = ""
                    isLoading.value = false
                    cryptoList.value += cryptoItems

                }

                is Resource.Error->{
                    errorMessage.value = result.message!!
                    isLoading.value = false
                }

                /*
                is Resource.Loading->{
                    //bunu sonra ele alacaz
                }
                 */

            }
        }

    }
}
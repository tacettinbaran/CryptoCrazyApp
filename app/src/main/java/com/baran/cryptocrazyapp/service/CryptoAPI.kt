package com.baran.cryptocrazyapp.service

import com.baran.cryptocrazyapp.model.Crypto
import com.baran.cryptocrazyapp.model.CryptoItem
import com.baran.cryptocrazyapp.model.CryptoList
import com.baran.cryptocrazyapp.model.CryptoListItem
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoAPI {

    /*
    https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json
    https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/main/crypto.json
     */

    //https://api.nomics.com/v1/prices?key=9ab3c6b3d426159a1fc70e4761210acb438643f8
    //https://api.nomics.com/v1/prices?key=<Kendi API Anahtarınız>
    //https://api.nomics.com/v1/currencies?key=<Kendi API Anahtarınız>&ids=BTC&attributes=id,name,logo_url

    @GET("cryptolist.json")
    suspend fun getCryptoList(): CryptoList  //List<CryptoListItem>

    @GET("crypto.json")
    suspend fun getCrypto(
        @Query("ids") ids: String,
        @Query("attributes") attributes: String
    ) : Crypto //List<CryptoItem>

    /*
     @GET("prices")
    suspend fun getCryptoList(
        @Query("key") key: String
    ): CryptoList  //List<CryptoListItem>

    @GET("currencies")
    suspend fun getCrypto(
        @Query("key") key: String,
        @Query("ids") ids: String,
        @Query("attributes") attributes: String
    ) : Crypto //List<CryptoItem>
     */
}
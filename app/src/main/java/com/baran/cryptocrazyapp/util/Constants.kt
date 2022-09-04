package com.baran.cryptocrazyapp.util

object Constants {

    /*
    https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json
    https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/main/crypto.json
     */

    //Kendi API Anahtarım
    // 9ab3c6b3d426159a1fc70e4761210acb438643f8
    //https://api.nomics.com/v1/prices?key=<Kendi API Anahtarınız>
    const val BASE_URL = "https://raw.githubusercontent.com/atilsamancioglu/IA32-CryptoComposeData/main/"
    const val API_KEY = "0d0ea68d089c65d6160289437a50c0ae66ae1ae9"
    const val CALL_ATTRIBUTES = "id, name, logo_url"

    /*
    const val BASE_URL = "https://api.nomics.com/v1/"
    const val API_KEY = "0d0ea68d089c65d6160289437a50c0ae66ae1ae9"
    const val CALL_ATTRIBUTES = "id, name, logo_url"
     */

    //ikinci istek
    //https://api.nomics.com/v1/currencies?key=<Kendi API Anahtarınız>&ids=BTC&attributes=id,name,logo_url
}
package com.baran.cryptocrazyapp.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.baran.cryptocrazyapp.model.Crypto
import com.baran.cryptocrazyapp.util.Resource
import com.baran.cryptocrazyapp.viewmodel.CryptoDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async
import kotlin.coroutines.CoroutineContext


@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {
    /*
        //Step 1 -> Wrong
        val scope = rememberCoroutineScope()

        var cryptoItem by remember {
            mutableStateOf<Resource<Crypto>>(Resource.Loading())
        }
        /*
        tam bu noktada düzgün çalışmıyor.
        İstek sürekli atılıyor. Datalar bir gösteriliyor bir gösterilmiyor.
        Composable'lar SideEffect'lerden bağımsız olması lazım.
        rememberCoroutineScope() bunu ancak kullanıcı etkisinde olan durumlarda kullanabiliriz.
        Örneğin butona tıklandığında.
         */
        scope.launch {
            cryptoItem = viewModel.getCrypto(id)
            Log.d("mesaj_cryptoItem", cryptoItem.data.toString())
        }
    */

    /*
        //Step 2 -> Better
        // LaunchedEffect(true) while(true) ile aynı işlevi görür dikkat etmek lazım
        var cryptoItem by remember {
            mutableStateOf<Resource<Crypto>>(Resource.Loading())
        }
        LaunchedEffect(key1 = Unit){
            cryptoItem = viewModel.getCrypto(id)
            Log.d("mesaj_cryptoItem", cryptoItem.data.toString())
        }
    */

    //Step 3 -> Best
    /*
    val cryptoItem by produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
    }
     */
    val cryptoItem = produceState<Resource<Crypto>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
        Log.d("mesaj_value", value.data.toString())
    }.value

    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //bunu yapamayız suspend fun değil!!!
            //viewModel.getCrypto(id)

            when (cryptoItem) {

                is Resource.Success -> {
                    val selectedCrypto = cryptoItem.data!![0]
                    Log.d("mesaj_selectedCrypto", selectedCrypto.toString())
                    Text(
                        text = selectedCrypto.name,
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primary,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        //COIL IMAGE COMPOSE
                        painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier.padding(10.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.Gray, CircleShape)
                    )

                    Text(
                        text = price,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center
                    )
                }

                is Resource.Error -> {
                    Text(text = cryptoItem.message!!)
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary)
                }
            }
        }
    }
}
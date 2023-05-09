package com.aplicacion.thecatsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplicacion.thecatsapp.data.CatRepository
import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.domain.GetCatsUseCase
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    // Expose screen UI product
    private var catRepository: CatRepository? = null
    var getCatsUseCase: GetCatsUseCase? = null
    val catLiveData = MutableLiveData<ArrayList<Cat>>()
    val errorCode: MutableLiveData<Int?> get() = _errorCode
    private val _errorCode = MutableLiveData<Int?>()
    val loading = MutableLiveData<Boolean>()

    /**
     * init Repository
     */
    init {
        catRepository = CatRepository()
        getCatsUseCase = GetCatsUseCase(catRepository!!)
    }

    /**
     * get cats
     */
    fun getCats() {
        viewModelScope.launch {
            val response = getCatsUseCase!!()
            if (response.httpCode == HttpURLConnection.HTTP_OK) {
                catLiveData.postValue(response.body!!)
                loading.value = false
                _errorCode.value = null
            } else {
                _errorCode.value = response.httpCode
                loading.value = false
            }
        }
    }

}
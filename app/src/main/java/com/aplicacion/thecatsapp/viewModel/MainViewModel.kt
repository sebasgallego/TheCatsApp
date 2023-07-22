package com.aplicacion.thecatsapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aplicacion.thecatsapp.data.CatRepository
import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.domain.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase
) : ViewModel() {
    // Expose screen UI product
    val catLiveData = MutableLiveData<ArrayList<Cat>>()
    val errorCode: MutableLiveData<Int?> get() = _errorCode
    private val _errorCode = MutableLiveData<Int?>()
    val loading = MutableLiveData<Boolean>()

    /**
     * get cats
     */
    fun getCats() {
        viewModelScope.launch {
            loading.value = true
            val response = getCatsUseCase()
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
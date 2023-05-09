package com.aplicacion.thecatsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.aplicacion.thecatsapp.data.model.Cat
import com.aplicacion.thecatsapp.data.network.ApiResponse
import com.aplicacion.thecatsapp.domain.GetCatsUseCase
import com.aplicacion.thecatsapp.viewModel.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection


@ExperimentalCoroutinesApi
class MainViewModelUnitTest{

    @RelaxedMockK
    private lateinit var getCatsUseCase: GetCatsUseCase

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel()
        mainViewModel.getCatsUseCase = getCatsUseCase
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getCatsUseCase return any breeds set on the livedata`() = runTest {
        //Given
        val cat = Cat()
        cat.name = "cat 1"
        cat.origin = "Colombia"
        val products = arrayListOf(cat, cat)
        val response = ApiResponse<ArrayList<Cat>>()
        response.httpCode = HttpURLConnection.HTTP_OK
        response.body = ArrayList(products)
        coEvery { getCatsUseCase() } returns response
        //When
        mainViewModel.getCats()
        //Then
        assert(mainViewModel.catLiveData.value == response.body)
    }

      @Test
      fun `when getCatsUseCase return HTTP_OK and empty list`() = runTest {
          //Given
          val response = ApiResponse<ArrayList<Cat>>()
          response.httpCode = HttpURLConnection.HTTP_OK
          response.body = ArrayList(ArrayList())
          coEvery { getCatsUseCase() } returns response
          //When
          mainViewModel.getCats()
          //Then
          assert(mainViewModel.catLiveData.value!!.size == response.body!!.size)
      }

      @Test
      fun `when getCatsUseCase return HTTP_OK and two size list`() = runTest {
          //Given
          val cat = Cat()
          cat.name = "cat 1"
          cat.origin = "Colombia"
          val products = arrayListOf(cat, cat)
          val response = ApiResponse<ArrayList<Cat>>()
          response.httpCode = HttpURLConnection.HTTP_OK
          response.body = ArrayList<Cat>(products)
          coEvery { getCatsUseCase() } returns response
          //When
          mainViewModel.getCats()
          //Then
          assert(mainViewModel.catLiveData.value!!.size == response.body!!.size)
      }

}
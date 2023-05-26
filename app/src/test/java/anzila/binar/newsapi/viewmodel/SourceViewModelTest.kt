package anzila.binar.newsapi.viewmodel

import anzila.binar.newsapi.model.ResponseDataSource
import anzila.binar.newsapi.model.list.ResponseDataList
import anzila.binar.newsapi.network.RestfulApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class SourceViewModelTest{
    lateinit var service : RestfulApi

    @Before
    fun setUp(){
        service  = mockk()
    }

    @Test
    fun testRetriveData(): Unit = runBlocking {
        val responseRetrive = mockk<Call<ResponseDataSource>>()

        every {
            runBlocking {
                service.getAllSources("")
            }
        } returns responseRetrive
        val result = service.getAllSources("")

        verify {
            runBlocking {
                service.getAllSources("")
            }
        }
        assertEquals(result, responseRetrive)
    }

    @Test
    fun testRetriveDataDua(): Unit = runBlocking {
        val responseRetriveDua = mockk<Call<ResponseDataList>>()

        every {
            runBlocking {
                service.getAllList("")
            }
        } returns responseRetriveDua
        val result = service.getAllList("")

        verify {
            runBlocking {
                service.getAllList("")
            }
        }
        assertEquals(result, responseRetriveDua)
    }
}
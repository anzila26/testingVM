package anzila.binar.newsapi.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import anzila.binar.newsapi.model.ResponseDataSource
import anzila.binar.newsapi.model.Source
import anzila.binar.newsapi.network.RestfulApi
import anzila.binar.newsapi.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(var api : RestfulApi) : ViewModel() {

    lateinit var liveDataSource : MutableLiveData<List<Source>?>

    init {
        liveDataSource = MutableLiveData()
    }

    fun getDataSource(): MutableLiveData<List<Source>?> {
        return  liveDataSource
    }

    fun callApiSource(category : String){
        api.getAllSources(category)
            .enqueue(object : Callback<ResponseDataSource>{
                override fun onResponse(
                    call: Call<ResponseDataSource>,
                    response: Response<ResponseDataSource>
                ) {
                    if (response.isSuccessful){
                        liveDataSource.postValue(response.body()!!.sources)
                        Log.d(ContentValues.TAG, "onResponse: ${response.body()!!.sources.size}")
                    }else{
                        liveDataSource.postValue(null)
                    }
                }

                override fun onFailure(call: Call<ResponseDataSource>, t: Throwable) {
                    liveDataSource.postValue(null)
                }
            })
    }

}
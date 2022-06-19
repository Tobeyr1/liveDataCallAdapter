package com.tobery.livedata.call.adapter

import androidx.lifecycle.LiveData
import com.tobery.livedata.call.livedatalib.ApiResponse
import com.tobery.livedata.call.livedatalib.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GitHubService {

    @GET("banner")
    fun getBanner(@Query("type")type:Int): LiveData<ApiResponse<banner_bean>>

    companion object{
   //     private const val BASE_URL = "https://api.github.com/"
      private const val BASE_URL = "http://121.5.194.180:3030/"

        fun create(): GitHubService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(initOkHttp())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
        }

        private fun initOkHttp(): OkHttpClient? {
            return OkHttpClient().newBuilder()
                .readTimeout(15, TimeUnit.SECONDS) //设置读取超时时间
                .connectTimeout(15, TimeUnit.SECONDS) //设置请求超时时间
                .writeTimeout(15, TimeUnit.SECONDS) //设置写入超时时间
                .retryOnConnectionFailure(true) //设置出错进行重新连接
                .build()
        }
    }
}
package com.tobey.pagger3study

import androidx.lifecycle.LiveData
import com.tobey.pagger3study.News.NewsReponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GitHubService {
    @GET("search/repositories?sort=stars&q=Android")
    suspend fun searchRepos(@Query("page")page:Int,@Query("per_page") perPage: Int): RepoResponse

    @POST("news/newsClassify")
    suspend fun searchArties(@Query("type")type:String,@Query("pageNum")pageNum:Int,@Query("pageSize")pageSize:Int): NewsReponse

    @POST("news/bannerSelect")
    suspend fun queryBanner()

    @GET("banner")
    fun getBanner(@Query("type")type:Int): LiveData<ApiResponse<banner_bean>>

    companion object{
   //     private const val BASE_URL = "https://api.github.com/"
      private const val BASE_URL = "http://121.5.194.180:3030/"

        fun create(): GitHubService{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(initOkHttp())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                //.addConverterFactory(LiveDataResponseBodyConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubService::class.java)
        }

        private fun initOkHttp(): OkHttpClient? {
            return OkHttpClient().newBuilder()
                .readTimeout(15, TimeUnit.SECONDS) //设置读取超时时间
                .connectTimeout(15, TimeUnit.SECONDS) //设置请求超时时间
                .writeTimeout(15, TimeUnit.SECONDS) //设置写入超时时间
                .addInterceptor(LogInterceptor()) //添加打印拦截器
                .retryOnConnectionFailure(true) //设置出错进行重新连接
                .build()
        }
    }
}
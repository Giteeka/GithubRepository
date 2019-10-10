package com.app.gitrepository.data.network

import com.app.gitrepository.BuildConfig
import com.app.gitrepository.data.model.Repository
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

/**
 *
 * interface Api services for Retrofit
 */
interface ApiService {

    @GET("repositories")
    fun getRepositories(): Single<List<Repository>>


    companion object {

        fun create(): ApiService {

            fun createDefaultOkHttpClient(): OkHttpClient {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                return OkHttpClient().newBuilder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()
            }

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(createDefaultOkHttpClient())
                .build()

            return retrofit.create(ApiService::class.java)


        }
    }


}
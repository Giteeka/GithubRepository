package com.app.gitrepository.data.remote

import android.util.Log
import com.hkuaapp.data.local.prefs.PreferenceHelper
import com.hkuaapp.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class RequestInterceptor : Interceptor {
    var preferenceHelper : PreferenceHelper ? = null
    @Inject
    constructor(preferenceHelper: PreferenceHelper){
       this.preferenceHelper = preferenceHelper
    }
  /*  private var requestHeaders: RequestHeader? = null
    init{
        this.requestHeaders = requestHeaders
    }*/

  /*  private var token : String? = ""
    fun setAccessToken(accessToken : String?){
        token = accessToken
    }*/

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = preferenceHelper?.getAccessToken()
        Log.d("**token is : "," "+token?.toString())



        val builder = if(token.isNullOrEmpty())  original.newBuilder()?.header("Content-Type",AppConstants.FORM)
        else original.newBuilder() ?.header("Authorization","Bearer "+token)
                ?.header("Content-Type",AppConstants.FORM)


      /*  val  builder= original.newBuilder()
                ?.header("Authorization","Bearer "+AppConstants.TOKEN)
                ?.header("Content-Type",AppConstants.FORM)*/



        val newRequest = builder.build()

        return chain.proceed(newRequest)
    }
}
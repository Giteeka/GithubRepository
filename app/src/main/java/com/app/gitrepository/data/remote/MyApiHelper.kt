package com.app.gitrepository.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class MyApiHelper : ApiHelper {
    override fun fetchQrCodeUrl(parameters: HashMap<String, String>): Single<ResponseQR>? {
        return service?.fetchQRCodeUrl(parameters)
    }

    override fun downloadPdfFile(url: String, file: File): Completable {

        val call: Call<ResponseBody>? = service?.downloadPdfFile(url)
        return Completable.create { emitter ->
            call?.enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    emitter.onError(t)
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val isWrittenDOne =
                            FileHelper.writeFile(file, response.body()?.byteStream(), false)
                    if (isWrittenDOne) {
                        emitter.onComplete()
                    }
                }
            })
        }
    }

    override fun fetchDocumentList(parameters: HashMap<String, String>): Single<ResponseDocument>? {
        return service?.fetchDocumentList(parameters)
    }

    override fun fetchAboutus(): Single<ResponseAboutus>? {
        return service?.fetchAboutUs()
    }

    override fun fetchAdvertisement(): Single<ResponseAdvertisement>? {
        return service?.fetchAdvertisement()
    }

    override fun fetchNursingCategory(): Single<ResponseNursingCategory>? {
        return service?.fetchNursingCategory()
    }

    override fun fetchNursingById(id: Int): Single<ResponseNursing>? {
        return service?.fetchNursingById(id)
    }


    private var service: ApiService? = null

    @Inject
    constructor(service: ApiService) {
        this.service = service
    }


    override fun loginUser(parameters: HashMap<String, String>): Single<ResponseLogin>? {
        return service?.loginUser(parameters)
    }

    override fun fetchHomeScreenData(): Single<ResponseHome>? {
        return service?.fetchHomeScreenData()
    }

    override fun fetchEvents(parameters: HashMap<String, String>): Single<ResponseEvent>? {
        return service?.fetchEvents(parameters)
    }

    override fun fetchEventDetail(parameters: HashMap<String, String>): Single<ResponseEventDetail>? {
        return service?.fetchEventDetail(parameters)
    }

    override fun attendEvent(parameters: HashMap<String, String>): Single<BaseApiResponse>? {
        return service?.attendEvent(parameters)
    }

    override fun submitAnswer(parameters: HashMap<String, String>): Single<BaseApiResponse>? {
        return service?.submitAnswer(parameters)
    }

    override fun fetchUserEvents(parameters: HashMap<String, String>): Single<ResponseUserEvent>? {
        return service?.fetchUserEvents(parameters)
    }


    override fun fetchDocumentCategory(): Single<ResponseDocumentCategory>? {
        return service?.fetchDocumentCategory()
    }

    override fun getApiHeader(): ApiHeader {
        TODO("implement later")
    }

    override fun fetchLinks(parameters: HashMap<String, String>): Single<ResponseLink>? {
        return service?.fetchLinks(parameters)
    }
}
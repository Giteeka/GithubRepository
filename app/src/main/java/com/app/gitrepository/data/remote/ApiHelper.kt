package com.app.gitrepository.data.remote

import java.io.File

interface ApiHelper {

    fun getApiHeader(): ApiHeader

    fun fetchEvents(parameters: HashMap<String, String>): Single<ResponseEvent>?

    fun loginUser(parameters: HashMap<String, String>): Single<ResponseLogin>?

    fun fetchEventDetail(parameters: HashMap<String, String>): Single<ResponseEventDetail>?

    fun attendEvent(parameters: HashMap<String, String>): Single<BaseApiResponse>?

    fun submitAnswer(parameters: HashMap<String, String>): Single<BaseApiResponse>?

    fun fetchUserEvents(parameters: HashMap<String, String>): Single<ResponseUserEvent>?

    fun fetchHomeScreenData(): Single<ResponseHome>?

    fun fetchDocumentCategory(): Single<ResponseDocumentCategory>?

    fun fetchLinks(parameters: HashMap<String, String>): Single<ResponseLink>?

    fun fetchNursingCategory(): Single<ResponseNursingCategory> ?

    fun fetchNursingById(id: Int) : Single<ResponseNursing> ?

    fun fetchAboutus() : Single<ResponseAboutus> ?

    fun fetchAdvertisement() : Single<ResponseAdvertisement> ?

    fun fetchDocumentList(parameters: HashMap<String, String>) : Single<ResponseDocument> ?


    fun downloadPdfFile(url : String,file : File) : Completable

    fun fetchQrCodeUrl(parameters: HashMap<String, String>) : Single<ResponseQR> ?
}
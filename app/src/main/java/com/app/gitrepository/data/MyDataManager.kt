package com.hkuaapp.data

import android.content.Context
import com.google.gson.Gson
import com.hkuaapp.data.local.db.DbHelper
import com.hkuaapp.data.local.prefs.PreferenceHelper
import com.hkuaapp.data.remote.ApiHeader
import com.app.gitrepository.data.remote.ApiHelper
import com.hkuaapp.modal.BaseApiResponse
import com.hkuaapp.modal.about.ResponseAboutus
import com.hkuaapp.modal.advertisement.ResponseAdvertisement
import com.hkuaapp.modal.document.DocumentCategory
import com.hkuaapp.modal.document.ResponseDocument
import com.hkuaapp.modal.document.ResponseDocumentCategory
import com.hkuaapp.modal.events.EventInfo
import com.hkuaapp.modal.events.ResponseEvent
import com.hkuaapp.modal.evnetdetail.ResponseEventDetail
import com.hkuaapp.modal.home.Document
import com.hkuaapp.modal.home.ResponseHome
import com.hkuaapp.modal.home.Slider
import com.hkuaapp.modal.link.ResponseLink
import com.hkuaapp.modal.login.ResponseLogin
import com.hkuaapp.modal.login.UserDetail
import com.hkuaapp.modal.nursing.NursingCategory
import com.hkuaapp.modal.nursing.NursingInfo
import com.hkuaapp.modal.nursing.ResponseNursing
import com.hkuaapp.modal.nursing.ResponseNursingCategory
import com.hkuaapp.modal.qrcode.ResponseQR
import com.hkuaapp.modal.qrcode.ResponseUserEvent
import io.reactivex.Completable
import io.reactivex.Single
import java.io.File
import javax.inject.Inject

class MyDataManager : DataManager {
    override fun clearAllPrefs() {
        mPreferencesHelper.clearAllPrefs()
    }

    override fun fetchQrCodeUrl(parameters: HashMap<String, String>): Single<ResponseQR>? {
        return mApiHelper.fetchQrCodeUrl(parameters)
    }

    override fun downloadPdfFile(url: String,file : File): Completable {
        return mApiHelper.downloadPdfFile(url,file)
    }

    override fun saveQrCodeText(eventId: Int,strQrCode: String?) {
        mPreferencesHelper.saveQrCodeText(eventId,strQrCode)
    }

    override fun getQrCodeText(eventId: Int): String? {
        return mPreferencesHelper.getQrCodeText(eventId)
    }

    override fun setCurrentUserCategoryId(categoryId: Long?) {
        mPreferencesHelper.setCurrentUserCategoryId(categoryId)
    }

    override fun getCurrentUserCategoryId(): Long? {
        return mPreferencesHelper.getCurrentUserCategoryId()
    }

    override fun getDocumentList(id: Int): Single<List<Document>> {
        return mDbHelper.getDocumentList(id)
    }

    override fun fetchDocumentList(parameters: HashMap<String, String>): Single<ResponseDocument>? {
        return mApiHelper.fetchDocumentList(parameters)
    }

    override fun getAdvertiseResponse(): String? {
        return mPreferencesHelper.getAdvertiseResponse()
    }

    override fun saverAdvertisement(responseAdvertise: String?) {
        mPreferencesHelper.saverAdvertisement(responseAdvertise)
    }

    override fun saveAboutus(responseAbout: String?) {
        mPreferencesHelper.saveAboutus(responseAbout)
    }

    override fun getAbouts(): String? {
        return mPreferencesHelper.getAbouts()
    }

    override fun fetchAboutus(): Single<ResponseAboutus>? {
        return mApiHelper.fetchAboutus()
    }

    override fun fetchAdvertisement(): Single<ResponseAdvertisement>? {
        return mApiHelper.fetchAdvertisement()
    }

    override fun fetchNursingCategory(): Single<ResponseNursingCategory>? {
        return mApiHelper.fetchNursingCategory()
    }

    override fun fetchNursingById(id: Int): Single<ResponseNursing>? {
        return mApiHelper.fetchNursingById(id)
    }

    override fun getNursing(id: Int): Single<NursingInfo> {
        return mDbHelper.getNursing(id)
    }

    override fun saveNursing(nursingInfo: NursingInfo) {
        mDbHelper.saveNursing(nursingInfo)
    }

    override fun saveNursingCategory(nursingCategory: List<NursingCategory>) {
        mDbHelper.saveNursingCategory(nursingCategory)
    }

    override fun getNursingCategory(): Single<List<NursingCategory>> {
        return mDbHelper.getNursingCategory()
    }


    override fun getLinkResponse(): String? {
        return mPreferencesHelper.getLinkResponse()
    }

    override fun saveLinkResponse(responseLink: String?) {
        mPreferencesHelper.saveLinkResponse(responseLink)
    }

    override fun fetchLinks(parameters: HashMap<String, String>): Single<ResponseLink>? {
        return mApiHelper.fetchLinks(parameters)
    }

    override fun getEventById(eventID: Int): Single<EventInfo> {
        return mDbHelper.getEventById(eventID)
    }

    override fun updateEvent(eventInfo: EventInfo) {
        mDbHelper.updateEvent(eventInfo)
    }

    override fun getEvents(categoryID: Int, eventType: Int): Single<List<EventInfo>> {
        return mDbHelper.getEvents(categoryID, eventType)
    }


    var mContext: Context
    var mApiHelper: ApiHelper
    var mPreferencesHelper: PreferenceHelper
    var mGson: Gson
    var mDbHelper: DbHelper

    @Inject
    constructor(context: Context, apiHelper: ApiHelper, preferencesHelper: PreferenceHelper,
                gson: Gson, dbHelper: DbHelper) {

        this.mContext = context
        this.mApiHelper = apiHelper
        this.mPreferencesHelper = preferencesHelper
        this.mGson = gson
        this.mDbHelper = dbHelper
    }

    override fun getUserDetail(): Single<UserDetail> {

        return mDbHelper.getUserDetail()
    }

    override fun saveUserDetail(userDetail: UserDetail) {
        mDbHelper.saveUserDetail(userDetail)
    }

    override fun getFcmToken(): String? {
        return mPreferencesHelper.getFcmToken()
    }

    override fun setFCMToken(accessToken: String?) {
        mPreferencesHelper.setFCMToken(accessToken)
    }


    override fun fetchHomeScreenData(): Single<ResponseHome>? {
        return mApiHelper.fetchHomeScreenData()
    }

    override fun loginUser(parameters: HashMap<String, String>): Single<ResponseLogin>? {
        return mApiHelper.loginUser(parameters)
    }

    override fun fetchEvents(parameters: HashMap<String, String>): Single<ResponseEvent>? {
        return mApiHelper.fetchEvents(parameters)
    }

    override fun fetchEventDetail(parameters: HashMap<String, String>): Single<ResponseEventDetail>? {
        return mApiHelper.fetchEventDetail(parameters)
    }


    override fun attendEvent(parameters: HashMap<String, String>): Single<BaseApiResponse>? {
        return mApiHelper.attendEvent(parameters)
    }

    override fun submitAnswer(parameters: HashMap<String, String>): Single<BaseApiResponse>? {
        return mApiHelper.submitAnswer(parameters)
    }

    override fun fetchUserEvents(parameters: HashMap<String, String>): Single<ResponseUserEvent>? {
        return mApiHelper.fetchUserEvents(parameters)
    }


    override fun setCurrentUserId(userId: Long?) {
        mPreferencesHelper.setCurrentUserId(userId)
    }

    override fun setFontPreference(fontPreference: Int) {
        mPreferencesHelper.setFontPreference(fontPreference)
    }


    override fun saveSliders(sliders: List<Slider>) {
        mDbHelper.saveSliders(sliders)
    }

    override fun getSliderList(): Single<List<Slider>> {
        return mDbHelper.getSliderList()
    }

    override fun saveDocumentList(documents: List<Document>) {
        mDbHelper.saveDocumentList(documents)
    }

    override fun getDocumentList(): Single<List<Document>> {
        return mDbHelper.getDocumentList()
    }

    override fun saveEvents(eventList: List<EventInfo>) {
        mDbHelper.saveEvents(eventList)
    }

    override fun getEvents(): Single<List<EventInfo>> {
        return mDbHelper.getEvents()
    }

    override fun fetchDocumentCategory(): Single<ResponseDocumentCategory>? {
        return mApiHelper.fetchDocumentCategory()
    }

    override fun saveDocumentCategoryList(documentCategories: List<DocumentCategory>) {
        mDbHelper.saveDocumentCategoryList(documentCategories)
    }

    override fun getDocumentCategories(): Single<List<DocumentCategory>> {
        return mDbHelper.getDocumentCategories()
    }

    override fun getFontPreference(): Int {
        return mPreferencesHelper.getFontPreference()
    }

    override fun setUserAsLoggedOut() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAccessToken(): String? {
        return mPreferencesHelper.getAccessToken()
    }

    override fun setAccessToken(accessToken: String?) {
        mPreferencesHelper.setAccessToken(accessToken)
    }

    override fun getCurrentUserEmail(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentUserName(): String? {
        return mPreferencesHelper.getCurrentUserName()
    }

    override fun setCurrentUserName(userName: String?) {
        mPreferencesHelper.setCurrentUserName(userName)
    }

    override fun getApiHeader(): ApiHeader {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun setCurrentUserEmail(email: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCurrentUserId(): Long? {
        return mPreferencesHelper.getCurrentUserId()
    }

    override fun updateApiHeader(userId: Long?, accessToken: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
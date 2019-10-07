package com.hkuaapp.data.local.db

import com.hkuaapp.modal.document.DocumentCategory
import com.hkuaapp.modal.home.Document
import com.hkuaapp.modal.events.EventInfo
import com.hkuaapp.modal.home.Slider
import com.hkuaapp.modal.login.UserDetail
import com.hkuaapp.modal.nursing.NursingCategory
import com.hkuaapp.modal.nursing.NursingInfo
import io.reactivex.Single
import javax.inject.Inject

class AppDbHelper @Inject constructor(private val mAppDatabase: AppDatabase) : DbHelper {
    override fun getDocumentList(id: Int): Single<List<Document>> {
      return  mAppDatabase.documentDao().fetchDocumentList(id)
    }

    override fun saveNursing(nursingInfo: NursingInfo) {
      mAppDatabase.nursingDao().insertNursing(nursingInfo)
    }

    override fun getNursing(id: Int): Single<NursingInfo> {
            return  mAppDatabase.nursingDao().fetchNursing(id)
    }

    override fun getNursingCategory(): Single<List<NursingCategory>> {
        return mAppDatabase.nursingCategoryDao().fetchNursingCategoryList()
    }

    override fun saveNursingCategory(nursingCategory: List<NursingCategory>) {
        mAppDatabase.nursingCategoryDao().insertCategoryList(nursingCategory)
    }


    override fun getEventById(eventID: Int) : Single<EventInfo> {
        return mAppDatabase.eventDao().getEventById(eventID)
    }

    override fun updateEvent(eventInfo: EventInfo) {
        mAppDatabase.eventDao().updateEvent(eventInfo)
    }

    override fun getEvents(categoryID: Int, eventType: Int): Single<List<EventInfo>> {
            return mAppDatabase.eventDao().fetchEvent(categoryID,eventType)
    }

    override fun saveDocumentCategoryList(documentCategories: List<DocumentCategory>) {
        mAppDatabase.documentCategoryDao().insertDocumentCategoryList(documentCategories)
    }

    override fun getDocumentCategories(): Single<List<DocumentCategory>> {
        return mAppDatabase.documentCategoryDao().fetchDocumentCategroyList()
    }

    override fun saveSliders(sliders: List<Slider>) {
        mAppDatabase.sliderDao().insertSliderList(sliders)
    }

    override fun getSliderList(): Single<List<Slider>> {
        return mAppDatabase.sliderDao().fetchSliders()
    }

    override fun saveDocumentList(documents: List<Document>) {
        mAppDatabase.documentDao().insertDocumentList(documents)

    }

    override fun getDocumentList(): Single<List<Document>> {
        return mAppDatabase.documentDao().fetchDocumentList()
    }



    override fun saveEvents(eventList: List<EventInfo>) {
        mAppDatabase.eventDao().insertEventList(eventList)
    }

    override fun getEvents(): Single<List<EventInfo>> {
        return mAppDatabase.eventDao().fetchEvent()
    }

    override fun getUserDetail(): Single<UserDetail> {
        return mAppDatabase.userDao().fechUser()
    }

    override fun saveUserDetail(userDetail: UserDetail) {
        mAppDatabase.userDao().insertUser(userDetail)
    }
}
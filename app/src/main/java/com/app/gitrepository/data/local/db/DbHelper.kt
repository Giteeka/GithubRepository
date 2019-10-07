package com.hkuaapp.data.local.db

import com.hkuaapp.modal.document.DocumentCategory
import com.hkuaapp.modal.home.Document
import com.hkuaapp.modal.events.EventInfo
import com.hkuaapp.modal.home.Slider
import com.hkuaapp.modal.login.UserDetail
import com.hkuaapp.modal.nursing.NursingCategory
import com.hkuaapp.modal.nursing.NursingInfo
import io.reactivex.Single

interface DbHelper {

    fun getUserDetail(): Single<UserDetail>

    fun saveUserDetail(userDetail: UserDetail)

    fun saveEvents(eventList: List<EventInfo>)

    fun getEvents(): Single<List<EventInfo>>

    fun saveSliders(sliders: List<Slider>)

    fun getSliderList(): Single<List<Slider>>

    fun saveDocumentList(documents: List<Document>)

    fun getDocumentList() : Single<List<Document>>

    fun getDocumentList(id : Int) : Single<List<Document>>

    fun saveDocumentCategoryList(documentCategories : List<DocumentCategory>)

    fun getDocumentCategories() : Single<List<DocumentCategory>>

    fun getEvents(categoryID : Int,eventType : Int): Single<List<EventInfo>>

    fun updateEvent(eventInfo: EventInfo)

    fun getEventById(eventID : Int) : Single<EventInfo>

    fun saveNursingCategory(nursingCategory: List<NursingCategory>)

    fun getNursingCategory() : Single<List<NursingCategory>>

    fun saveNursing(nursingInfo : NursingInfo)

    fun getNursing(id : Int) : Single<NursingInfo>

}
package com.hkuaapp.data.local.prefs

interface PreferenceHelper {
     fun getAccessToken(): String?


     fun setAccessToken(accessToken: String?)

     fun getFcmToken(): String?

     fun setFCMToken(accessToken: String?)

     fun getCurrentUserEmail(): String?

     fun setCurrentUserEmail(email: String?)

     fun getCurrentUserId(): Long?

     fun setCurrentUserId(userId: Long?)

     fun setCurrentUserCategoryId(categoryId: Long?)

     fun getCurrentUserCategoryId(): Long?

     fun getCurrentUserName(): String?

     fun setCurrentUserName(userName: String?)

     fun setFontPreference(fontPreference: Int)

     fun getFontPreference(): Int

     fun getLinkResponse(): String?

     fun saveLinkResponse(responseLink: String?)

     fun getAdvertiseResponse(): String?

     fun saverAdvertisement(responseAdvertise: String?)

     fun saveAboutus(responseAbout: String?)

     fun getAbouts(): String?

     fun saveQrCodeText(eventId : Int,strQrCode: String?)

     fun getQrCodeText(eventId: Int): String?

     fun clearAllPrefs()
}
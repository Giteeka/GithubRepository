package com.hkuaapp.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.hkuaapp.di.PreferenceInfo
import com.hkuaapp.utils.AppConstants
import javax.inject.Inject

class AppPreferenceHelper @Inject constructor(context: Context, @PreferenceInfo prefFileName: String) : PreferenceHelper {


    private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

    private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"

    private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"

    private val PREF_KEY_CURRENT_USER_CATEGORY_ID = "PREF_KEY_CURRENT_USER_CATEGORY_ID"


    private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"

    private val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"

    private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"

    private val PREF_KEY_FONT_PREFERENCE = "PREF_KEY_FONT_PREFERENCE"

    private val PREF_KEY_FCM_TOKEN = "PREF_KEY_FCM_TOKEN"

    private val PREF_KEY_LINK_RESPONSE = "PREF_KEY_LINK_RESPONSE"


    private val PREF_KEY_ABOUT_RESPONSE = "PREF_KEY_ABOUT_RESPONSE"

    private val PREF_KEY_ADVERTISE_RESPONSE = "PREF_KEY_ADVERTISE_RESPONSE"


    private val PREF_KEY_QR_CODE_TEXT = "PREF_KEY_QR_CODE_TEXT"

    private var mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun clearAllPrefs() {
        mPrefs.edit().clear().apply()
    }

    override fun getAccessToken(): String? {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    override fun setAccessToken(accessToken: String?) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

    override fun getFcmToken(): String? {
        return mPrefs.getString(PREF_KEY_FCM_TOKEN, "")
    }

    override fun setFCMToken(fcmToken: String?) {
        mPrefs.edit().putString(PREF_KEY_FCM_TOKEN, fcmToken).apply()
    }

    override fun getCurrentUserEmail(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    override fun setCurrentUserEmail(email: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()
    }

    override fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return if (userId == AppConstants.NULL_INDEX) null else userId
    }

    override fun setCurrentUserId(userId: Long?) {
        val id = userId ?: AppConstants.NULL_INDEX
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    override fun setCurrentUserCategoryId(categoryId: Long?) {
        val id = categoryId ?: AppConstants.NULL_INDEX
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_CATEGORY_ID, id).apply()
    }

    override fun getCurrentUserCategoryId(): Long? {
        val cateID = mPrefs.getLong(PREF_KEY_CURRENT_USER_CATEGORY_ID, AppConstants.NULL_INDEX)
        return if (cateID == AppConstants.NULL_INDEX) null else cateID
    }

    override fun getCurrentUserName(): String? {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)

    }

    override fun setCurrentUserName(userName: String?) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()
    }

    override fun setFontPreference(fontPreference: Int) {
        mPrefs.edit().putInt(PREF_KEY_FONT_PREFERENCE, fontPreference).apply()
    }

    override fun getFontPreference(): Int {
        return mPrefs.getInt(PREF_KEY_FONT_PREFERENCE, 1)
    }

    override fun getLinkResponse(): String? {
        return mPrefs.getString(PREF_KEY_LINK_RESPONSE, null)
    }

    override fun saveLinkResponse(responseLink: String?) {
        mPrefs.edit().putString(PREF_KEY_LINK_RESPONSE, responseLink).apply()
    }

    override fun getAdvertiseResponse(): String? {
        return mPrefs.getString(PREF_KEY_ADVERTISE_RESPONSE, null)
    }

    override fun saverAdvertisement(responseAdvertise: String?) {
        mPrefs.edit().putString(PREF_KEY_ADVERTISE_RESPONSE, responseAdvertise).apply()
    }

    override fun saveAboutus(responseAbout: String?) {
        mPrefs.edit().putString(PREF_KEY_ABOUT_RESPONSE, responseAbout).apply()
    }

    override fun getAbouts(): String? {
        return mPrefs.getString(PREF_KEY_ABOUT_RESPONSE, null)
    }

    override fun saveQrCodeText(eventId: Int, strQrCode: String?) {
        mPrefs.edit().putString(PREF_KEY_QR_CODE_TEXT + "_" + eventId, strQrCode).apply()
    }

    override fun getQrCodeText(eventId: Int): String? {
        return mPrefs.getString(PREF_KEY_QR_CODE_TEXT + "_" + eventId, null)
    }
}
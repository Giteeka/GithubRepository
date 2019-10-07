package com.app.gitrepository.data;

import com.hkuaapp.data.local.db.DbHelper;
import com.hkuaapp.data.local.prefs.PreferenceHelper;
import com.app.gitrepository.data.remote.ApiHelper;

public interface DataManager extends PreferenceHelper, ApiHelper, DbHelper {

    void setUserAsLoggedOut();

    void updateApiHeader(Long userId, String accessToken);

}

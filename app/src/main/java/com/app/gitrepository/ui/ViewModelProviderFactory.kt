package com.app.gitrepository.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.gitrepository.data.MyDataManager
import com.app.gitrepository.ui.home.HomeViewModel

class ViewModelProviderFactory constructor(var myDataManager: MyDataManager) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(myDataManager) as T
        }
        return super.create(modelClass)
    }
}
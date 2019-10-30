package com.app.gitrepository.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.gitrepository.data.DataManager
import com.app.gitrepository.data.MyDataManager
import com.app.gitrepository.ui.home.HomeViewModel
import com.app.gitrepository.utils.rx.AppSchedulerProvider
import com.app.gitrepository.utils.rx.SchedulerProvider

class ViewModelProviderFactory constructor(var myDataManager: DataManager, var appSchedulerProvider: SchedulerProvider) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(myDataManager,appSchedulerProvider) as T
        }
        return super.create(modelClass)
    }
}
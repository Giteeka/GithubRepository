package com.app.gitrepository.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.app.gitrepository.data.DataManager
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.ui.base.BaseViewModel
import com.app.gitrepository.utils.Logg
import com.app.gitrepository.utils.rx.AppSchedulerProvider
import com.app.gitrepository.utils.rx.SchedulerProvider


class HomeViewModel(private var myDataManager: DataManager,private var appSchedulerProvider: SchedulerProvider) : BaseViewModel<HomeNavigator>() {

    private val TAG = "HomeViewModel"
    var listLiveData: MutableLiveData<List<Repository>> = MutableLiveData()
    var noDataFound: ObservableField<Boolean> = ObservableField(false)
    fun loadData(isForced: Boolean = false) {
        loadDataFromLocal()
        if (myDataManager.isNetworkConnected())
            fetchDataFromRemote()

    }

    private fun fetchDataFromRemote() {
        isLoading.set(true)
        val subscribe =
            myDataManager.fetchHomeScreenData()
                .subscribeOn(appSchedulerProvider.io())
                .doAfterSuccess {
                    myDataManager.deleteAll()
                    myDataManager.insert(it)
                }
                .observeOn(appSchedulerProvider.ui())
                .subscribe({ blogResponse ->
                    isLoading.set(false)
                    if (blogResponse != null && blogResponse.isNotEmpty()) {
                        setData(blogResponse)
                    } else {
                        noDataFound.set(true)
                    }
                }, { throwable ->
                    isLoading.set(false)
                    noDataFound.set(true)
                    getNavigator()?.showToast("Exception: ${throwable.message}")
                })
    }


    private fun loadDataFromLocal() {
        isLoading.set(true)
        val subscribe =
            myDataManager.getRowItems().subscribeOn(appSchedulerProvider.io())
                .observeOn(appSchedulerProvider.ui())
                .subscribe(
                    { blogResponse ->
                        setData(blogResponse)
                    }, { throwable ->
                        isLoading.set(false)
                        noDataFound.set(true)

                    })
    }

    private fun setData(blogResponse: List<Repository>?) {
        listLiveData.value = blogResponse
        isLoading.set(false)
        noDataFound.set(blogResponse?.size == 0)
    }

}
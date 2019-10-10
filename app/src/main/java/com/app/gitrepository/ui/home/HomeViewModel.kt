package com.app.gitrepository.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.gitrepository.data.MyDataManager
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.ui.base.BaseViewModel
import com.app.gitrepository.utils.Logg
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeViewModel(private var myDataManager: MyDataManager) : BaseViewModel<HomeNavigator>() {

    private val TAG = "HomeViewModel"
    var listLiveData: MutableLiveData<List<Repository>> = MutableLiveData()
    var noDataFound: ObservableField<Boolean> = ObservableField(false)
    fun loadData(isForced: Boolean = false) {
        loadDataFromLocal()
        isLoading.set(true)
        val subscribe =
            myDataManager.getDataForHome()
                .subscribeOn(getSchedulerProvider().io())
                .doAfterSuccess {
                    myDataManager.deleteAll()
                    myDataManager.insert(it)
                }
                .observeOn(getSchedulerProvider().ui())
                .subscribe({ blogResponse ->
                    isLoading.set(false)
                    if (blogResponse != null) {
                        if (blogResponse.isNotEmpty()) {
                            listLiveData.value = blogResponse
                        }
                    } else {
                        getNavigator()?.showToast("oops something went wrong")
                    }
                }, { throwable ->
                    isLoading.set(false)
                    getNavigator()?.showToast("Exception: ${throwable.message}")
                })
    }

    private fun loadDataFromLocal() {
        val subscribe =
            myDataManager.getRowItems().subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(
                    { blogResponse ->
                        listLiveData.value = blogResponse
                        Logg.e(TAG, "list size------------ : ${listLiveData.value?.size}")
                        noDataFound.set(listLiveData.value?.size ?: 0 == 0)
                    }, { throwable ->
                        noDataFound.set(listLiveData.value?.size ?: 0 == 0)
                    })
    }

}
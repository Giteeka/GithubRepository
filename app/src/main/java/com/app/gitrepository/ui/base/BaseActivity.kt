/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.app.gitrepository.ui.base

import android.annotation.TargetApi
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.work.WorkManager
import com.app.gitrepository.GitApp
import com.app.gitrepository.data.DataManager
import com.app.gitrepository.data.MyDataManager
import com.app.gitrepository.data.local.DbHelper
import com.app.gitrepository.data.local.MyDbHelper
import com.app.gitrepository.data.local.RoomDatabaseHelper
import com.app.gitrepository.data.local.SharedPreferenceHelper
import com.app.gitrepository.data.network.ApiService
import com.app.gitrepository.data.network.MyApiHelper
import com.app.gitrepository.utils.NetworkUtils
import com.app.gitrepository.utils.rx.AppSchedulerProvider
import com.app.gitrepository.utils.rx.SchedulerProvider

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    // this can probably depend on isLoading variable of BaseViewModel,
    // since its going to be common for all the activities
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): V?

    val isNetworkConnected: Boolean?
        get() = myDataManager?.isNetworkConnected()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    open var TAG = "BaseActivity"

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(vararg permissions: String): Boolean {
        var hasPermission = true
        for (per in permissions) {
            hasPermission =
                Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(per) == PackageManager.PERMISSION_GRANTED
        }
        return hasPermission
    }

    private var myDataManager: DataManager? = null
    fun getDataManager(): DataManager {
        if (myDataManager == null) {

            var dbHelper: DbHelper = MyDbHelper(RoomDatabaseHelper.getInstance(applicationContext))
            var sharePref = SharedPreferenceHelper.getInstance(applicationContext)
            var apiHelper =
                MyApiHelper(ApiService.create((application as GitApp?)?.baseUrl.orEmpty()))
            var networkUtils = NetworkUtils(applicationContext)
            var workManager = WorkManager.getInstance(applicationContext)
            myDataManager = MyDataManager(networkUtils, dbHelper, apiHelper, workManager)
//            myDataManager = MyDataManager(this)
        }
        return myDataManager!!
    }

    fun getSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }


    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        viewDataBinding?.setVariable(bindingVariable, mViewModel)
        viewDataBinding?.executePendingBindings()
    }


}


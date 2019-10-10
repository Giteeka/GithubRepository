package com.app.gitrepository.ui.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.gitrepository.BR
import com.app.gitrepository.R
import com.app.gitrepository.data.local.SharedPreferenceHelper
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.databinding.ActivityHomeBinding
import com.app.gitrepository.ui.ViewModelProviderFactory
import com.app.gitrepository.ui.base.BaseActivity
import com.google.android.material.snackbar.Snackbar


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), HomeNavigator {
    override fun noInternetConnection() {
        showToast(getString(R.string.no_internet_connection))
    }

    override fun showToast(s: String) {
        viewDataBinding?.root?.let { Snackbar.make(it, s, Snackbar.LENGTH_LONG).show() }
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_home


    override fun getViewModel(): HomeViewModel? {
        return ViewModelProviders.of(this, ViewModelProviderFactory(getDataManager())).get(
            HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding?.swipeToRefresh?.setOnRefreshListener {
            getViewModel()?.loadData(true)
        }
        getViewModel()?.setNavigator(this)
        supportActionBar?.title =
            getDataManager().sharePref.getString(SharedPreferenceHelper.PREF_TITLE, getString(R.string.title_home))
        getViewModel()?.loadData()

        getViewModel()?.listLiveData?.observe(this,
            Observer<List<Repository>> { t ->
                supportActionBar?.title =
                    getDataManager().sharePref.getString(
                        SharedPreferenceHelper.PREF_TITLE,
                        getString(R.string.title_home)
                    )
                viewDataBinding?.rvItems?.adapter = HomeAdapter(t)
            })
    }


}

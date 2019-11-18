package com.app.gitrepository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.app.gitrepository.data.DataManager
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.ui.home.HomeViewModel
import com.app.gitrepository.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.*
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class HomeViewModelTest {
    var avatarUrl =
        "https://images.sftcdn.net/images/t_app-cover-l,f_auto/p/befbcde0-9b36-11e6-95b9-00163ed833e7/260663710/the-test-fun-for-friends-screenshot.jpg"
    var repoUrl = "https://github.com/Giteeka/GithubRepository"

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var dataManager = Mockito.mock(DataManager::class.java)
    private lateinit var homeViewModel: HomeViewModel

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    var observer: Observer<List<Repository>>? = null

    lateinit var testSchedulerProvider: TestSchedulerProvider
    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider(TestScheduler())

        homeViewModel = HomeViewModel(dataManager, testSchedulerProvider)
        observer?.let { homeViewModel.listLiveData.observeForever(it) }
    }

    @Test
    fun error() {

        Mockito.`when`(dataManager.fetchHomeScreenData())
            .thenReturn(Single.error(Throwable("An error has occurred!")))
        Mockito.`when`(dataManager.isNetworkConnected()).thenReturn(true)
        Mockito.`when`(dataManager.getRowItems()).thenReturn(
            Single.error(Throwable("An error has occurred!"))
        )
        homeViewModel.loadData()
        testSchedulerProvider.testScheduler.triggerActions()
        assertSame(homeViewModel.noDataFound.get(), true)
    }

    @Test
    fun test() {
        val repo: Repository = Repository(
            1, "Giteeka", "GiteekaRepo", avatarUrl, repoUrl, "Giteeka Test Repo",
            "android", "#000000", "1", "2", "3", ArrayList()
        )
        val repo1: Repository = Repository(
            2, "Giteeka1", "GiteekaRepo", avatarUrl, repoUrl, "Giteeka Test Repo",
            "android", "#000000", "1", "2", "3", ArrayList()
        )
        val list: ArrayList<Repository> = ArrayList()

        list.add(repo)
        list.add(repo1)

        var response = Single.just(list as List<Repository>)

        Mockito.`when`(dataManager.fetchHomeScreenData()).thenReturn(response)
        Mockito.`when`(dataManager.isNetworkConnected()).thenReturn(true)
        Mockito.`when`(dataManager.getRowItems()).thenReturn(response)

        homeViewModel.loadData()
        testSchedulerProvider.testScheduler.triggerActions()
        assertNotNull(homeViewModel.listLiveData);
        assertTrue(homeViewModel.listLiveData.hasObservers());
        assertSame(homeViewModel.listLiveData.value, list)

    }





    class TestSchedulerProvider(var testScheduler: TestScheduler) : SchedulerProvider {
        override fun computation(): TestScheduler {
            return testScheduler
        }

        override fun io(): TestScheduler {
            return testScheduler
        }

        override fun ui(): TestScheduler {
            return testScheduler
        }

    }

    class TestObserver<T> : Observer<T> {
        var observedValue: T? = null

        override fun onChanged(t: T) {
            observedValue = t
        }
    }

    fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
        observeForever(it)
    }
}


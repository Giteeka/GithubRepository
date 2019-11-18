package com.app.gitrepository

import androidx.room.PrimaryKey
import com.app.gitrepository.data.MyDataManager
import com.app.gitrepository.data.local.DbHelper
import com.app.gitrepository.data.local.MyDbHelper
import com.app.gitrepository.data.local.RepositoryDao
import com.app.gitrepository.data.local.RoomDatabaseHelper
import com.app.gitrepository.data.model.BuiltBy
import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.data.network.ApiHelper
import com.app.gitrepository.data.network.MyApiHelper
import com.app.gitrepository.utils.NetworkUtils
import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleJust
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations


class MyDataManagerTest {

    private val mockDatabase = mock(RoomDatabaseHelper::class.java)

    private var mockDao: RepositoryDao

    private var dbHelper: DbHelper = MyDbHelper(mockDatabase)

    private var networkUtils: NetworkUtils = mock(NetworkUtils::class.java)

    private var apiHelper: ApiHelper = mock(MyApiHelper::class.java)

    private var dataManager = MyDataManager(networkUtils, dbHelper, apiHelper = apiHelper,workManager = null)

//    private var apiHelper = mockDao(ApiHelper::class.java)
//    private var dataManager = MyDataManager()

    init {
        mockDao = mock(RepositoryDao::class.java)
    }

//    data class Repository(
//        @PrimaryKey(autoGenerate = true)
//        var _id: Int = 0,
//        var author: String?,
//        var name: String?,
//        var avatar: String?,
//        var url: String?,
//        var description: String?,
//        var language: String?,
//        var languageColor: String?,
//        var stars: String?,
//        var forks: String?,
//        var currentPeriodStars: String?,
//        var builtBy: ArrayList<BuiltBy?>?
//    )
//
    @Before
    fun init_mocks() {
        MockitoAnnotations.initMocks(this)
    }

    var avatarUrl = "https://images.sftcdn.net/images/t_app-cover-l,f_auto/p/befbcde0-9b36-11e6-95b9-00163ed833e7/260663710/the-test-fun-for-friends-screenshot.jpg"
    var repoUrl = "https://github.com/Giteeka/GithubRepository"

    @Test
    fun getRepository() {
        `when`(mockDatabase.rowDao()).thenReturn(mockDao)

        var repo: Repository = Repository(
            1, "Giteeka", "GiteekaRepo", avatarUrl, repoUrl, "Giteeka Test Repo",
            "android", "#000000", "1", "2", "3", ArrayList()
        )

        `when`(mockDao.getRowItemByName("GiteekaRepo")).thenReturn(Single.just(repo))

//        `when`(mockDao.getRowItemByName("GiteekaRepo")).thenReturn(repo)
//        Assert.assertEquals(repo,mockDao.getRowItemByName("GiteekaRepo") )
        val test = dataManager.getRowByName("GiteekaRepo").test()
        test.assertValue(repo)
        test.dispose()

    }

}
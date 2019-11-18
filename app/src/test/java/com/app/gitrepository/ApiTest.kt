package com.app.gitrepository

import com.app.gitrepository.data.model.Repository
import com.app.gitrepository.data.network.ApiService
import com.app.gitrepository.data.network.MyApiHelper
import io.reactivex.Single
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class ApiTest{
    private val apiService  = Mockito.mock(ApiService::class.java)
    private val apiHelper = MyApiHelper(apiService)
    var avatarUrl = "https://images.sftcdn.net/images/t_app-cover-l,f_auto/p/befbcde0-9b36-11e6-95b9-00163ed833e7/260663710/the-test-fun-for-friends-screenshot.jpg"
    var repoUrl = "https://github.com/Giteeka/GithubRepository"

    @Test
    fun testSuccessApi(){
        var repo: Repository = Repository(
            1, "Giteeka", "GiteekaRepo", avatarUrl, repoUrl, "Giteeka Test Repo",
            "android", "#000000", "1", "2", "3", ArrayList()
        )
        var repo1: Repository = Repository(
            2, "Giteeka1", "GiteekaRepo", avatarUrl, repoUrl, "Giteeka Test Repo",
            "android", "#000000", "1", "2", "3", ArrayList()
        )
        var list : ArrayList<Repository> = ArrayList()

        list.add(repo)
        list.add(repo1)

        var response = Single.just(list as List<Repository>)
        `when`(apiService.getRepositories()).thenReturn(response)

        var testObservable = apiHelper.fetchHomeScreenData().test()
        testObservable.assertValues(list)

    }

    @Test
    fun testErrorApi(){
        `when`(apiService.getRepositories()).thenReturn(Single.error(Throwable("An error has occurred!")))
        var testObservable = apiHelper.fetchHomeScreenData().test()
        testObservable.assertErrorMessage("An error has occurred!")


    }
}
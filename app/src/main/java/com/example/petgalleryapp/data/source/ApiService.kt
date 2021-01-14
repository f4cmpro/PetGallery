//package jp.co.rakuten.livestreaming.data.source
//
//import jp.co.rakuten.livestreaming.data.model.sample.Project
//import kotlinx.coroutines.flow.Flow
//import retrofit2.Call
//import retrofit2.Response
//import retrofit2.http.GET
//import retrofit2.http.Path
//
//interface ApiService {
//    /****************************************************************
//     * Sample
//     ****************************************************************/
//    @GET("users/{user}/repos")
//    suspend fun getProjectList(@Path("user") user: String): List<Project>
//}
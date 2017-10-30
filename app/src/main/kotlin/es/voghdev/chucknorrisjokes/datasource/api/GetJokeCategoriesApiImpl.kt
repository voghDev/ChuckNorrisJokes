package es.voghdev.chucknorrisjokes.datasource.api

import com.google.gson.JsonSyntaxException
import es.voghdev.chucknorrisjokes.BuildConfig
import es.voghdev.chucknorrisjokes.datasource.api.model.ChuckNorrisService
import es.voghdev.chucknorrisjokes.model.AbsError
import es.voghdev.chucknorrisjokes.model.CNError
import es.voghdev.chucknorrisjokes.model.JokeCategory
import es.voghdev.chucknorrisjokes.usecase.GetJokeCategories
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetJokeCategoriesApiImpl : GetJokeCategories, ApiRequest {
    override fun getJokeCategories(): Pair<List<JokeCategory>?, AbsError?> {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            builder.addInterceptor(LogJsonInterceptor())

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(getEndPoint())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build()

        val service: ChuckNorrisService = retrofit.create(ChuckNorrisService::class.java)

        val call: Call<List<String>> = service.getJokeCategories()

        try {
            val rsp: Response<List<String>>? = call.execute()

            if (rsp?.body()?.size ?: 0 > 0) {
                return Pair(rsp?.body()?.map { JokeCategory(name = it) } ?: emptyList<JokeCategory>(), null)
            } else if (rsp?.errorBody() != null) {
                val error = rsp?.errorBody().string()
                return Pair(null, CNError(error))
            }
        } catch(e: JsonSyntaxException) {
            return Pair(null, CNError(e.message ?: "Unknown error parsing JSON"))
        }

        return Pair(null, CNError("Unknown error"))
    }
}
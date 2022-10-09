package com.example.simpleretrofitwithcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val api: MyApi = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/posts/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        GlobalScope.launch {
            val response: Response<List<Comment>> = api.getComments()
            if (response.isSuccessful){
                for (comment in response.body()!!){
                    println("Comment : " +
                            "$comment")
                }
            }
        }
    }
}
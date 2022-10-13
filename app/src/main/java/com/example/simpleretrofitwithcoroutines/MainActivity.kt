package com.example.simpleretrofitwithcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var tv: TextView
    private lateinit var pb: ProgressBar

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.tv)
        pb = findViewById(R.id.pb)


        val api: MyApi = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/posts/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        GlobalScope.launch {
            val response: Response<List<Comment>> = api.getComments()
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    tv.visibility = View.VISIBLE
                    pb.visibility = View.GONE
                    for (comment in response.body()!!) {
                        println(
                            "Comment : " +
                                    "$comment"
                        )
                    }
                }
            }
        }
    }
}
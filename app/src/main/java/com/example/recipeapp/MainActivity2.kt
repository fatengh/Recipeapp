package com.example.recipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    private var recipeDetails: List<RecipeDeta.recDatum>? = null
    lateinit var rvRecip: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rvRecip = findViewById(R.id.rvdata)
        val apiInterface = ApiClient().getClient()?.create(ApiInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getRecipies()?.enqueue(object : Callback<List<RecipeDeta.recDatum>> {
                override fun onResponse(
                    call: Call<List<RecipeDeta.recDatum>>,
                    response: Response<List<RecipeDeta.recDatum>>
                ) {
                    recipeDetails = response.body()!!
                    rvRecip.adapter = RecipeAdap(this@MainActivity2, recipeDetails!!)
                    rvRecip.layoutManager = LinearLayoutManager(this@MainActivity2)
                }

                override fun onFailure(call: Call<List<RecipeDeta.recDatum>>, t: Throwable) {
                    Toast.makeText(applicationContext, "" + t.message, Toast.LENGTH_SHORT).show();
                }

            })
        }


    }



}
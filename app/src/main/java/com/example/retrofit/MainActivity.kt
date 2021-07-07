package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.text)
        val repositry = Repository()
        val viewModelFactory = MainViewModelFactory(repositry)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {  reponse ->
            if(reponse.isSuccessful){
                Log.d("Response", reponse.body()?.userId.toString())
                Log.d("Response", reponse.body()?.id.toString())
                textView.text = reponse.body()?.title
                Log.d("Response", reponse.body()?.title!!)
                Log.d("Response", reponse.body()?.body!!)
            } else {
                Log.d("Response", reponse.errorBody().toString())
                textView.text = reponse.code().toString()
            }
        })

    }
}
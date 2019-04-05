package com.example.labo4_0119_ejercicio1.activities

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labo4_0119_ejercicio1.R
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import models.Movie
import org.json.JSONObject
import utils.NetworkUtils
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private inner class FetchMovie : AsyncTask<String, Void, String>(){

        override fun doInBackground(vararg params: String): String {
            if(params.isNullOrEmpty()) return ""

            val movieName = params[0]
            val movieURL = NetworkUtils().BuildSearchUrl(movieName)

            return try {

                NetworkUtils().getResponseFromHttpUrl(movieURL)

            }catch (e: IOException){
                ""
            }
        }

        override fun onPostExecute(movieInfo: String){
            super.onPostExecute(movieInfo)

            if(!movieInfo.isEmpty()){
                val movieJson = JSONObject(movieInfo)

                if(movieJson.getString("Response") == "True"){
                    val movie = Gson().fromJson<Movie>(movieInfo, Movie::class.java)
                }else{
                    Snackbar.make(main_ll, "NEl", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

}

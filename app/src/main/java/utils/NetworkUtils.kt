package utils

import android.net.Uri
import java.io.IOException
import java.lang.System.`in`
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

class NetworkUtils {

    val MOVIES_API_URL = "http://www.omdbapi.com/"
    val TOKEN_API = "7bf1fb84"

    fun BuildSearchUrl(movieName: String): URL {
        val builtUri = Uri.parse(MOVIES_API_URL)
            .buildUpon()
            .appendQueryParameter("apikey", TOKEN_API)
            .appendQueryParameter("t", movieName)
            .build()

        return try {
            URL(builtUri.toString())
        }catch (e:MalformedURLException){
            URL("")
        }
    }

    @Throws(IOException::class)
    fun getResponseFromHttpUrl(url: URL): String{
        val urlConnection = url.openConnection() as HttpURLConnection
        try {

            val `in` = urlConnection.inputStream

            val scanner = Scanner(`in`)
            scanner.useDelimiter("\\A")

            val hasInput = scanner.hasNext()
            return if(hasInput) {
                scanner.next()
            }else{
                ""
            }

        }finally {

        }
    }
}
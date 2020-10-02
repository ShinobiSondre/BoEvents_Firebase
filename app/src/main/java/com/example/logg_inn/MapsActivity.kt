package com.example.logg_inn

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.logg_inn.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Field


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var list = HomeFeed(listOf())
    val maxIndex = 8
    var curIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val jsonFileString = fetchLocalJson(applicationContext, "file.json")

        if (jsonFileString != null) {
            Log.i("data" , jsonFileString)
        }

        val gson = GsonBuilder().create()
        val homeFeed = gson.fromJson(jsonFileString, HomeFeed::class.java)

        /*while(curIndex < maxIndex){
            val bø = LatLng(homeFeed.events[curIndex].lat, homeFeed.events[curIndex].lng)
            val info = homeFeed.events[curIndex].info


            val kategori = homeFeed.events[curIndex].genre

            val imageResource: Int =  resources.getIdentifier(
                kategori,
                "drawable",
                this.packageName)
            System.out.println(kategori + " " + imageResource)


            mMap.addMarker(
                MarkerOptions()
                    .position(bø)
                    .title(info)
                    .icon(BitmapDescriptorFactory.fromResource(imageResource))
            )
            curIndex++
        }
*/

        val bø1 = LatLng(59.415326, 9.067200)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bø1, 13f))
    }

    fun fetchLocalJson(context: Context, fileName: String): String? {
        val jsonString : String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException){
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    /*
    fun fetchJson() {
        println("fetching json")






        val url = "https://raw.githubusercontent.com/Potatamas/xmltest/master/noe2.json"


        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                list = homeFeed


            }
            override fun onFailure(call: Call, e: IOException) {
                println("Failure!")
            }

        })

        //placeMarkers(this.list)



         */

    /*

    }
    fun placeMarkers(liste : HomeFeed){


        var lat : Double
        var lng : Double
        var bø = LatLng(59.418326, 9.067200)

        //while(x != n){
        System.out.println("dette er en kategori " + liste.events[2].genre)
            lat = liste.events[2].lat
            lng = liste.events[2].lng
            bø = LatLng(lat, lng)


            mMap.addMarker(MarkerOptions()
                .position(bø)
                .title("Markør i Bø")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.brettspill)))
        //    n++

        //}
    }
}
*/
    class HomeFeed(val events: List<Events>)

    class Events(
        val lat: Double,
        val lng: Double,
        val info: String,
        val bilder: String,
        val txt: String,
        val genre: String,
        val link: String
    )

}
package com.example.logg_inn

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fullscreen_dialog.view.*
import kotlinx.android.synthetic.main.slideup.*
import kotlinx.android.synthetic.main.stylelogginn.view.*
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), OnMapReadyCallback {


    //Login Dialog
    private lateinit var closeDialog: AlertDialog;
    //Nytt Event Dialog
    private lateinit var closeDialog1 : AlertDialog;

    // Login / Nytt Event / Hvilken som helst dialog bruker disse to i felles
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView;

    //RecycleView + Searchbar bruker disse
    private lateinit var eventAdapter: EventRecyclerAdapter
    private lateinit var itemsList: MutableList<com.example.logg_inn.models.DataModel>

    //Google Maps
    private lateinit var mMap: GoogleMap
    val maxIndex = 5
    var curIndex = 0





    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main);

        //RecycleView
        initRecyclerView();
        addDataset();
        itemsList = eventAdapter.getList();


        //Login Dialog inflater
        var dialog = LayoutInflater.from(this).inflate(R.layout.stylelogginn, null);
        var builder = AlertDialog.Builder(this).setView(dialog);
        closeDialog = builder.create()


        //Nytt Event Dialog inflater
        var dialog1 = LayoutInflater.from(this).inflate(R.layout.fullscreen_dialog, null);
        var builder1 = AlertDialog.Builder(this).setView(dialog1);
        closeDialog1 = builder1.create()


        //GoogleMaps Fragment

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)





        //Drawer

        drawerLayout = findViewById(R.id.drawerLayout)

        navView = findViewById(R.id.navView)

        val navController = this.findNavController(R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navView, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        navView = this.findViewById<NavigationView>(R.id.navView);
        navView.bringToFront();

        //Drawer Menu Items

        navView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.item1 -> {

                    // Dialog Test : closeDialog.show();
                    System.out.println("Hello1")
                    true
                }

                R.id.item2 -> {
                    System.out.println("Hello2")
                    true
                }


                else -> false
            }

        }





        //Login Listeneres

        dialog.btnlogin.setOnClickListener {

            closeDialog.dismiss();
        }


        dialog.close_login.setOnClickListener {

            closeDialog.dismiss();

        }

        //Nytt Event Listeneres

        dialog1.fullscreen_dialog_close.setOnClickListener(){

            closeDialog1.dismiss();
        }

        dialog1.fullscreen_dialog_action.setOnClickListener(){

            closeDialog1.show()
        }




    }

    //RecycleView

    private fun addDataset(){
        val data = DataSource.createDataset()
        eventAdapter.submitList(data)
    }
    private fun initRecyclerView() {

        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecoration = TopSpacingItemDecoration(5)
            addItemDecoration(topSpacingDecoration)
            eventAdapter = EventRecyclerAdapter()
            adapter = eventAdapter
        }
    }



    //Drawer

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }


    //SearchBar

    fun SearchBar(menu: Menu): Boolean {

        var menu1: Menu = menu;


        val searchItem = menu1.findItem(R.id.searchbar)


        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Event Search"






        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {

                slidepanel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
                filterList(newText.toLowerCase())

                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // task HERE

                if(query!=""||query!=null){
                    searchView.setIconified(true);
                    searchView.onActionViewCollapsed();
                    filterList(query.toLowerCase())
                   }else{

                searchView.setIconified(true);
                searchView.onActionViewCollapsed();}


                //on submit send entire query

                return false
            }

        })

        return true;
    }


    //Searbar FilterItems

    private fun filterList(filterItem: String) {

        var tempList: MutableList<com.example.logg_inn.models.DataModel> = ArrayList()

        for (d in itemsList){

            if(filterItem in d.title.toLowerCase()){

                tempList.add(d)


            }
        }

        eventAdapter.updateList(tempList)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.toppbar, menu)
        SearchBar(menu);
        return true
    }


    //TopBar Item Listener

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {

            R.id.login -> {

                closeDialog.show();

                return true;
            }

            R.id.nyttevent -> {

                closeDialog1.show();

                return true
            }
            



            else -> super.onOptionsItemSelected(item)
        }
    }





    //Databse Connection

    /*v20app2000u1

    pw1*/


    fun connectDatabase() {
       var conn: Connection? = null

        val connectionProps = Properties()
        connectionProps.put("user", "v20app2000u1")
        connectionProps.put("password", "pw1")
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                "jdbc:" + "mysql" + "://" +
                        "itfag.usn.no" + "/" +
                        "",
                connectionProps
            )
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }

    }
    //END


    //Google Maps Functions

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val jsonFileString = fetchLocalJson(applicationContext, "file.json")

        if (jsonFileString != null) {
            Log.i("Data BOYY: ", jsonFileString)
        }


        val gson = GsonBuilder().create()
        val homeFeed = gson.fromJson(jsonFileString, HomeFeed::class.java)


        while (curIndex < maxIndex) {
            val bø = LatLng(homeFeed.events[curIndex].lat, homeFeed.events[curIndex].lng)
            val info = homeFeed.events[curIndex].info


            val kategori = homeFeed.events[curIndex].genre

            val imageResource: Int = resources.getIdentifier(
                kategori,
                "drawable",
                this.packageName
            )
            System.out.println(kategori + " " + imageResource)


            mMap.addMarker(
                MarkerOptions()
                    .position(bø)
                    .title(info)
                    .icon(BitmapDescriptorFactory.fromResource(imageResource))
            )
            curIndex++
        }

        val bø1 = LatLng(59.415326, 9.067200)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bø1, 13f))
    }


    fun fetchLocalJson(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}

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

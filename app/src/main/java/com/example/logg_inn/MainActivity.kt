package com.example.logg_inn

import android.accessibilityservice.GestureDescription
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logg_inn.models.DataModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.fullscreen_dialog.view.*
import kotlinx.android.synthetic.main.slideup.*
import kotlinx.android.synthetic.main.stylelogginn.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.io.IOException


class MainActivity : AppCompatActivity(), OnMapReadyCallback{

    var imageUrls = mutableListOf<String>()
    private lateinit var picker: TimePicker

    //FIREBASE FR

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
// ...

    //NyttEvent Items
    private lateinit var editText: EditText
    private lateinit var ButtonEventSave: Button
    private var filePath: Uri = Uri.EMPTY



// ...



    //Login Dialog
    private lateinit var closeDialog: AlertDialog

// ...
// Initialize Firebase Auth


    //Nytt Event Dialog
    lateinit var closeDialog1 : AlertDialog

    // Login / Nytt Event / Hvilken som helst dialog bruker disse to i felles
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    //RecycleView + Searchbar bruker disse
    private lateinit var eventAdapter: EventRecyclerAdapter
    private lateinit var itemsList: MutableList<com.example.logg_inn.models.DataModel>

    //Google Maps
    private lateinit var mMap: GoogleMap
    val maxIndex = 5
    var curIndex = 0




    @SuppressLint("InflateParams", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)




        //Firebase Auth Anonymous

        // Initialize Firebase Auth
        auth = Firebase.auth


        signInAnonymously()

        /*auth.signInAnonymously()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    println( "Authentication Succsess.")
                    val user = auth.currentUser




                } else {
                    // If sign in fails, display a message to the user.
                    println( "Authentication Fail.")
                }

                // ...
            }
*/


        //Firebase Read Data / RecycleView Init
        //OnDataChange er async så jeg initierer rycleviewet i onDatachange og putter ting i listen der og sender listen til eventAdapter


        database = FirebaseDatabase.getInstance().reference


        var list = ArrayList<DataModel>()

        //AVSLUTTET HER FOR BILDE SHIT



        listFiles()




        //END OF BILDE SHIT


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this@MainActivity)


        database.child("Events").addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot){

                    if(list!=null)
                        list.clear()

                    for(ds1 in snapshot.children){

                        list.add(
                            DataModel(
                                ds1.child("tittel").value.toString(),
                                ds1.child("body").value.toString(),
                                ds1.child("img").value.toString(),
                                ds1.child("addresse").value.toString(),
                                ds1.child("id").value.toString()
                            )
                        )

                        val lat = ds1.child("lat").value.toString().toDouble()
                        val lng = ds1.child("lng").value.toString().toDouble()


                            if(lat != null && lng != null)

                        placeMarkers(ds1.child("tittel").value.toString(),ds1.child("lat").value.toString().toDouble(),ds1.child("lng").value.toString().toDouble())


                    }


                    initRecyclerView()

                    eventAdapter.submitList(list)

                    itemsList = eventAdapter.getList()


                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })



        //Login Dialog inflater
        var dialog = LayoutInflater.from(this).inflate(R.layout.stylelogginn, null)


        var builder = AlertDialog.Builder(this).setView(dialog)
        closeDialog = builder.create()


        //Nytt Event Dialog inflater
        var dialog1 = LayoutInflater.from(this).inflate(R.layout.fullscreen_dialog, null)
        var builder1 = AlertDialog.Builder(this).setView(dialog1)
        closeDialog1 = builder1.create()



        //FirebaseFR

        database = Firebase.database.reference


        //Initiate event items

        closeDialog1.setContentView(R.layout.fullscreen_dialog)
        editText = closeDialog1.findViewById(R.id.tittel)
        ButtonEventSave = closeDialog1.findViewById(R.id.fullscreen_dialog_action)

        picker = closeDialog1.findViewById<View>(R.id.datePicker1) as TimePicker
        picker.setIs24HourView(true)





        //GoogleMaps Fragment

        /*val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)*/







        //Drawer

        drawerLayout = findViewById(R.id.drawerLayout)

        navView = findViewById(R.id.navView)

        val navController = this.findNavController(R.id.nav_host_fragment)

        NavigationUI.setupWithNavController(navView, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        navView = this.findViewById<NavigationView>(R.id.navView)
        navView.bringToFront()

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

            closeDialog.dismiss()



            println("Hello")

            picker = closeDialog1.findViewById<View>(R.id.datePicker1) as TimePicker

            println("Hour : " + picker.hour.toString() + "Minute: " + picker.minute.toString() )
        }


        dialog.close_login.setOnClickListener {

            closeDialog.dismiss()

        }

        //Nytt Event Listeneres

        dialog1.fullscreen_dialog_close.setOnClickListener {
            System.out.print("Button Clicked")
            closeDialog1.dismiss()
        }

        dialog1.fullscreen_dialog_action.setOnClickListener {


            Toast.makeText(applicationContext,"Button Clicked: ", Toast.LENGTH_LONG).show()



            if(filePath!=Uri.EMPTY)
            imgUpload()
            else {
                saveEvent("https://static.thenounproject.com/png/340719-200.png")
            }


            closeDialog1.dismiss()


        }


        dialog1.uploadImg.setOnClickListener {


            Toast.makeText(applicationContext,"Button Clicked ", Toast.LENGTH_LONG).show()
            fileChooser()

        }




    }



    //Upload img


    fun fileChooser(){

        var i = Intent()

        i.type = "image/*"
        i.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(i, "Choose Picture"),111)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 111 && resultCode == Activity.RESULT_OK && data!=null){


            filePath = data.data!!
            var bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filePath)
        }
    }

    fun imgUpload(){


    if(filePath!=null || filePath.equals(null)) {
        var pd = ProgressDialog(this)
        pd.setTitle("Uploading")
        pd.show()

        editText = closeDialog1.findViewById(R.id.tittel)
        val tittel = editText.text.toString()

        var imageRef = FirebaseStorage.getInstance().getReference("images/" + tittel)



        imageRef.putFile(filePath).addOnSuccessListener { p0 ->
            pd.dismiss(); Toast.makeText(applicationContext, "File Uploaded ", Toast.LENGTH_LONG)
            .show()
            closeDialog1.dismiss()


            imageRef.downloadUrl.addOnSuccessListener {

                saveEvent(it.toString())

                    Log.i("THIS IMAGE URL: ",it.toString())

            }




        }

            .addOnFailureListener { p0 ->
                pd.dismiss()
                Toast.makeText(
                    applicationContext,
                    "Error: File not Uploaded" + p0,
                    Toast.LENGTH_LONG
                ).show()

            }


            .addOnProgressListener {

                    p0 ->
                var progress = (100.0 * p0.bytesTransferred) / p0.totalByteCount
                pd.setMessage("Uploaded ${progress.toInt()}%")

            }

    }



    }


    //Save eventinfo

    fun saveEvent(imageurl: String){

        editText = closeDialog1.findViewById(R.id.tittel)
        val tittel = editText.text.toString()

        editText = closeDialog1.findViewById(R.id.addresse)
        var addresse = editText.text.toString()

        editText = closeDialog1.findViewById(R.id.information)

        var body = editText.text.toString()

        if(tittel.isEmpty()){

            editText.error = "Husk å skrive en tittel til ditt arrangement"
            return

        }


        val ref = FirebaseDatabase.getInstance().getReference("Events/")

        val eventId = ref.push().key

        if(!addresse.contains("Bø") || addresse.contains("Bøgata") || addresse.contains("bøgata"))
            addresse = addresse + " Bø"

        val lat = getCoordinates(addresse).latitude

        val lng = getCoordinates(addresse).longitude


        val event = com.example.logg_inn.models.Events(eventId,tittel,addresse,lat,lng,imageurl,body)



        Log.i("IMAGE SIZE: " ,imageUrls.size.toString())

        if (eventId != null) {

            ref.child(eventId).setValue(event).addOnCompleteListener {

                System.out.print("Inserted Tittel: " + tittel)
            }
        }

    }

    //RecycleView

    /*

    Trengs ikke pga Firebase Stuff atm

    private fun addDataset(){
        val data = DataSource.createDataset()
        eventAdapter.submitList(data)
    }
    */

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

        var menu1: Menu = menu


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
                    searchView.isIconified = true
                    searchView.onActionViewCollapsed()
                    filterList(query.toLowerCase())
                   }else{

                    searchView.isIconified = true
                    searchView.onActionViewCollapsed();}


                //on submit send entire query

                return false
            }

        })

        return true
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
        SearchBar(menu)
        return true
    }


    //TopBar Item Listener

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {

            R.id.login -> {

                closeDialog.show()
                database = FirebaseDatabase.getInstance().reference


                return true
            }

            R.id.nyttevent -> {

                closeDialog1.show()

                return true
            }
            



            else -> super.onOptionsItemSelected(item)
        }
    }



    //Google Maps Functions

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val bø1 = LatLng(59.415326, 9.067200)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bø1, 15f))
    }


    fun moveCamera(latLng : LatLng){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
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


    //GMAPS funksjoner for firebase

    private fun getCoordinates(s : String): LatLng{
        val geocoder = Geocoder(this)
        val list = geocoder.getFromLocationName(s , 1)
        val latlng = LatLng(list[0].latitude, list[0].longitude)

        return latlng
    }

    private fun placeMarkers(tittel : String, lat : Double, lng : Double){

        val latLng = LatLng(lat, lng)
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(tittel)
        )

    }



    private fun listFiles() = CoroutineScope(Dispatchers.IO).launch {

        val imageRef = FirebaseStorage.getInstance().reference

        try {
            val images = imageRef.child("images/").listAll().await()
            imageUrls = mutableListOf<String>()
            for(image in images.items) {
                val url = image.downloadUrl.await()


                imageUrls.add(url.toString())
            }
            withContext(Dispatchers.Main) {

                //Maybe put image urls list here



            }
        } catch(e: Exception) {
            withContext(Dispatchers.Main) {
                //Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }



    private fun signInAnonymously() {
        auth.signInAnonymously()
            .addOnSuccessListener(this, object : OnSuccessListener<AuthResult?> {
                override fun onSuccess(authResult: AuthResult?) {
                    println("signInAnonymously:SUCCESS")




                    Log.i( "Info: ", "index=")


                }
            })
            .addOnFailureListener(this, object : OnFailureListener {
                override fun onFailure(exception: java.lang.Exception) {
                    println("signInAnonymously:FAILURE")
                }
            })
    }


}



//GMAPS



package com.example.logg_inn

import android.app.AlertDialog
import android.app.assist.AssistStructure
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.logg_inn.models.DataModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fullscreen_dialog.*
import kotlinx.android.synthetic.main.layout_event_list_item.view.*

class EventRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: MutableList<DataModel> = ArrayList()
    private lateinit var database: DatabaseReference


    lateinit var closeDialog1 : LayoutInflater



    fun updateList(list: MutableList<DataModel>){

    items = list

        notifyDataSetChanged()

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return EventsViewHodler(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_event_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){

            is EventsViewHodler ->{
                holder.bind(items.get(position))



                //Event Info ClickEvent

                holder.itemView.setOnClickListener { p0 ->

                    Log.i("Tittel: " , items.get(position).id.toString())

                    var bilde = items.get(position).image

                    var tittel = items.get(position).title

                    var brukernavn = items.get(position).username




                    val activity = p0.context as AppCompatActivity
                    val eventInfoFragment = EventInfoFragment()
                    activity.supportFragmentManager.beginTransaction()
                        .replace(R.id.drawerLayout, eventInfoFragment)
                        .addToBackStack(null)
                        .commit()



                }

                //END




            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getList(): MutableList<DataModel>{

        return items
    }

    fun submitList(events: MutableList<DataModel>){
        items = events
    }

    class EventsViewHodler constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){

        val eventImage = itemView.event_image
        val eventTitle = itemView.event_title
        val eventAuthor = itemView.event_author

        init {
            itemView.setOnClickListener{v: View ->
                val position: Int = adapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(dataModel: DataModel){

            eventTitle.text = dataModel.title
            eventAuthor.text = dataModel.username

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.overwatchto)
                .error(R.drawable.tracerspray1)
                .dontAnimate()

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(dataModel.image)
                .into(eventImage)
        }
    }


}
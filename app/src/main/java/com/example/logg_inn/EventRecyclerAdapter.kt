package com.example.logg_inn

import android.app.PendingIntent.getActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.logg_inn.models.DataModel
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.layout_event_list_item.view.*
import kotlinx.coroutines.android.awaitFrame


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

                    val activity = p0.context as AppCompatActivity
                    val eventInfoFragment = EventInfoFragment()

                    val fragmentTransaction = activity.supportFragmentManager.beginTransaction()




                    fragmentTransaction.replace(R.id.drawerLayout, eventInfoFragment)


                    fragmentTransaction.commit();


                    var bilde = items.get(position).image

                    var tittel = items.get(position).title

                    var addresse = items.get(position).addresse

                    var body = items.get(position).body



                        fragmentTransaction.runOnCommit {

                        eventInfoFragment.getTextView(R.id.event_info_author).setText("bruker01")

                        eventInfoFragment.getTextView(R.id.event_info_title).setText(tittel)

                        eventInfoFragment.getTextView(R.id.event_info_body).setText(body)

                        eventInfoFragment.getTextView(R.id.event_info_sted).setText(addresse)

                        var eventInfoImageView = eventInfoFragment.getImageView(R.id.event_info_image);

                        Glide.with(eventInfoFragment).load(bilde).into(eventInfoImageView);


                    }



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
            eventAuthor.text = dataModel.addresse

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
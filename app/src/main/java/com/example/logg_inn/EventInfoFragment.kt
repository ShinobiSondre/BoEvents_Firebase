package com.example.logg_inn

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.logg_inn.ui.home.HomeFragment
import com.example.logg_inn.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_event_info.*


class EventInfoFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var tittel: TextView

    lateinit var root: View

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        root = inflater.inflate(R.layout.fragment_event_info, container, false)



       val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {

           val intent = Intent(activity, MainActivity::class.java)
           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
           intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT)
           startActivity(intent)



        }




        return root


    }


    fun getTextView(textviewid:Int): TextView {
        return root.findViewById(textviewid)
    }

    fun getImageView(bildeviewid:Int): ImageView{

        return root.findViewById(bildeviewid)

    }








}









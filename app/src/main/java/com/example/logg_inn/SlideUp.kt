package com.example.logg_inn
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SlideUp : Fragment() {

        //2
        companion object {

            fun newInstance(): SlideUp {
                return SlideUp()
            }
        }

        //3
        override fun onCreateView(inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            return (inflater.inflate((R.layout.slideup), container, false))
        }

    }


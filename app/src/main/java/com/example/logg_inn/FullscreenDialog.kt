package com.example.logg_inn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment


class FullscreenDialog : DialogFragment(), View.OnClickListener {
    private var callback: Callback? = null
    fun setCallback(callback: Callback?) {
        this.callback = callback
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fullscreen_dialog, container, false)
        val close = view.findViewById<ImageButton>(R.id.fullscreen_dialog_close)
        val action = view.findViewById<TextView>(R.id.fullscreen_dialog_action)
        close.setOnClickListener(this)
        action.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.fullscreen_dialog_close -> dismiss()
            R.id.fullscreen_dialog_action -> {
                callback!!.onActionClick("Whatever")
                dismiss()
            }
        }
    }

    interface Callback {
        fun onActionClick(name: String?)
    }

    companion object {
        fun newInstance(): FullscreenDialog {
            return FullscreenDialog()
        }
    }
}
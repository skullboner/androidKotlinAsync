package com.example.adrian.async

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TEXT_STATE = "currentText"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            textview1.text = savedInstanceState.getString(TEXT_STATE)
        }
    }

    fun startTasK(view: View) {
        textview1.setText(R.string.napping)
        SimpleAsyncTask(::handleUpdateText).execute()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE,  textview1.text.toString())
    }

    private fun handleUpdateText(progress:Int, text: String) {
        progress_bar.progress = progress
        textview1.text = text
    }


}

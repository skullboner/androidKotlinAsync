package com.example.adrian.async

import android.os.AsyncTask
import java.util.*


const val PROGRESS_INTERVAL = 4

class SimpleAsyncTask(val updateText: (y: Int, x: String) -> Unit) : AsyncTask<Void, Int, String>() {


    override fun doInBackground(vararg params: Void?): String {
        val r = Random()
        val n = r.nextInt(11)
        val s = n * 100

        fun sleep(time: Int) {
            if (time > 0) {
                if (time % PROGRESS_INTERVAL == 0) {
                    val progressPercent = 100 - (time.toDouble() / s.toDouble() * 100).toInt()
                    publishProgress(progressPercent, time)
                }
                Thread.sleep(1L)
                sleep(time-1)
            }
        }


        try {
            sleep(s)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        return "Awake afer sleepig for $s seconds"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        updateText(values[0]!!, "${values[0]}  - ${values[1]}")
    }

    override fun onPostExecute(result: String) {
        updateText(100, result)
    }


}



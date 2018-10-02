package com.example.adrian.async

import android.os.AsyncTask
import java.util.*
import kotlin.system.measureTimeMillis


const val PROGRESS_INTERVAL = 10

class SimpleAsyncTask(val updateText: (y: Int, x: String) -> Unit) : AsyncTask<Void, Int, String>() {


    override fun doInBackground(vararg params: Void?): String {
        val r = Random()
        val n = r.nextInt(11)
        val s = n * 100


        fun sleep(time: Int) {
            if (time > 0) {
                val progressPercent = 100 - (time.toDouble() / s.toDouble() * 100).toInt()
                if (progressPercent % PROGRESS_INTERVAL == 0) {
                    publishProgress(progressPercent, time)
                }
                Thread.sleep(1L)
                sleep(time-1)
            }
        }


        try {
            val time = measureTimeMillis {
                sleep(s)
            }
            return "Awake afer sleepig for $s seconds. actually took $time"
        } catch (e: InterruptedException) {
            return e.stackTrace.toString()
        }
    }

    override fun onProgressUpdate(vararg values: Int?) {
        updateText(values[0]!!, "${values[0]}  - ${values[1]}")
    }

    override fun onPostExecute(result: String) {
        updateText(100, result)
    }


}



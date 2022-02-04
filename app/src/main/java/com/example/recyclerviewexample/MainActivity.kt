package com.example.recyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.recyclerviewexample.Model.Task
import com.example.recyclerviewexample.adapter.CustomAdapter
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    var taskList = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val customAdapter = CustomAdapter(taskList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = customAdapter

        val url = "https://api.npoint.io/154f32b1a6eea7ef6372"

        val request = JsonArrayRequest(Request.Method.GET, url, null, Response.Listener {
                response ->
            try {
               for(i in 0 until response.length()){
                   val jsonObject = response.getJSONObject(i)
                   val title = jsonObject.getString("title")
                   Log.i("Title", title)
                   val time = jsonObject.getString("time")
                   Log.i("Time", time)

                   val todoArr = jsonObject.getJSONArray("todo")
                   val firstTask = todoArr.getString(0)
                   Log.i("First Task", firstTask)
                   val secondTask = todoArr.getString(1)
                   Log.i("Second Task", secondTask)

                   val task = Task(title,time, firstTask, secondTask)
                   taskList.add(task)
               }
                customAdapter.notifyDataSetChanged()

            }catch (e: JSONException){
                Log.e("ParseErr", e.toString())
            }
        }, Response.ErrorListener {
                error ->
            Log.e("ResponseErr", error.toString())
        })

        VolleySingleton.getInstance(applicationContext).addToRequestQueue(request)
    }
}
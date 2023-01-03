package ru.myrkwill.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import ru.myrkwill.androidapp.databinding.ActivityMainBinding

const val API_KEY = "999fe75cf7934588b45125034230301"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bGet.setOnClickListener {
            getResult("London")
        }
    }

    private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json" +
                "?key=$API_KEY&q=$name&aqi=no"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            val obj = JSONObject(response)
            val temp = obj.getJSONObject("current")

            Log.d("MyLog", "Response: ${temp.getString("temp_c")}")
        }, {
            Log.d("MyLog", "Volley error: $it")
        })
        queue.add(stringRequest )
    }
}
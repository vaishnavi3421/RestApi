package eu.tutorial.restapi

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import eu.tutorial.restapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    var data = ArrayList<Products>()
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycleView.layoutManager = LinearLayoutManager(this)

        getAllProducts()
    }

    private fun getAllProducts() {
        val retrofit = ServiceBuilder.buildService(ServiceInterface::class.java)
        retrofit.getAllProduct().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                processApiResponse(response)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("Failed", "Api Failed" + t.message)
            }

        })
    }

    private fun processApiResponse(response: Response<ApiResponse>) {

        try {

            val responseBody = response.body()!!
            data = responseBody.products

            var adapterr = ProductAdapter(data)
            binding.recycleView.adapter = adapterr


        } catch (ex: Exception) {
            ex.printStackTrace()

        }
    }
}
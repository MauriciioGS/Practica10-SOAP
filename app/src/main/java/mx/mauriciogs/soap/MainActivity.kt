package mx.mauriciogs.soap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import mx.mauriciogs.soap.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val executor = Executors.newSingleThreadExecutor()
    private val myHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            val dataOne = binding.parameter1.text.toString().trim()
            val datatwo = binding.parameter2.text.toString().trim()
            getService(dataOne, datatwo)
        }
    }

    private fun getService(dataOne: String, datatwo: String) {
        executor.execute {
            val response = CallService().callApi(Utils.METHOD,dataOne, datatwo)
            myHandler.post {
                try {
                    binding.resultado.text = response
                } catch (exc: Exception){
                    exc.printStackTrace()
                }
            }
        }
    }


}
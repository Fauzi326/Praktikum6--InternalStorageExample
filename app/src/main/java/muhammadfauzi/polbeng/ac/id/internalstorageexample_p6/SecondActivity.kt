package muhammadfauzi.polbeng.ac.id.internalstorageexample_p6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import muhammadfauzi.polbeng.ac.id.internalstorageexample_p6.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    override fun onResume() {
        super.onResume()
        loadData()
    }
    private fun loadData() {
        Thread {
            val input = openFileInput(MainActivity.FILE_NAME)
            input.use {
                var buffer = StringBuilder()
                var bytesRead = input.read()
                while (bytesRead != -1) {
                    buffer.append(bytesRead.toChar())
                    bytesRead = input.read()
                }
                runOnUiThread {
                    binding.tvOutputText.text = buffer.toString()
                }
            }
        }.start()
    }
}
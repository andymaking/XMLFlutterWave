package io.king.xmlflutter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.flutterwave.raveandroid.RaveConstants
import com.flutterwave.raveandroid.RavePayActivity
import com.flutterwave.raveandroid.RavePayManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fName = fName
        val lName = lName
        val email = email
        val narration = naration
        val secretKey = "FLWPUBK_TEST-8bab8ffe4fbaa17012d67b90ae60ea13-X"

        submit_button.setOnClickListener {
            RavePayManager(this@MainActivity)
                .setAmount(1000.0)
                .setCountry("NG")
                .setCurrency("NGN")
                .setEmail("$email")
                .setfName("$fName")
                .setlName("$lName")
                .setNarration("$narration")
                .setPublicKey(secretKey)
                .setTxRef(Calendar.getInstance().timeInMillis.toString() + "")
                .acceptAccountPayments(true)
                .acceptCardPayments(true)
                .onStagingEnv(true)
                .setEncryptionKey("FLWSECK_TEST89a887af0af8")
                .allowSaveCardFeature(true)
                .initialize()
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RaveConstants.RAVE_REQUEST_CODE && data != null) {
            val message: String? = data.getStringExtra("response")
            when (resultCode) {
                RavePayActivity.RESULT_SUCCESS -> {
                    Toast.makeText(this, "SUCCESS $message", Toast.LENGTH_SHORT).show()
                }
                RavePayActivity.RESULT_ERROR -> {
                    Toast.makeText(this, "ERROR $message", Toast.LENGTH_SHORT).show()
                }
                RavePayActivity.RESULT_CANCELLED -> {
                    Toast.makeText(this, "CANCELLED $message", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
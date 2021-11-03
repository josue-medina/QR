package com.example.qr

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qr.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onChildTitleChanged(childActivity: Activity?, title: CharSequence?) {
        super.onChildTitleChanged(childActivity, title)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{ setUpQRCode() }
    }

    private fun setUpQRCode() {
        IntentIntegrator(this)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            .setBeepEnabled(true)
            .setPrompt("SELECCIONE CODIGO QR")
            .initiateScan()

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)

        if (result != null) {
            if (result.contents != null) {
                // textView
                binding.textView.text = result.contents
            }else{
                Toast.makeText(this,"Cancelled", Toast.LENGTH_SHORT).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}
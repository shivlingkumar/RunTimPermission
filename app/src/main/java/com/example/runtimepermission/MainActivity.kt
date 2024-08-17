package com.example.runtimepermission

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.example.runtimepermission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            requestRuntimePermissionForMusic()
        }
        binding.button2.setOnClickListener {
            requestRuntimePermissionForImages()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestRuntimePermissionForMusic() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request READ_MEDIA_AUDIO permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_MEDIA_AUDIO),
                13
            )
        } else {
            Toast.makeText(this, "Music file permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestRuntimePermissionForImages() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_MEDIA_IMAGES
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request READ_MEDIA_IMAGES permission
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_MEDIA_IMAGES),
                14
            )
        } else {
            Toast.makeText(this, "Image file permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            13 -> {
                // Handle the result for READ_MEDIA_AUDIO permission
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Music file permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Music file permission not granted", Toast.LENGTH_SHORT).show()
                }
            }
            14 -> {
                // Handle the result for READ_MEDIA_IMAGES permission
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Image file permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Image file permission not granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

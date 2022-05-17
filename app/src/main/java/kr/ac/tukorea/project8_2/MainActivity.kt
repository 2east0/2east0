package kr.ac.tukorea.project8_2

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.io.FileFilter

class MainActivity : AppCompatActivity() {
    lateinit var btnPrev : Button
    lateinit var btnNext : Button
    lateinit var myPicture : myPictureView
    var curNum : Int = 0
    var imageFiles : Array<File>? = null
    lateinit var imageFname : String
    lateinit var tvNumber : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "간단 이미지 뷰어"
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), Context.MODE_PRIVATE)

        btnPrev = findViewById<Button>(R.id.btnPrev)
        btnNext = findViewById<Button>(R.id.btnNext)
        myPicture = findViewById<myPictureView>(R.id.myPictureView1)
        tvNumber = findViewById<TextView>(R.id.tvNumber)

        imageFiles = File(Environment.getExternalStorageDirectory().absolutePath + "/Pictures").listFiles(
            FileFilter { file -> file.name.startsWith(".") })
        imageFname = imageFiles!![0].toString()
        myPicture.imagePath=imageFname
        tvNumber.setText("1"+"/"+imageFiles!!.size)

        btnPrev.setOnClickListener {
            if (curNum <= 0) {
                curNum = imageFiles!!.size - 1
            } else {
                curNum--
            }
            imageFname = imageFiles!![curNum].toString()
            myPicture.imagePath=imageFname
            myPicture.invalidate()
            tvNumber.setText((curNum+1).toString() + "/" + imageFiles!!.size)
        }

        btnNext.setOnClickListener {
            if (curNum >= imageFiles!!.size - 1) {
                Toast.makeText(applicationContext, "마지막 그림입니다", Toast.LENGTH_SHORT).show()
            } else {
                curNum++
            }
            imageFname = imageFiles!![curNum].toString()
            myPicture.imagePath=imageFname
            myPicture.invalidate()
            tvNumber.setText((curNum+1).toString() + "/" + imageFiles!!.size)
        }
    }
}
package com.example.rxjava

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btnSimple:Button
    lateinit var btnMap:Button
    lateinit var btnZip:Button
    lateinit var btnTimer:Button
    lateinit var btnFilter:Button
    lateinit var btnConcat:Button
    lateinit var btnMerge:Button
    lateinit var btnDelay:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSimple=findViewById(R.id.btnSimple)
        btnMap=findViewById(R.id.btnMap)
        btnZip=findViewById(R.id.btnZip)
        btnTimer=findViewById(R.id.btnTimer)
        btnFilter=findViewById(R.id.btnFilter)
        btnConcat=findViewById(R.id.btnConcat)
        btnMerge=findViewById(R.id.btnMerge)
        btnDelay=findViewById(R.id.btnDelay)

        btnSimple.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Simple")
            startActivity(intent)
        }

        btnMap.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Map")
            startActivity(intent)
        }

        btnZip.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Zip")
            startActivity(intent)
        }

        btnTimer.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Timer")
            startActivity(intent)
        }

        btnFilter.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Filter")
            startActivity(intent)
        }

        btnConcat.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Concat")
            startActivity(intent)
        }


        btnMerge.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Merge")
            startActivity(intent)
        }

        btnDelay.setOnClickListener {
            val intent= Intent(this,SecondActivity::class.java)
            intent.putExtra("key","Delay")
            startActivity(intent)
        }

    }

}
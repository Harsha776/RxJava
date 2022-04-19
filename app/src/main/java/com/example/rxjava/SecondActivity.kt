package com.example.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.MainThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import java.util.concurrent.TimeUnit

class SecondActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var titleText : TextView
    lateinit var textVal : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        btn=findViewById(R.id.btn)
        titleText=findViewById(R.id.title)
        textVal=findViewById(R.id.text)
        val title= intent.extras?.get("key")
        titleText.text=title.toString()
        btn.setOnClickListener {
            if(title.toString().equals("Simple")){
                getSimpleObservable().subscribe(getSimpleObserver())
            }else if(title.toString().equals("Map")){
                getMapObservable().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map {it->
                        val data=ArrayList<String>()
                        for(value in it.indices)
                            data.add(it[value]+" Map")
                        Log.d("TAG", "onCreate: "+data)
                        return@map data
                    }
                    .subscribe(getMapObserver())
            }else if(title.toString().equals("Zip")){
                Observable.zip(getZipObservable1(),getZipObservable2(),
                BiFunction<String,String,String>{a,b->
                    for (value in a.indices){
                        if(a.contains(b))
                            return@BiFunction a
                    }
                    return@BiFunction "no result"


                }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getZipObserver())


            }else  if(title.toString().equals("Filter")){
                getFilterObservable1().filter {  it->
                    return@filter it.length>=6
                }
                    .subscribe(getFilterObserver())
            }else  if(title.toString().equals("Concat")){
                Observable.concat(getZipObservable1(),getZipObservable2())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getZipObserver())
            }else  if(title.toString().equals("Merge")){
                Observable.merge(getZipObservable1(),getZipObservable2())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getZipObserver())
            }
            else  if(title.toString().equals("Delay")){

               /* var replay:PublishSubject<String> = PublishSubject.create()

                getZipObservable1().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(replay)
                replay.subscribe(getZipObserver())
                replay.onNext("done")
                replay.subscribe(getZipObserver())*/

               /* var replay:BehaviorSubject<String> = BehaviorSubject.create()

                getZipObservable1().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(replay)
                replay.subscribe(getZipObserver())
                replay.onNext("done")
                replay.subscribe(getZipObserver())*/


              /*  var replay:ReplaySubject<String> = ReplaySubject.create()
                getZipObservable1().subscribe(replay)
                replay.subscribe(getZipObserver())
                replay.subscribe(getZipObserver())*/


                 Observable.merge(getZipObservable1(),getZipObservable2())
                     .delay(2,TimeUnit.SECONDS)
                     .subscribeOn(Schedulers.io())
                     .observeOn(AndroidSchedulers.mainThread())
                     .subscribe(getZipObserver())
            }

        }
    }

    fun getFilterObservable1():Observable<String>{
        return Observable.just("Harsha","Hemanth","gowda")
    }

    fun getFilterObserver():Observer<String> {
        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                val str = textVal.text.toString()
                textVal.text = "$str \nonSubscribe:"
                Log.d("TAG", "onSubscribe: ")
            }

            override fun onNext(t: String) {
                val str = textVal.text.toString()
                textVal.text = "$str \n $t"
                Log.d("TAG", "onNext: " + t)
            }

            override fun onError(e: Throwable) {
                val str = textVal.text.toString()
                textVal.text = "$str \n onError:"
                Log.d("TAG", "onError: ")
            }

            override fun onComplete() {
                val str = textVal.text.toString()
                textVal.text = "$str \n onComplete:"
                Log.d("TAG", "onComplete: ")
            }

        }
    }

    /////////////
    fun getZipObservable1():Observable<String>{
        return Observable.just("Harsha","Hemanth","gowda")
    }

    fun getZipObservable2():Observable<String>{
        return Observable.just("Harsha","Hemanth","Gowtham")
    }

    fun getZipObserver():Observer<String>{
        return object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                val str=textVal.text.toString()
                textVal.text= "$str \nonSubscribe:"
                Log.d("TAG", "onSubscribe: ")
            }

            override fun onNext(t: String) {
                val str=textVal.text.toString()
                textVal.text= "$str \n $t"
                Log.d("TAG", "onNext: "+t)
            }

            override fun onError(e: Throwable) {
                val str=textVal.text.toString()
                textVal.text= "$str \n onError:"
                Log.d("TAG", "onError: ")
            }

            override fun onComplete() {
                val str=textVal.text.toString()
                textVal.text= "$str \n onComplete:"
                Log.d("TAG", "onComplete: ")
            }

        }
    }

    ///////////////////////////////////////
    fun getMapObservable():Observable<ArrayList<String>>{
        var list= ArrayList<String>()
        list.add("Harsha")
        list.add("Siddegowda")
        list.add("Baby")
        return Observable.create{
            it.onNext(list)
            it.onComplete()
        }
    }

    fun getMapObserver():Observer<ArrayList<String>> {
        return object : Observer<ArrayList<String>>{
            override fun onSubscribe(d: Disposable) {
                val str = textVal.text.toString()
                textVal.text = "$str \nonSubscribe:"
                Log.d("TAG", "onSubscribe: ")
            }

            override fun onNext(t: ArrayList<String>) {
                val str = textVal.text.toString()
                textVal.text = "$str \n $t"
                Log.d("TAG", "onNext: " + t)
            }

            override fun onError(e: Throwable) {
                val str = textVal.text.toString()
                textVal.text = "$str \n onError:"
                Log.d("TAG", "onError: ")
            }

            override fun onComplete() {
                val str = textVal.text.toString()
                textVal.text = "$str \n onComplete:"
                Log.d("TAG", "onComplete: ")
            }

        }
    }

/////////////
    fun getSimpleObservable():Observable<String>{
        return Observable.just("Harsha","Sidde","Gowda")
    }
    fun getSimpleObserver():Observer<String>{
        return object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                val str=textVal.text.toString()
                textVal.text= "$str \nonSubscribe:"
                Log.d("TAG", "onSubscribe: ")
            }

            override fun onNext(t: String) {
                val str=textVal.text.toString()
                textVal.text= "$str \n $t"
                Log.d("TAG", "onNext: "+t)
            }

            override fun onError(e: Throwable) {
                val str=textVal.text.toString()
                textVal.text= "$str \n onError:"
                Log.d("TAG", "onError: ")
            }

            override fun onComplete() {
                val str=textVal.text.toString()
                textVal.text= "$str \n onComplete:"
                Log.d("TAG", "onComplete: ")
            }

        }
    }
}
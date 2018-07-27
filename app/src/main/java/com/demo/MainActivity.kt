package com.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.demo.adapter.AccountAdapter
import com.demo.entity.Result
import com.demo.restservices.APIService
import com.demo.restservices.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    val realm by lazy { Realm.getDefaultInstance() }
    private lateinit var list: ArrayList<Result>
    private val TAG = MainActivity::class.java.simpleName
    private lateinit var apiServices: APIService
    private lateinit var context: AppCompatActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        apiServices = ApiUtils.apiService

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .name("person.realm").build()
        Realm.setDefaultConfiguration(config)

        list = ArrayList()

        rv_animal_list.layoutManager = LinearLayoutManager(this)

        initJson()
    }


    private fun initJson() {
        list.clear()
        progress.visibility = View.VISIBLE
        val m = Result()
        apiServices.getCategoryShopAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d(TAG, "Res: ${it.result}")
                            deleteAll()
                            for (r: Result in it.result) {
                                m.id = r.id
                                m.accountName = r.accountName
                                m.acountNo = r.acountNo
                                m.mobile = r.mobile
                                m.address = r.address
                                m.acType = r.acType
                                addOffline(m)
                            }
                            progress.visibility = View.GONE
                            showData()
                        },
                        { error ->
                            progress.visibility = View.GONE
                            Log.d(TAG, "ERROR ${error.message}")
                            showData()
                        }
                )
    }

    private fun addOffline(result: Result) {
        realm.executeTransaction { realm.copyToRealm(result) }
    }

    private fun showData() {
        val results = realm.where(Result::class.java).findAll()
        for (ll in results) {
            list.add(ll)
        }
        rv_animal_list.adapter = AccountAdapter(list)
    }

    private fun deleteAll(){
        realm.executeTransaction { realm -> realm.deleteAll() }
    }

}

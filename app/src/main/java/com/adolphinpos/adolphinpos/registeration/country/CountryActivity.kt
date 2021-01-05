package com.adolphinpos.adolphinpos.registeration.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.CountryAdapter
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.activity_country.*

class CountryActivity : AppCompatActivity(),CountryDelegate, CountryAdapter.OnItemClickedDelegate {

    private lateinit var countryAdapter: CountryAdapter
    var mPresenter: CountryPresenter?=null
    var countryModel: ArrayList<CountryModel.Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        countryAdapter = CountryAdapter(countryModel,this )
        countryAdapter!!.setOnClickCountry(this)

        mPresenter=CountryPresenter(this)
        mPresenter!!.delegate = this
        val llm = GridLayoutManager(this, 2)

        recyclerView.layoutManager = llm
        recyclerView.adapter =countryAdapter

        getCountry()

    }




    override fun didGetCountrySuccess(response: CountryModel) {

        hideLoaderCell()
        countryModel.clear()
        countryAdapter.notifyDataSetChanged()
        runOnUiThread {
            countryModel.addAll(response.data)
            Log.d("WWWWWWWWWWW", countryModel[1].name)

            countryAdapter.notifyDataSetChanged()
            countryAdapter = CountryAdapter(countryModel, this)
            recyclerView.adapter = countryAdapter
        }

    }
    private fun emptyCell() {
        hideLoaderCell()
        countryModel.clear()
        runOnUiThread {
            countryAdapter.notifyDataSetChanged()
//            animation_view.visibility = View.VISIBLE
//            progress_bar.visibility = View.GONE
//            recyclerView.visibility = View.GONE
        }

    }
    fun hideLoaderCell() {

//        swipe_container!!.setRefreshing(false);
        for (i in countryModel.indices) {

            if (countryModel[i].type == "loader" || countryModel[i].type == "empty") {
                countryModel.removeAt(i)
            }


        }


    }
    fun getCountry() {

        countryModel.clear()


        countryAdapter!!.notifyDataSetChanged()

//        countryModel.add(CountryModel.Data(type = "loader"))


//        if (countryModel[0].type == "loader" || countryModel.size == 0)
        mPresenter!!.getCountry()

    }
    override fun didGetCountryFail(msg: String) {
        hideLoaderCell()
        runOnUiThread {
            emptyCell()
        }
    }

    override fun didEmpty() {
        hideLoaderCell()
        runOnUiThread {
            emptyCell()
        }
    }

    override fun setOnClickCountry(position: Int) {

    }
}
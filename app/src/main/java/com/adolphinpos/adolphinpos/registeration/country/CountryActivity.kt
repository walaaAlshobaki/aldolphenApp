package com.adolphinpos.adolphinpos.registeration.country

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.RecyclerAdapter
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.activity_country.*
import java.lang.Exception

class CountryActivity : AppCompatActivity(),CountryDelegate, RecyclerAdapter.OnItemselectedDelegate {

    private lateinit var countryAdapter: RecyclerAdapter
    private lateinit var recyclerVieww: RecyclerView
    var mPresenter: CountryPresenter?=null
    var countryModel: ArrayList<CountryModel.Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
//        val county=CountryModel.Data("ffff","ddddd","sdsddsds","sdsddsds",1,"ddddffd","ddddd")
//        countryModel.add(county)
//        countryModel.add(county)
//        countryModel.add(county)
//        countryModel.add(county)
//        countryModel.add(county)
//        countryModel.add(county)
//        countryModel.add(county)




        countryAdapter = RecyclerAdapter(baseContext,countryModel )
        mPresenter=CountryPresenter(this)
        mPresenter!!.delegate = this

        countryModel.clear()
        mPresenter!!.getCountry()


        recyclerVieww = recyclerView

        recyclerVieww.adapter = countryAdapter

        val llm = GridLayoutManager(this, 4)
        recyclerVieww.layoutManager = llm




    }





    override fun didGetCountrySuccess(response: CountryModel) {
        try {

            countryModel.clear()
            countryModel.addAll(response.data!!)
            countryAdapter!!.notifyDataSetChanged()

        } catch (ex: Exception) {

            Log.d("apiExepction inside", ex.toString())


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

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectLesson(position: Int) {

    }

    override fun onSelectQuiz(position: Int) {

    }

    override fun onSelectHomework(position: Int) {

    }

    override fun onSelectvc(position: Int) {

    }

    override fun onSelectshowOption(position: Int) {

    }


}
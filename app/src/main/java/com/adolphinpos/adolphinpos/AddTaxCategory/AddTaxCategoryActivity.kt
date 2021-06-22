package com.adolphinpos.adolphinpos.AddTaxCategory

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.Adapters.MySpinnerAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.companyBranch.CityDelegate
import com.adolphinpos.adolphinpos.companyBranch.CityModel
import com.adolphinpos.adolphinpos.companyBranch.CityPresenter
import com.adolphinpos.adolphinpos.registeration.country.CountryDelegate
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.adolphinpos.adolphinpos.registeration.country.CountryPresenter
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_add_tax_category.*


class AddTaxCategoryActivity : AppCompatActivity(), CountryDelegate, CityDelegate,
    CompanyTaxCategoryDelegate {
    var countryModel: ArrayList<CountryModel.Data> = ArrayList()
    var mPresenter: CountryPresenter? = null
    var mCompanyTaxCategoryPresenter: CompanyTaxCategoryPresenter? = null

    var cityPresenter: CityPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_tax_category)
        val window = this.window
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mPresenter = CountryPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getCountry()

        cityPresenter = CityPresenter(this)
        cityPresenter!!.delegate = this
        mCompanyTaxCategoryPresenter = CompanyTaxCategoryPresenter(this)
        mCompanyTaxCategoryPresenter!!.delegate = this

        confirm.setOnClickListener {
            if (taxName.text.toString().isNullOrEmpty() || Percentage.text.toString()
                    .isNullOrEmpty()
            ) {
                DesignerToast.Custom(
                    this, "All filed is required", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.warnings_background, 16, "#FFFFFF", R.drawable.ic_warninges, 55, 219
                );
            } else {
                mCompanyTaxCategoryPresenter!!.addCompanyTaxCategory(taxName.text.toString())
            }

        }


    }

    override fun didGetCountrySuccess(response: CountryModel) {
        countryModel.clear()
        countryModel.addAll(response.data)

        val mSpinnerAdapter = MySpinnerAdapter(
            this,
            R.layout.spinner_row, R.id.text, countryModel
        )


        mSpinnerAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item)
        countrySpinner.adapter = mSpinnerAdapter

        countrySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {

                common.selectedCountry = response.data[position].id!!
                cityPresenter!!.getCity(response.data[position].id!!)
//                (parent!!.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.appMainColor))

                Log.d("DDDDDDDDDDDDDDDD", response.data[position].name!!)
                countrySpinner.setSelection(position)


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        for (i in response.data.indices) {
            if (response.data[i].id == userInfo.contryId) {
                countrySpinner.setSelection(i)
                cityPresenter!!.getCity(response.data[i].id)
                common.selectedCountry = response.data[i].id!!

            }

        }

    }

    override fun didGetCountryFail(msg: String) {

    }

    override fun didGetCitySuccess(response: CityModel) {
        val dataCreateByStrings = Array<String>(response.data.size) { "" }
        for (i in response.data.indices) {
            dataCreateByStrings[i] = response.data[i].name.toString()
        }
        val aa =
            ArrayAdapter<Any>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                dataCreateByStrings
            )

        aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
        citySpinner.adapter = aa

        citySpinner.setSelection(0, true)
        val v: View = citySpinner.getSelectedView()
        (v as TextView).setTextColor(resources.getColor(R.color.appMainColor))

        citySpinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {

                common.selectedCity = response.data[position].id!!

                (parent!!.getChildAt(0) as TextView).setTextColor(resources.getColor(R.color.appMainColor))

                Log.d("DDDDDDDDDDDDDDDD", response.data[position].name!!)
                citySpinner.setSelection(position)


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun didGetCityFail(msg: String) {

    }

    override fun didEmpty() {

    }

    override fun didAddCompanyTaxCategorySuccess(response: Int) {
        mCompanyTaxCategoryPresenter!!.addCompanyTaxCategoryRate(
            response, common.selectedCountry,
            common.selectedCity, Percentage.text.toString().toInt()
        )

    }

    override fun didAddCompanyTaxCategoryFail(msg: String) {
        if (msg == "Added" || msg == "true") {

        }
    }

    override fun didAddCompanyTaxCategoryRateSuccess(response: CategoryTaxModel) {


        DesignerToast.Custom(
            this, "successfully Added",
            Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.sacssful_background, 16, "#FFFFFF", R.drawable.ic_checked, 55, 219
        )
    }

    override fun didAddCompanyTaxCategoryRateFail(msg: String) {
        if (msg == "Added" || msg == "true") {


            DesignerToast.Custom(
                this, "successfully Added",
                Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.sacssful_background, 16, "#FFFFFF", R.drawable.ic_checked, 55, 219
            )
        }
    }

    override fun didGetCompanyTaxCategorySuccess(response: TaxCategoryModel) {

    }

    override fun didGetCompanyTaxCategoryFail(msg: String) {

    }
}
package com.mep.kotlinespresso

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    companion object{
        val COUNTRY : String  = "country"
        val CODE : String  = "locale"
        val REQUEST_CODE = 1000

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val country = intent.getStringExtra(COUNTRY)
        if(country == null)
            locale.text = getString(R.string.locale_no)
        else{

            val ret : String = when(country){
                getString(R.string.country_korea) -> getString(R.string.locale_korea)
                getString(R.string.country_usa) -> getString(R.string.locale_usa)
                getString(R.string.country_canada) -> getString(R.string.locale_canada)
                else -> getString(R.string.locale_no)
            }

            locale.text = ret
        }


        button2.setOnClickListener {

            val retIntent = Intent()
            retIntent.putExtra(SecondActivity.CODE, locale.text.toString())
            setResult(Activity.RESULT_OK, retIntent)

            onBackPressed()

        }
    }
}

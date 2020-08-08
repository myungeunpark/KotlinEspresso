package com.mep.kotlinespresso

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            val intent = Intent(this, SecondActivity::class.java)
            if(editText.text.isEmpty()){

                intent.putExtra(SecondActivity.COUNTRY, getString(R.string.country_korea))

            }else{

                intent.putExtra(SecondActivity.COUNTRY, editText.text.toString())
            }
            startActivityForResult(intent, SecondActivity.REQUEST_CODE)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == SecondActivity.REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){

                val code = data?.getStringExtra(SecondActivity.CODE)
                result_code.text = code
            }
        }
    }
}

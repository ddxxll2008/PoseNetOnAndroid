package org.tensorflow.lite.examples.posenet

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        btn_start.setOnClickListener {
            //因为前置镜头是反向的，所以左右相反
            var isLeftHand = true
            if (rb_left.isChecked) {
                isLeftHand = false
            }
            var intent = Intent()
            intent.putExtra(IS_LEFT_HAND, isLeftHand)
            intent.setClass(this, PosenetActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
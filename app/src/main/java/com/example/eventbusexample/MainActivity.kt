package com.example.eventbusexample

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    var btnClick: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick = findViewById(R.id.btnClick)
        btnClick!!.setOnClickListener(View.OnClickListener { //call eventBus when click button
            EventBus.getDefault().post(MessageToastEvent("Button clicked by Palash"))
        })
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun eventToast(messageToastEvent: MessageToastEvent) {
        Toast.makeText(this@MainActivity, messageToastEvent.message, Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
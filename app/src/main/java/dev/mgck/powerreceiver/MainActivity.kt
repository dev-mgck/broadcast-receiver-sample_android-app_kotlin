package dev.mgck.powerreceiver

import android.content.Intent
import android.content.IntentFilter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    // CustomReceiver object
    private var mReceiver = CustomReceiver()

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "dev.mgck.powerreceiver.ACTION_CUSTOM_BROADCAST"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initReceiver()

    }

    override fun onDestroy() {
        // Registro retirado ao fechar app
        this.unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    fun initReceiver() {
        // Instância do objeto IntentFilter
        val filter = IntentFilter()
        // O sitema recebe mensagem de tranmissão baseado no valor especificado no IntentFilter
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        filter.addAction(ACTION_CUSTOM_BROADCAST)

        // Registro do receptor através da atividade corrente
        this.registerReceiver(mReceiver, filter)
    }

    fun sendCustomBroadcast(view: View) {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        customBroadcastIntent.putExtra("key", " This app is ready!")
        sendBroadcast(customBroadcastIntent)
    }
}
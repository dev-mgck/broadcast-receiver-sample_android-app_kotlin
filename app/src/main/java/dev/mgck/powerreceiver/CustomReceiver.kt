package dev.mgck.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.widget.Toast

class CustomReceiver : BroadcastReceiver() {

    companion object {
        const val ACTION_CUSTOM_BROADCAST = "dev.mgck.powerreceiver.ACTION_CUSTOM_BROADCAST"
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.

        val intentAction : String? = intent.action
        if (intentAction != null ) {
            var toastMessage = "unknown intent action"
            when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> toastMessage = "Power Connected!"
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = "Power disconnected!"
                ACTION_CUSTOM_BROADCAST -> toastMessage = "Custom Broadcast Received." +
                        intent.getStringExtra("key")
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}

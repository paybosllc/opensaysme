package com.example.opensaysme

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class DeviceConnectionReceiver: BroadcastReceiver() {
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action
        val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
        if(action.equals(BluetoothDevice.ACTION_ACL_CONNECTED)) {
            Toast.makeText(context, "Connected to ${device?.name}", Toast.LENGTH_SHORT).show()
        } else if(action.equals(BluetoothDevice.ACTION_ACL_DISCONNECTED)) {
            Toast.makeText(context, "${device?.name} disconnected!", Toast.LENGTH_SHORT).show()
        }
    }
}

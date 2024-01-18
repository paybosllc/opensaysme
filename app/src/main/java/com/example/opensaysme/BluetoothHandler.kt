package com.example.opensaysme

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.util.Log
import java.io.IOException
import java.util.UUID

@SuppressLint("MissingPermission")
class BluetoothHandler(
    private val bluetoothAdapter: BluetoothAdapter,
    private val macAddress: String
) : Thread() {
    fun createSocket(device: BluetoothDevice): BluetoothSocket {
        val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        return device.createRfcommSocketToServiceRecord(uuid)
    }

    override fun run() {
        var bluetoothSocket: BluetoothSocket? = null
        if (bluetoothSocket == null || !bluetoothSocket.isConnected) {
            val device = bluetoothAdapter.getRemoteDevice(macAddress)
            try {
                bluetoothSocket = createSocket(device)
            } catch (e: IOException) {
                Log.d("Connect Error", "Error Creating Socket!")
            }

            if (bluetoothSocket != null && bluetoothSocket.isConnected) {
                Log.d("Connecting", "Connecting to ${device.name}")
            } else {
                bluetoothSocket?.close()
            }
        }
    }
}
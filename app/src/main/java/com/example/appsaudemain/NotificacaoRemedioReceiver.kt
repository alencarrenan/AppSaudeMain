package com.example.appsaudemain

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificacaoRemedioReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val nomeRemedio = intent.getStringExtra("nomeRemedio") ?: "Remédio"

        val channelId = "remedio_channel"
        val channelName = "Lembrete de Remédio"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val tapIntent = Intent(context, MeusRemedios::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            tapIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle("Hora do Remédio")
            .setContentText("Tome seu remédio: $nomeRemedio")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(nomeRemedio.hashCode(), notification)
    }
}

package com.example.timeandtune.Presentation.Navigation

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.timeandtune.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private var isMusicPlaying = false

    override fun onBind(intent: Intent?): IBinder? {
        return MusicBinder()
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.campfire)
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isMusicPlaying) {
            mediaPlayer.start()
            isMusicPlaying = true
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        isMusicPlaying = false
    }

    fun playMusic(resId: Int) {
        mediaPlayer.stop()
        mediaPlayer.release()
        mediaPlayer = MediaPlayer.create(this, resId)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        isMusicPlaying = true
    }

    fun stopMusic() {
        if (isMusicPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            isMusicPlaying = false
        }
    }

    inner class MusicBinder : Binder() {
        fun getService(): MusicService = this@MusicService
    }
}


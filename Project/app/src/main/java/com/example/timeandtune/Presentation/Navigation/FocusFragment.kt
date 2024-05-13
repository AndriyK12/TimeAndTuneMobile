package com.example.timeandtune.Presentation.Navigation

import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.timeandtune.R
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class FocusFragment : Fragment(), View.OnClickListener {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.campfire)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.focus_fragment, container, false)

        view.findViewById<ImageView>(R.id.cafeButton).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.rainButton).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.windButton).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.campfireButton).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.trainButton).setOnClickListener(this)
        view.findViewById<ImageView>(R.id.birdsButton).setOnClickListener(this)

        view.findViewById<ExtendedFloatingActionButton>(R.id.stopButton).setOnClickListener {
            stopPlayback()
        }

        view.findViewById<ExtendedFloatingActionButton>(R.id.DNDButton).setOnClickListener {
            toggleDoNotDisturbMode()
        }

        return view
    }

    private fun toggleDoNotDisturbMode() {
        val notificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (notificationManager.isNotificationPolicyAccessGranted) {
            val currentMode = notificationManager.currentInterruptionFilter
            val newMode = if (currentMode == NotificationManager.INTERRUPTION_FILTER_NONE) {
                NotificationManager.INTERRUPTION_FILTER_ALL
            } else {
                NotificationManager.INTERRUPTION_FILTER_NONE
            }

            notificationManager.setInterruptionFilter(newMode)
        }
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cafeButton -> {
                playMusic(R.raw.cafe)
            }
            R.id.rainButton -> {
                playMusic(R.raw.rain)
            }
            R.id.windButton -> {
                playMusic(R.raw.wind)
            }
            R.id.campfireButton -> {
                playMusic(R.raw.campfire)
            }
            R.id.trainButton -> {
                playMusic(R.raw.train)
            }
            R.id.birdsButton -> {
                playMusic(R.raw.birds)
            }

        }
    }

    private fun playMusic(resId: Int) {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(requireContext(), resId)
        }
        mediaPlayer.start()
    }

    private fun stopPlayback() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            mediaPlayer = MediaPlayer.create(requireContext(), R.raw.campfire)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
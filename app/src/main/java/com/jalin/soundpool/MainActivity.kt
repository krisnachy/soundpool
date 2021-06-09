package com.jalin.soundpool

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var soundPool: SoundPool
    private lateinit var runnable: Runnable
    private var soundId: Int = 0
    private var spLoaded = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        soundPool = SoundPool.Builder()
            .setMaxStreams(10)
            .build()
        
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (status == 0) {
                spLoaded = true
            } else {
                Toast.makeText(this@MainActivity, "SoundPool Error", Toast.LENGTH_SHORT).show()
            }
        }

        soundId = soundPool.load(this, R.raw.machine_gun, 1)

        iv_play.setOnClickListener {
            if (spLoaded) {
                soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
            }
        }
    }
}
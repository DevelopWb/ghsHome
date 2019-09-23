package com.ghs.ghshome.models.push;

import android.content.Context;
import android.media.MediaPlayer;

public class MerPlayerManager {

    private MediaPlayer mediaPlayer;

    public MerPlayerManager(Context context,int path) {

        mediaPlayer.reset();
        mediaPlayer = MediaPlayer.create(context,path);
    }

    /**
     * 开始音乐
     */
    public void startMusic() {
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }


    /**
     * 关闭音乐
     */
    public void stopMusic() {
        mediaPlayer.stop();

    }

    public void onDestry() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }


    }
}

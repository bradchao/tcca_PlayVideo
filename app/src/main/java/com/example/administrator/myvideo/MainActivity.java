package com.example.administrator.myvideo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        }else{
            init();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            init();
        }
    }

    private void init(){
        videoView = (VideoView)findViewById(R.id.videoView);

        //videoView.setVideoURI(Uri.parse("http://www.bradchao.com/android/running.mp4"));
        File file = new File("/mnt/ext_sdcard/DCIM/Camera/VID_20170824_143858.mp4");
        videoView.setVideoURI(Uri.fromFile(file));


        //videoView.start();
    }

    public void play(View view) {
        if (!videoView.isPlaying()){
            videoView.start();
        }
    }

    public void pausePlay(View view) {
        if (videoView.isPlaying()){
            videoView.pause();
        }
    }
}

package com.example.android.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MusicData mMusicData;
    private ImageView ivMusicAblum;
    private TextView txMusicTitle;
    private ImageButton btn_play;
    private ImageButton btn_next;
    private ImageButton btn_prev;
    private int position;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial(getIntent().getIntExtra(MusicListActivity.POSITIONINDEX, 0));
        setView();
        createMediaPlayer();
        btn_play.setOnClickListener(this);
        btn_prev.setOnClickListener(this);
        btn_next.setOnClickListener(this);
    }

    public static Intent getmIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    private MusicData getMusicData(int position) {
        return MusicLab.getMusicLab(getBaseContext()).getMusicDatas().get(position);
    }

    private void initial(int position) {
        txMusicTitle = (TextView) findViewById(R.id.tx_music_title);
        ivMusicAblum = (ImageView) findViewById(R.id.iv_music_album);
        btn_play = (ImageButton) findViewById(R.id.btn_play);
        btn_next= (ImageButton) findViewById(R.id.ib_next);
        btn_prev= (ImageButton) findViewById(R.id.ib_prev);
        this.position = position;
        mMusicData = getMusicData(position);
    }

    private void setView() {
        txMusicTitle.setText(mMusicData.getMtitle());
        ivMusicAblum.setImageResource(mMusicData.getMusicPicture());
    }

    @Override
    protected void onStop() {
        super.onStop();
        cancelMediaPlayer(mediaPlayer);
    }

    private void createMediaPlayer() {
        if (mediaPlayer != null) {
            cancelMediaPlayer(mediaPlayer);
        }
        mediaPlayer = MediaPlayer.create(getBaseContext(), mMusicData.getMusicFile());
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                cancelMediaPlayer(mediaPlayer);
            }
        });
    }

    private void cancelMediaPlayer(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
        mediaPlayer=null;
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btn_play:
                if (mediaPlayer.isPlaying()){
                    btn_play.setImageResource(R.drawable.ic_action_name);
                    mediaPlayer.pause();
                }else {
                    btn_play.setImageResource(R.drawable.playing_statue);
                    mediaPlayer.start();
                }
                break;
            case R.id.ib_next:
                position=(position+1)%5;
                mMusicData=getMusicData(position);
                setView();
                createMediaPlayer();
                break;
            case R.id.ib_prev:
                position=(position-1)%5;
                if (position<0){
                    position=4;
                }
                mMusicData=getMusicData(position);
                setView();
                createMediaPlayer();
        }
    }
}

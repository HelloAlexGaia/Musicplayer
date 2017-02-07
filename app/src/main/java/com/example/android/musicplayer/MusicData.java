package com.example.android.musicplayer;

/**
 * Created by 张俊秋 on 2017/2/4.
 */

public class MusicData {
    private int mtitle;
    private int mMusicFile;
    private int mMusicPicture;
    private int mMusicSinger;

    public int getMusicSinger() {
        return mMusicSinger;
    }

    public MusicData(int mtitle, int musicSinger, int musicFile, int musicPicture){
        this.mtitle=mtitle;
        this.mMusicFile=musicFile;
        this.mMusicPicture=musicPicture;
        this.mMusicSinger=musicSinger;
    }

    public int getMtitle() {
        return mtitle;
    }

    public int getMusicFile() {
        return mMusicFile;
    }

    public int getMusicPicture() {
        return mMusicPicture;
    }
}

package com.example.android.musicplayer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张俊秋 on 2017/2/5.
 */

public class MusicLab {
    private static MusicLab sMusicLab;
    private List<MusicData> mMusicDatas;
    private MusicLab(Context context){
        mMusicDatas=new ArrayList<>();
        setMusicDatas(mMusicDatas);
    }
    public static MusicLab getMusicLab(Context context){
        if (sMusicLab==null){
            sMusicLab=new MusicLab(context);
        }
        return sMusicLab;
    }

    public List<MusicData> getMusicDatas(){
        return mMusicDatas;
    }
    public MusicData getMusic(int position){
        return mMusicDatas.get(position);
    }
    public void setMusicDatas(List<MusicData> mMusicDatas){
        if (mMusicDatas!=null){
            mMusicDatas.add(new MusicData(R.string.ori_and_the_blind_forest,R.string.ori_singer,R.raw.light_of_nibel,R.drawable.light_of_nibel));
            mMusicDatas.add(new MusicData(R.string.hi,R.string.hi_singer,R.raw.hi,R.drawable.hi));
            mMusicDatas.add(new MusicData(R.string.no_title,R.string.no_title_singer,R.raw.no_title,R.drawable.no_title_picture));
            mMusicDatas.add(new MusicData(R.string.paradisus_paradoxum,R.string.paradisus_paradoxum_singer,R.raw.paradisus_paradoxum,R.drawable.paradisus_paradoxum));
            mMusicDatas.add(new MusicData(R.string.wild_good_god,R.string.wild_good_god_singer,R.raw.wild_good_god_ed,R.drawable.wild_good_god));
        }
    }
}

package com.example.android.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MusicListActivity extends AppCompatActivity {
    public static final String POSITIONINDEX="position";
    private RecyclerView mRvMusicList;
    private MusicLab mMusicLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        initialData();
        mRvMusicList= (RecyclerView) findViewById(R.id.recycler_view_music_list);
        mRvMusicList.setLayoutManager(new LinearLayoutManager(this));
        mRvMusicList.setAdapter(new MyAdapter(mMusicLab.getMusicDatas()));
    }
    public static Intent getIntent(Context context){
        return new Intent(context,MusicListActivity.class);
    }

    private void initialData(){
        mMusicLab=MusicLab.getMusicLab(this);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txItemTitle;
        private TextView txSinger;
        private ImageView ivItemAlbum;
        private MusicData mMusicData;
        private int position;
        public MyViewHolder(View itemView) {
            super(itemView);
            txItemTitle = (TextView) itemView.findViewById(R.id.tx_item_title);
            ivItemAlbum= (ImageView) itemView.findViewById(R.id.iv_item_album);
            txSinger= (TextView) itemView.findViewById(R.id.tx_title_singer);
            itemView.setOnClickListener(this);
        }

        public void bindMusic(MusicData musicData,int position){
            this.mMusicData=musicData;
            this.position=position;
            txItemTitle.setText(mMusicData.getMtitle());
            ivItemAlbum.setImageResource(mMusicData.getMusicPicture());
            txSinger.setText(mMusicData.getMusicSinger());
        }

        @Override
        public void onClick(View view) {
            Intent intent=MainActivity.getmIntent(getBaseContext());
            intent.putExtra(POSITIONINDEX,position);
            startActivity(intent);
        }
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private List<MusicData> mMusicDatas;

        public MyAdapter(List<MusicData> mMusicDatas){
            this.mMusicDatas=mMusicDatas;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=getLayoutInflater();
            View view=layoutInflater.from(getBaseContext()).inflate(R.layout.activity_music_item,parent,false);
            MyViewHolder myViewHolder=new MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            MusicData musicData=mMusicDatas.get(position);
            holder.bindMusic(musicData,position);
        }

        @Override
        public int getItemCount() {
            return mMusicDatas.size();
        }
    }

}

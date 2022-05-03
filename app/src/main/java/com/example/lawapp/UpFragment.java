package com.example.lawapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.camera2.CameraOfflineSession;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import java.util.ArrayList;

public class UpFragment extends Fragment {

    ListView listView;
    TextView textView;
    MediaPlayer player;
    ImageButton btnPlay;
    SeekBar sBar;
    String[] s_array;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.up_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        s_array = getResources().getStringArray(R.array.mofad_array);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.mofad_array, android.R.layout.simple_spinner_item);

        setup();

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (player != null && b) {
                    player.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addSeekBar();
                if (player.isPlaying()) {
                    player.pause();
                    btnPlay.setImageDrawable(getContext().getDrawable(R.drawable.play_gray_icon));


                } else {
                    player.start();
                    btnPlay.setImageDrawable(getContext().getDrawable(R.drawable.pause_gray_icon));
                }
            }
        });


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //  player.release();
                //  player=MediaPlayer.create(getContext(),R.raw.paris_heart);

                for (int i = 1; i < s_array.length; i++) {
                    if (position == i) ;
                    textView.setText(s_array[position]);
                }

            }
        });

    }

    public void addSeekBar() {
        sBar.setMax(player.getDuration());
        Handler handler = new Handler();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (player != null) {
                    sBar.setProgress(player.getCurrentPosition());

                }

                handler.postDelayed(this, 1000);
            }
        });


    }

    public void setup() {
        listView = getActivity().findViewById(R.id.list_view_mofad);
        textView = getActivity().findViewById(R.id.lbl_show_text);
        player = MediaPlayer.create(getContext(), R.raw.paris_heart);
        btnPlay = (ImageButton) getActivity().findViewById(R.id.btn_play_sound);
        sBar = (SeekBar) getActivity().findViewById(R.id.seek_bar);
    }

}

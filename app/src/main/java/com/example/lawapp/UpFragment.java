package com.example.lawapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class UpFragment extends Fragment {

    ListView listView;
    TextView textView;
    MediaPlayer player;
    ImageButton btnPlay;
    SeekBar sBar;
    String[] s_array;
    SearchView searchView;

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

        setupViews();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                try{
                int n = 0;
                n = Integer.valueOf(newText) - 1;

                if (newText == null ) {

                    n = 0;
                    textView.setText(null);
                    searchView.cancelLongPress();
                    Toast.makeText(getContext(), "فیلد خالیست!!", Toast.LENGTH_SHORT).show();

                } else if(newText != null && n<=1335 && n>0){
                    textView.setText(s_array[n]);
                    Integer n2 = Integer.parseInt(newText);
                    adapter.getFilter().filter(newText);
                    adapter.notifyDataSetChanged();
                }}
                catch (Exception e){
                    Toast.makeText(getContext(), "شماره ماده را وارد کنید!", Toast.LENGTH_SHORT).show();
                }

                return true;

            }
        });


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

    public void setupViews() {
        listView = getActivity().findViewById(R.id.list_view_mofad);
        textView = getActivity().findViewById(R.id.lbl_show_text);
        player = MediaPlayer.create(getContext(), R.raw.paris_heart);
        btnPlay = (ImageButton) getActivity().findViewById(R.id.btn_play_sound);
        sBar = (SeekBar) getActivity().findViewById(R.id.seek_bar);
        searchView = getActivity().findViewById(R.id.search_box);

    }


}

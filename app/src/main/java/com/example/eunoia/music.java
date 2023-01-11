package com.example.eunoia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link music#newInstance} factory method to
 * create an instance of this fragment.
 */
public class music extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public music() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment music.
     */
    // TODO: Rename and change types and number of parameters
    public static music newInstance(String param1, String param2) {
        music fragment = new music();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false);
    }

    //Spotify Button in the Music Button redirects with the Spotify Link
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button BtnSpotifyLink = view.findViewById(R.id.BtnSpotify);
        BtnSpotifyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://open.spotify.com/playlist/7EEbnnS7Zt1fGvTOjLL6Ry?si=er4VFxs_Q9akOXK5P-Xlmg");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

            //AppleMusic Button in the Music Button redirects with the Apple Music Link
            Button BtnAppleMusic = view.findViewById(R.id.BtnApple);
            BtnAppleMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://music.apple.com/my/playlist/eunoia/pl.u-d2b05dXtLgkW052");
                    startActivity(new Intent(Intent.ACTION_VIEW,uri));
                }
            });
        }
    }

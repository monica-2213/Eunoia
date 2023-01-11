package com.example.eunoia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eunoia.databinding.ActivityHelpBinding;
import com.example.eunoia.databinding.ActivityHelplineBinding;


public class HelplineActivity extends AppCompatActivity{
    ActivityHelplineBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpline);
        binding = ActivityHelplineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.BtnEmailLifeLine.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String EmailLifeLine = binding.LifeLineEmail.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                String[] addresses = EmailLifeLine.split(",");
                intent.putExtra(Intent.EXTRA_EMAIL,addresses);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }else{
                    Toast.makeText(HelplineActivity.this, "No App is Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.BtnEmailBef.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String EmailBefrienders = binding.BefriendersEmail.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                String[] addresses = EmailBefrienders.split(",");
                intent.putExtra(Intent.EXTRA_EMAIL,addresses);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }else{
                    Toast.makeText(HelplineActivity.this, "No App is Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.BtnEmailTW.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String EmailThriveWell = binding.ThriveWellEmail.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                String[] addresses = EmailThriveWell.split(",");
                intent.putExtra(Intent.EXTRA_EMAIL,addresses);

                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }else{
                    Toast.makeText(HelplineActivity.this, "No App is Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.project1_kfanta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button playComputerButton;
    Button playPartnerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playComputerButton = findViewById(R.id.playComputerButton);
        playPartnerButton = findViewById(R.id.playPartnerButton);

        playComputerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playGameIntent = new Intent(MainActivity.this, playgameActivity.class);
                startActivity(playGameIntent);
            }
        });

        playPartnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playManIntent = new Intent(MainActivity.this, playmanActivity.class);
                startActivity(playManIntent);
            }
        });
    }
}

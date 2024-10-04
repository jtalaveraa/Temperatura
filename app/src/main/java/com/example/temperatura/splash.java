package com.example.temperatura;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    private TextView countdownTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        countdownTextView = findViewById(R.id.countdownTextView);

        new CountDownTimer( 5000,  1000) {
            @Override
            public void onTick(long millisUntilFinished){
                countdownTextView.setText("Segundos para entrar: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
package com.example.challengeday5_project5_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.challengeday5_project5_buildtriviaapp.data.QuestionBank;
import com.example.challengeday5_project5_buildtriviaapp.model.Question;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new QuestionBank().getQuestions();

    }
}

package com.example.challengeday5_project5_buildtriviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.challengeday5_project5_buildtriviaapp.data.AnswerListAsyncResponse;
import com.example.challengeday5_project5_buildtriviaapp.data.QuestionBank;
import com.example.challengeday5_project5_buildtriviaapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";
    private TextView answerTextView;
    private TextView counterQuestionTextView;
    private CardView cardView;
    private ImageButton backImageBtn;
    private ImageButton nextImageBtn;
    private Button trueBtn;
    private Button falseBtn;
    private int currentQuestionIndex = 0;
    private List<Question> questionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerTextView = findViewById(R.id.answerTextViewCV);
        counterQuestionTextView = findViewById(R.id.numberOfQuestionTextView);
        cardView = findViewById(R.id.answerCardView);
        backImageBtn = findViewById(R.id.backButton);
        nextImageBtn = findViewById(R.id.nextButton);
        trueBtn = findViewById(R.id.trueButton);
        falseBtn = findViewById(R.id.falseButton);


        backImageBtn.setOnClickListener(this);
        nextImageBtn.setOnClickListener(this);
        trueBtn.setOnClickListener(this);
        falseBtn.setOnClickListener(this);

        answerTextView.setTextColor(getResources().getColor(R.color.colorText));

        questionList = new QuestionBank().getQuestions(new AnswerListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Question> questionArrayList) {
                answerTextView.setText(questionArrayList.get(currentQuestionIndex).getAnswer());
                Log.d(TAG, "processFinished: "+questionArrayList);
            }
        });



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backButton:
                if (currentQuestionIndex>0 ){
                    currentQuestionIndex = (currentQuestionIndex - 1) % currentQuestionIndex;
                    updateQuestion();
                } else Toast.makeText(getApplicationContext(),"Can\'t go back.",Toast.LENGTH_SHORT).show();
                break;

            case R.id.nextButton:
                    currentQuestionIndex = (currentQuestionIndex + 1) % questionList.size();
                    updateQuestion();
                    Log.d(TAG, "onClick: Next Button :: "+questionList.get(currentQuestionIndex).getAnswer());

                break;

            case R.id.trueButton:

                break;

            case R.id.falseButton:

                break;
        }
    }

    private void updateQuestion(){
        String question = questionList.get(currentQuestionIndex).getAnswer();
        answerTextView.setText(question);
        counterQuestionTextView.setText(currentQuestionIndex + " / "+ questionList.size());


    }
}

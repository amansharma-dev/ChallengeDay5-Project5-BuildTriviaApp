package com.example.challengeday5_project5_buildtriviaapp.data;

import com.example.challengeday5_project5_buildtriviaapp.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}

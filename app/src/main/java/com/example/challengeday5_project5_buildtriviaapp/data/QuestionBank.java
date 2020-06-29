package com.example.challengeday5_project5_buildtriviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.challengeday5_project5_buildtriviaapp.controller.ApplicationController;
import com.example.challengeday5_project5_buildtriviaapp.model.Question;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    //fetch Json request url: https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json

    public static final String TAG = "QuestionBank";

    private String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    ArrayList<Question> questionArrayList = new ArrayList<>();

    public List<Question> getQuestions(final AnswerListAsyncResponse callback){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, "onResponse: "+response);

                        for(int i = 0; i< response.length() ; i++){
                            try {
                                Question question = new Question();
                                question.setAnswer(response.getJSONArray(i).get(0).toString());
                                question.setAnswerTrue(response.getJSONArray(i).getBoolean(1));
//                                Log.d(TAG, "onResponse: "+response.getJSONArray(i).getString(0));
//                                Log.d(TAG, "onResponse: "+response.getJSONArray(i).getBoolean(1));

                                questionArrayList.add(question);
                                Log.d(TAG, "onResponse: "+question);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if(null != callback) callback.processFinished(questionArrayList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        ApplicationController.getInstance().addToRequestQueue(jsonArrayRequest);
        return  questionArrayList;
    }

}

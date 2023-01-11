package com.example.eunoia;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;



public class AssesmentActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ans1,ans2,ans3,ans4;
    Button submitBtn;

    int score=0;
    int totalQuestion = QuestionsAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment);
        setContentView(R.layout.activity_main);

        totalQuestionsTextView = findViewById(R.id.TVTitle);
        questionTextView = findViewById(R.id.textView2);
        ans1 = findViewById(R.id.BtnAns1);
        ans2 = findViewById(R.id.BtnAns2);
        ans3 = findViewById(R.id.BtnAns3);
        submitBtn = findViewById(R.id.SubmitBtn);

        ans1.setOnClickListener(this);
        ans2.setOnClickListener(this);
        ans3.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions:" + totalQuestion);

        loadNewQuestion();
    }

    public void onClick(View view) {
        ans1.setBackgroundColor(Color.WHITE);
        ans2.setBackgroundColor(Color.WHITE);
        ans3.setBackgroundColor(Color.WHITE);


        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.SubmitBtn){
            if (selectedAnswer.equals(QuestionsAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }else{
            //choices button clicked
            selectedAnswer= clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion(){

        if (currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }

        questionTextView.setText(QuestionsAnswer.question[currentQuestionIndex]);
        ans1.setText(QuestionsAnswer.choice[currentQuestionIndex][0]);
        ans2.setText(QuestionsAnswer.choice[currentQuestionIndex][1]);
        ans3.setText(QuestionsAnswer.choice[currentQuestionIndex][2]);
    }
    void finishQuiz(){
        String passStatus ="";
        if (score>totalQuestion*0.60){
            passStatus = "Passed";
        }else{
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz() )
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score=0;
        currentQuestionIndex = 0;
        loadNewQuestion();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_assesment_activity, container, false);
    }
}
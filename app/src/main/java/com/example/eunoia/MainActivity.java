package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email, name, password, dateOfBirth;
    RadioGroup gender;
    Button btnSignUp, btnLogin;
    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        gender = findViewById(R.id.gender);
        btnSignUp = findViewById(R.id.btnSignup);
        btnLogin = findViewById(R.id.btnLogin);
        dbHelper = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailUser = email.getText().toString();
                String nameUser = name.getText().toString();
                String passwordUser = password.getText().toString();
                String DOBUser = dateOfBirth.getText().toString();
                RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
                String genderUser = checkedBtn.getText().toString();

                if(emailUser.length()>1){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("email", emailUser);
                    contentValues.put("name", nameUser);
                    contentValues.put("password", passwordUser);
                    contentValues.put("dob", DOBUser);
                    contentValues.put("gender", genderUser);

                    dbHelper.insertUser(contentValues);
                    Toast.makeText(MainActivity.this, "Registration Successful! Login to Continue.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

                else{
                    Toast.makeText(MainActivity.this, "Registration Failed! Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

//Qayleef Part Down Here (Assessment)
/*
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ans1,ans2,ans3,ans4;
    Button submitBtn;

    int score   =0;
    int totalQuestion = QuestionsAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    protected void onCreate2(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_assesment_activity);

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

    @Override
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
    }*/

    //trial 2/1/23
    //jaskskbask

package com.example.eunoia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    public FragmentManager fragmentManager;
    EditText username, password, repassword;
    Button signUp, login;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signUp = (Button) findViewById(R.id.btnSignup);
        login = (Button) findViewById(R.id.btnLogin);
        DB = new DBHelper(this);



        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkUsername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert == true){
                                Toast.makeText(MainActivity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exist. Please proceed to login!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    //Qayleef Part Down Here (Assessment)

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
    }

    //trial 2/1/23
    //jaskskbask

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String username = "Hayato";
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        TrackerMenu trackerMenu = new TrackerMenu();
        trackerMenu.setArguments(bundle);
        fragmentTransaction.replace(R.id.frameLayout,trackerMenu).commit();
    }

}




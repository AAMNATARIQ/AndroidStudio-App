package amnatariq.org.myapplication;

import static java.util.Collections.shuffle;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    String mQuestions[] ={"Which of the following are object oriented languages?",
            "In programming, a series of logically ordered steps that lead to a required result is called?",
            "Which is a typical language for programming inside Web pages?",
            "Which of the following converts source code into machine code at each runtime?",
            "Which of the following commonly happens to variables (in most languages)?"};

    String mChoices [][] = {
            {"Java","Cobol","C++"},
            {"a compiler","a data structure","an algorithm"},
            {"JavaScript","HTML","XML"},
            {"linker","compiler","interpreter"},
            {"declaration","assignment","derivation"}
    };

    String mCorrectAnswers[] = {"C++","an algorithm","JavaScript","interpreter","declaration"};

    TextView mScoreView;
    TextView mTotalQuestionView;
    TextView mQuestionView;
    Button mBtnChoice1, mBtnChoice2, mBtnChoice3, mShowAns;

    String mAnswer;
    int mScore = 0;
    int mQuestionNumber = 0;

    int mTotalQuestion = 0;

    int temp;
    int t;
    int t1;
    int t2;
    int t3;

    int n1,n2,n3,n4,n5;

    int[] array;
    ArrayList<Integer> list = new ArrayList<Integer>(5);
    List<Integer> l = new ArrayList<>();
    int count=0;
    int counting=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScoreView = (TextView)findViewById(R.id.score);
        mTotalQuestionView = (TextView)findViewById(R.id.totalquestion);
        mQuestionView = (TextView)findViewById(R.id.question);

        mBtnChoice1 = (Button)findViewById(R.id.choice1);
        mBtnChoice2 = (Button)findViewById(R.id.choice2);
        mBtnChoice3 = (Button)findViewById(R.id.choice3);





    }

    public void updateScore(int point)
    {
        mScoreView.setText(""+mScore);
    }

    public void updateQuestionNum(int point)
    {
        mTotalQuestionView.setText(""+mTotalQuestion+"/5`");
    }
    public void updateQuestion()
    {
        //mQuestionView.setText(getQuestion(mQuestionNumber));
        mTotalQuestion = mTotalQuestion+1;
        updateQuestionNum(mTotalQuestion);
        mQuestionView.setText(getQuestionRand());

//        mBtnChoice1.setText(getChoices1(mQuestionNumber));
//        mBtnChoice2.setText(getChoices2(mQuestionNumber));
//        mBtnChoice3.setText(getChoices3(mQuestionNumber));

        mBtnChoice1.setText(getChoices1(temp));
        mBtnChoice2.setText(getChoices2(temp));
        mBtnChoice3.setText(getChoices3(temp));
        mAnswer = getCorrectAnswer(temp);
        mQuestionNumber++;
    }

    public String getQuestionRand(){
        Random r = new Random();
        temp = r.nextInt(5);

        while(l.contains(temp))
        {
            temp = r.nextInt(5);
        }
        l.add(counting,temp);
        counting=counting+1;

        String question = mQuestions[temp];
        return question;
    }

    public void addArray(int te,int num)
    {
        for(int i=num;i<3;i++)
        {
            array[i]=te;
        }
    }
    public boolean checkArray(int[] te, int num)
    {
        boolean c=true;
        for(int i=0;i<te.length;i++)
        {
            if(te[i]==num)
            {
                c= false;
            }
            else
            {
                c= true;
            }
        }
        return c;
    }

    public String getQuestion(int a){
        String question = mQuestions[a];
        return question;
    }

//    public String getRandomChoices(int a)
//    {
//        Random r = new Random();
//        int ran = r.nextInt(2);
//    }

    public String getChoices1(int a){
        Random r = new Random();
        t= r.nextInt(2);
        String choice = mChoices[a][t];
        return choice;
    }
    public String getChoices2(int a){
        Random r = new Random();
        t1 = r.nextInt(2);
        if (t1==t)
        {
            while(t1==t)
            {
                Random r1 = new Random();
                t1 = r1.nextInt(2);
            }
            String choice = mChoices[a][t1];
            return choice;
        }
        else
        {
            String choice = mChoices[a][t1];
            return choice;
        }
    }
    public String getChoices3(int a){
        Random r = new Random();
        t2 = r.nextInt(3);
        if(t2==t1 || t2==t)
        {
            while(t2==t1 || t2==t)
            {
                Random r2 = new Random();
                t2 = r2.nextInt(3);
            }
            String choice = mChoices[a][t2];
            return choice;
        }
        else
        {
            String choice = mChoices[a][t2];
            return choice;
        }

    }

    public String getCorrectAnswer(int a){
        String answer = mCorrectAnswers[a];
        return answer;
    }

}
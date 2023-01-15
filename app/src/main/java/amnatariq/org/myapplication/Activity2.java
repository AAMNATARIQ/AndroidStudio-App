package amnatariq.org.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Activity2 extends AppCompatActivity {

    TextView correctView,incorrectView,dateView;
    TextView questionView,questionValView;


    TextView quesOne, quesOneVal, quesOneCorrAns;
    TextView quesTwo, quesTwoVal, quesTwoCorrAns;
    TextView quesThree, quesThreeVal, quesThreeCorrAns;
    TextView quesFour, quesFourVal, quesFourCorrAns;
    TextView quesFive, quesFiveVal, quesFiveCorrAns;
    TextView quesSix, quesSixVal, quesSixCorrAns;


    ArrayList<String> array;
    int count=0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        correctView = findViewById(R.id.score);
        incorrectView = findViewById(R.id.incorrect);
        dateView = findViewById(R.id.date_val);

        quesOne = findViewById(R.id.question_num1);
        quesOneVal = findViewById(R.id.ques_val1);
        quesOneCorrAns = findViewById(R.id.ques_val_ans1);

        quesTwo = findViewById(R.id.question_num2);
        quesTwoVal = findViewById(R.id.ques_val2);
        quesTwoCorrAns = findViewById(R.id.ques_val_ans2);

        quesThree = findViewById(R.id.question_num3);
        quesThreeVal = findViewById(R.id.ques_val3);
        quesThreeCorrAns = findViewById(R.id.ques_val_ans3);

        quesFour = findViewById(R.id.question_num4);
        quesFourVal = findViewById(R.id.ques_val4);
        quesFourCorrAns = findViewById(R.id.ques_val_ans4);

        quesFive = findViewById(R.id.question_num5);
        quesFiveVal = findViewById(R.id.ques_val5);
        quesFiveCorrAns = findViewById(R.id.ques_val_ans5);

        quesSix = findViewById(R.id.question_num6);
        quesSixVal = findViewById(R.id.ques_val6);
        quesSixCorrAns = findViewById(R.id.ques_val_ans6);


        String correctAns = getIntent().getStringExtra("keycorrect");
        String incorrectAns;
        incorrectAns = getIntent().getStringExtra("keyincorrect");

        correctView.setText(correctAns);
        incorrectView.setText(incorrectAns);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        dateView.setText(strDate);

        Bundle bundle = getIntent().getExtras();
        array= bundle.getStringArrayList("stringArray");

        quesOne.setText(array.get(0));
        quesOneCorrAns.setText(array.get(2));
        quesOneVal.setText(array.get(3));

        quesTwo.setText(array.get(1));
        quesTwoCorrAns.setText(array.get(5));
        quesTwoVal.setText(array.get(3));

        quesThree.setText(array.get(4));
        quesThreeCorrAns.setText(array.get(8));
        quesThreeVal.setText(array.get(9));

        quesFour.setText(array.get(7));
        quesFourCorrAns.setText(array.get(11));
        quesFourVal.setText(array.get(12));

        quesFive.setText(array.get(10));
        quesFiveCorrAns.setText(array.get(14));
        quesFiveVal.setText(array.get(15));

        quesSix.setText(array.get(13));
        quesSixCorrAns.setText(array.get(16));
        quesSixVal.setText(array.get(17));

//        ListView lv =findViewById(R.id.list_val);
//        ArrayAdapter<String> items = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array);
//        lv.setAdapter(items);

    }
}
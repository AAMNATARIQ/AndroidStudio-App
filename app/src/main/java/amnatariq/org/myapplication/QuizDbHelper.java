package amnatariq.org.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import amnatariq.org.myapplication.QuizContract.QuestionsTable;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="MyQuiz.db";
    private static final int DATABASE_VERSION =1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //table made
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db =db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE "+
        QuestionsTable.TABLE_NAME + " ( " +
        QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
        QuestionsTable.COLUMN_QUESTION + " TEXT, " +
        QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
        QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
        QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
        QuestionsTable.COLUMN_ANSWER + " TEXT" + ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL ("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER,question.getAnswer());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    //inserting data in table
    private void fillQuestionsTable()
    {
        Question q1 = new Question("this is a question?","A","B","C","answer is A");
        addQuestion(q1);

        //adding all the questions here
    }


    @SuppressLint("Range")
    public ArrayList<Question> getAllQuestions()
    {
        ArrayList<Question> qlist = new ArrayList<>();
        db=getReadableDatabase();

        String Projection[] ={
                QuestionsTable._ID,
                QuestionsTable.COLUMN_QUESTION,
                QuestionsTable.COLUMN_OPTION1,
                QuestionsTable.COLUMN_OPTION2,
                QuestionsTable.COLUMN_OPTION3,
                QuestionsTable.COLUMN_ANSWER
        };

        Cursor c = db.query(QuestionsTable.TABLE_NAME, Projection,
                null,null,null,null,null);

        if(c.moveToFirst()){
            do{
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER)));
                qlist.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return qlist;
    }
}

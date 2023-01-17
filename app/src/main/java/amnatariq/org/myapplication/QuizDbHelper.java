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
        QuestionsTable.COLUMN_ANSWER + " TEXT, " +
        QuestionsTable.COLUMN_MYANSWER + " TEXT" + ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL ("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    public void deleteCourse(String courseName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(QuestionsTable.TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }

    private void addQuestion(Question question)
    {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER,question.getAnswer());
        cv.put(QuestionsTable.COLUMN_MYANSWER,question.getMyanswer());
        db.insert(QuestionsTable.TABLE_NAME,null,cv);
    }

    //inserting data in table
    public void fillQuestionTable(String q1,String o1,String o2, String o3, String a, String ma) {
        Question q = new Question(q1, o1, o2, o3, a, ma);
        addQuestion(q);
    }
    private void fillQuestionsTable()
    {
        Question q1 = new Question("Which of the following are object oriented languages?",
                "Java","Cobol","C++",
                "C++","");
        addQuestion(q1);
        Question q2 = new Question(
                "In programming, a series of logically ordered steps that lead to a required result is called?",
                "a compiler","a data structure","an algorithm",
                "an algorithm",""
        );
        addQuestion(q2);
        Question q3 = new Question(
                "Which is a typical language for programming inside Web pages?",
                "JavaScript","HTML", "XML",
                "JavaScript",""
        );
        addQuestion(q3);
        Question q4 = new Question(
                "Which of the following converts source code into machine code at each runtime?",
                "linker","compiler","interpreter",
                "interpreter",""
                );
        addQuestion(q4);
        Question q5 = new Question(
                "Which of the following commonly happens to variables (in most languages)?",
                "declaration","assignment","derivation",
                "declaration",""
                );
        addQuestion(q5);
        Question q6 = new Question(
                "AND, OR and NOT are logical operators. What data type is expected for their operands?",
                "integer","boolean","character",
                "boolean",""
        );
        addQuestion(q6);

        //adding all the questions here
    }

    public void updateUserAnswer(String ques, String answer)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put(QuestionsTable.COLUMN_MYANSWER,answer);
        System.out.println(ques);
        db.update(QuestionsTable.TABLE_NAME,v,QuestionsTable.COLUMN_QUESTION + " = ?" , new String[]{ques.toString()});
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
                QuestionsTable.COLUMN_ANSWER,
                //QuestionsTable.COLUMN_MYANSWER
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
                //question.setAnswer(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_MYANSWER)));
                qlist.add(question);
            }while(c.moveToNext());
        }
        c.close();
        return qlist;
    }
}

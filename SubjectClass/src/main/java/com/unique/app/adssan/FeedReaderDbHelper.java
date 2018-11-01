package com.unique.app.adssan;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.unique.app.adssan.Adapter.PartModel;
import com.unique.app.adssan.Adapter.TypeModel;

import java.util.ArrayList;


/**
 * Created by thiyagu on 3/20/2018.
 */

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "data.db";
    private static final String TABLE_NAME_SUBJECT = "subject";
    private static final String TABLE_NAME_CHAPTER = "chapter";
    private static final String TABLE_NAME_QUESTIONS = "questions";
    private static final String TABLE_NAME_QUESTIONS1 = "questions1";


    private static final String ID = "id";
    private static final String COLUMN_ID = "idvalue";


    private static final String COLUMN_SUB_CID = "cid";
    private static final String COLUMN_SUB_CATEGORY_NAME = "categoryname";
    private static final String COLUMN_SUB_YEAR = "year";


    private static final String COLUMN_CHAPTER_NAME = "chaptername";
    private static final String COLUMN_CHAPTER_SUBJECT = "subject";
    private static final String COLUMN_CHAPTER_YEAR = "year";


    private static final String COLUMNT_QUESTIONS_ID = "tid";
    private static final String COLUMNT_QUESTIONS_YEAR = "years";
    private static final String COLUMN_QUESTIONS_SUBJECT = "subject";
    private static final String COLUMN_QUESTIONS_CHAPTERNAME = "chaptername";
    private static final String COLUMN_QUESTIONS_CHAPTERPART = "part";
    private static final String COLUMN_QUESTIONS_QUE = "que";


    private static final String COLUMNT_QUESTIONS1_ID1 = "id5";
    private static final String COLUMNT_QUESTIONS1_TID = "tid5";
    private static final String COLUMN_QUESTIONS1_QUES = "ques5";
    private static final String COLUMN_QUESTIONS1_CNO = "cno5";
    private static final String COLUMN_QUESTIONS1_RNO = "rno5";
    private static final String COLUMN_QUESTIONS1_YEARS = "years5";
    private static final String COLUMN_QUESTIONS1_SUBJECT = "subject5";
    private static final String COLUMN_QUESTIONS1_PART = "part5";
    private static final String COLUMN_QUESTIONS1_CHAPTER = "chapter5";
    private static final String COLUMN_QUESTIONS1_QUE = "que5";
    private static final String COLUMNT_QUESTIONS1_STATUS = "status";


    private static final String SQL_CREATE_TABLE_SUBJECT = "CREATE TABLE " + FeedReaderDbHelper.TABLE_NAME_SUBJECT + " (" +
            FeedReaderDbHelper.ID + " INTEGER PRIMARY KEY," +
            FeedReaderDbHelper.COLUMN_SUB_CID + " TEXT," +
            FeedReaderDbHelper.COLUMN_SUB_CATEGORY_NAME + " TEXT," +
            FeedReaderDbHelper.COLUMN_SUB_YEAR + " TEXT)";


    private static final String SQL_CREATE_TABLE_CHAPTER = "CREATE TABLE " + FeedReaderDbHelper.TABLE_NAME_CHAPTER + " (" +
            FeedReaderDbHelper.ID + " INTEGER PRIMARY KEY," +
            FeedReaderDbHelper.COLUMN_ID + " TEXT," +
            FeedReaderDbHelper.COLUMN_CHAPTER_NAME + " TEXT," +
            FeedReaderDbHelper.COLUMN_CHAPTER_SUBJECT + " TEXT," +
            FeedReaderDbHelper.COLUMN_CHAPTER_YEAR + " TEXT)";


    private static final String SQL_CREATE_TABLE_QUESTIONS = "CREATE TABLE " + FeedReaderDbHelper.TABLE_NAME_QUESTIONS + " (" +
            FeedReaderDbHelper.ID + " INTEGER PRIMARY KEY," +
            FeedReaderDbHelper.COLUMNT_QUESTIONS_ID + " TEXT," +
            FeedReaderDbHelper.COLUMNT_QUESTIONS_YEAR + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS_SUBJECT + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS_CHAPTERNAME + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS_CHAPTERPART + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS_QUE + " TEXT)";


    private static final String SQL_CREATE_TABLE_QUESTIONS1 = "CREATE TABLE " + FeedReaderDbHelper.TABLE_NAME_QUESTIONS1 + " (" +
            FeedReaderDbHelper.ID + " INTEGER PRIMARY KEY," +
            FeedReaderDbHelper.COLUMNT_QUESTIONS1_ID1 + " TEXT," +
            FeedReaderDbHelper.COLUMNT_QUESTIONS1_STATUS + " TEXT," +
            FeedReaderDbHelper.COLUMNT_QUESTIONS1_TID + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_QUES + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_CNO + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_YEARS + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_SUBJECT + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_PART + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_CHAPTER + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_QUE + " TEXT," +
            FeedReaderDbHelper.COLUMN_QUESTIONS1_RNO + " TEXT)";


    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_SUBJECT);
        db.execSQL(SQL_CREATE_TABLE_CHAPTER);
        db.execSQL(SQL_CREATE_TABLE_QUESTIONS);
        db.execSQL(SQL_CREATE_TABLE_QUESTIONS1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_QUESTIONS1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CHAPTER);
        onCreate(db);


    }

    public void addSubject(DataSubject chapter) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, chapter.getId());
        values.put(COLUMN_SUB_CID, chapter.getCid());
        values.put(COLUMN_SUB_CATEGORY_NAME, chapter.getCategory_name());
        values.put(COLUMN_SUB_YEAR, chapter.getYear());


        db.insert(TABLE_NAME_SUBJECT, null, values);
        // PrintAllParam();
        db.close();


    }

    public void DEleteAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from " + TABLE_NAME_SUBJECT);
        db.execSQL("delete from " + TABLE_NAME_CHAPTER);
        db.execSQL("delete from " + TABLE_NAME_QUESTIONS);
        db.execSQL("delete from " + TABLE_NAME_QUESTIONS1);
        db.close();
    }


    public void addChapter(DataChapter dataChapter) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, dataChapter.getId());
        values.put(COLUMN_ID, dataChapter.getColumnid());
        values.put(COLUMN_CHAPTER_NAME, dataChapter.getChapter_name());
        values.put(COLUMN_CHAPTER_SUBJECT, dataChapter.getSubject());
        values.put(COLUMN_CHAPTER_YEAR, dataChapter.getYear());

        db.insert(TABLE_NAME_CHAPTER, null, values);
        // PrintAllParam();
        db.close();

//        FeedReaderDbHelper.ID + " INTEGER PRIMARY KEY," +
//                FeedReaderDbHelper.ID_VALUE + " TEXT," +
//                FeedReaderDbHelper.COLUMN_CHAPTER_NAME + " TEXT," +
//                FeedReaderDbHelper.COLUMN_CHAPTER_SUBJECT + " TEXT," +
//                FeedReaderDbHelper.COLUMN_CHAPTER_YEAR + " TEXT)";


    }

    public ArrayList<String> getQue(String subject, String part, String chapter, String year) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<String> array_list = new ArrayList<>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {

            array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUE)));

            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));

            res.moveToNext();

        }
        db.close();

        return array_list;


    }


    public ArrayList<TypeModel> getTypeQuest(String subject, String part, String chapter, String year) {

//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<TypeModel> array_list = new ArrayList<>();
        TypeModel typeModel = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'  GROUP BY "+COLUMN_QUESTIONS1_QUE, null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUE)));
            typeModel = new TypeModel(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUE)));
            Log.v("forgetchapter1212",""+res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUE)));
            array_list.add(typeModel);
            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
            res.moveToNext();
        }
        db.close();

        return array_list;


    }


    public void addQuestions(DataQuestions dataQuestions) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, dataQuestions.getId());
        values.put(COLUMNT_QUESTIONS_ID, dataQuestions.getTid());
        values.put(COLUMNT_QUESTIONS_YEAR, dataQuestions.getYears());
        values.put(COLUMN_QUESTIONS_SUBJECT, dataQuestions.getSubject());
        values.put(COLUMN_QUESTIONS_CHAPTERNAME, dataQuestions.getChapter_name());
        values.put(COLUMN_QUESTIONS_CHAPTERPART, dataQuestions.getQue());
        values.put(COLUMN_QUESTIONS_QUE, dataQuestions.getQue());
        db.insert(TABLE_NAME_QUESTIONS, null, values);
        //PrintAllParam();
        db.close();

//        FeedReaderDbHelper.ID + " INTEGER PRIMARY KEY," +
//                FeedReaderDbHelper.COLUMNT_QUESTIONS_ID + " TEXT," +
//                FeedReaderDbHelper.COLUMNT_QUESTIONS_YEAR + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS_SUBJECT+ " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS_CHAPTERNAME + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS_CHAPTERPART + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS_QUE + " TEXT)";


    }


    public void addQuestions1(DataQuestions1 dataQuestions1) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, dataQuestions1.getIdvalue());
        values.put(COLUMNT_QUESTIONS1_TID, dataQuestions1.getTid());
        values.put(COLUMN_QUESTIONS1_QUES, dataQuestions1.getQues());
        values.put(COLUMN_QUESTIONS1_CNO, dataQuestions1.getCno());
        values.put(COLUMN_QUESTIONS1_YEARS, dataQuestions1.getYears());
        values.put(COLUMN_QUESTIONS1_SUBJECT, dataQuestions1.getSubject());
        values.put(COLUMN_QUESTIONS1_PART, dataQuestions1.getPart());

        values.put(COLUMN_QUESTIONS1_CHAPTER, dataQuestions1.getChapter());
        values.put(COLUMN_QUESTIONS1_QUE, dataQuestions1.getQue());
        values.put(COLUMN_QUESTIONS1_CNO, dataQuestions1.getCno());
        values.put(COLUMN_QUESTIONS1_RNO, dataQuestions1.getRno());



        db.insert(TABLE_NAME_QUESTIONS1, null, values);
        //  PrintAllParam();
        db.close();


//
//        FeedReaderDbHelper.COLUMNT_QUESTIONS1_ID1 + " TEXT," +
//                FeedReaderDbHelper.COLUMNT_QUESTIONS1_TID + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_QUES + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_CNO + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_YEARS + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_SUBJECT + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_PART + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_CHAPTER + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_QUE + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_CNO + " TEXT," +
//                FeedReaderDbHelper.COLUMN_QUESTIONS1_RNO + " TEXT)";


    }

    public ArrayList<CardSubjectData> getSubject(String year) {
        ArrayList<CardSubjectData> array_list = new ArrayList<CardSubjectData>();
        CardSubjectData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_SUBJECT + " where " + COLUMN_SUB_YEAR + " = " + "'" + year + "'", null);
        res.moveToFirst();
//Log.v("asdasdsadasdsad","select * from " + TABLE_NAME_SUBJECT+" where "+COLUMN_SUB_YEAR+" = "+"'"+year+"'");
        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME)));
            card_subjectData = new CardSubjectData(res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME)));
            array_list.add(card_subjectData);


            res.moveToNext();

        }
        db.close();

        return array_list;


    }

    public ArrayList<String> getSubjectAlone(String year) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_SUBJECT + " where " + COLUMN_SUB_YEAR + " = " + "'" + year + "'", null);
        res.moveToFirst();
//Log.v("asdasdsadasdsad","select * from " + TABLE_NAME_SUBJECT+" where "+COLUMN_SUB_YEAR+" = "+"'"+year+"'");
        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME)));
            // card_subjectData = new CardSubjectData(res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME)));
            array_list.add(res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME)));


            res.moveToNext();

        }
        db.close();

        return array_list;


    }

//    public ArrayList<CardSubjectData> getPartMenu(String subject) {
//        ArrayList<CardSubjectData> array_list = new ArrayList<CardSubjectData>();
//        QuestionsDAta questionsDAta = null;
//        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " = " + "'" + subject + "'", null);
//
//        Log.v("sadsadsadsad", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " = " + subject);
//
//        res.moveToFirst();
//
//        while (res.isAfterLast() == false) {
//
//            String s = res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_PART));
//
//
//          //  array_list.add(s);
//
//
//            res.moveToNext();
//
//        }
//        db.close();
//
//
//        return array_list;
//
//
//    }
public ArrayList<PartModel> getPartNames(String subject) {
    ArrayList<PartModel> array_list = new ArrayList<PartModel>();
    PartModel partModel = null;
    //hp = new HashMap();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " = " + "'" + subject + "' GROUP BY "+COLUMN_QUESTIONS1_PART, null);

    Log.v("sadsadsadsad", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " = " + subject);

    res.moveToFirst();

    while (res.isAfterLast() == false) {
        partModel = new PartModel(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_PART)));
        array_list.add(partModel);
        res.moveToNext();
    }
    db.close();
    return array_list;
}

    public ArrayList<String> getPart(String subject) {
        ArrayList<String> array_list = new ArrayList<String>();
        QuestionsDAta questionsDAta = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " = " + "'" + subject + "'", null);

        Log.v("sadsadsadsad", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " = " + subject);

        res.moveToFirst();

        while (res.isAfterLast() == false) {

            String s = res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_PART));


            array_list.add(s);


            res.moveToNext();

        }
        db.close();


        return array_list;


    }


    public ArrayList<CardSubjectData> getChapter(String chaptername) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<CardSubjectData> array_list = new ArrayList<CardSubjectData>();
        CardSubjectData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_CHAPTER_NAME + " =" + "'" + chaptername + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_CHAPTER + " =" + "'" + chaptername + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME)));
            card_subjectData = new CardSubjectData(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_SUBJECT)));
            array_list.add(card_subjectData);

            //Log.v("sadadasdasdasdsad", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_PART)));

            res.moveToNext();

        }
        db.close();

        return array_list;


    }

    public ArrayList<CardChapterData> getChapter(String value, String part) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());


        ArrayList<CardChapterData> array_list = new ArrayList<CardChapterData>();
        CardChapterData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "select DISTINCT " + COLUMN_QUESTIONS1_CHAPTER + " from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + value + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'");
        Cursor res = db.rawQuery("select DISTINCT " + COLUMN_QUESTIONS1_CHAPTER + " from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + value + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME)));
            card_subjectData = new CardChapterData(res.getString(0));
            array_list.add(card_subjectData);


            res.moveToNext();

        }
        db.close();

        return array_list;


    }

    public ArrayList<CardQuestionsData> getQuestions(String subject, String part, String chapter, String year,String type) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'" + " and " + COLUMN_QUESTIONS1_QUE + " = " + "'" + type + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
            card_subjectData = new CardQuestionsData(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)), res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_CNO)),res.getString(res.getColumnIndex(ID)),res.getString(res.getColumnIndex(COLUMNT_QUESTIONS1_STATUS)));
            array_list.add(card_subjectData);

            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));

            res.moveToNext();

        }
        db.close();

        return array_list;


    }


    public ArrayList<CardQuestionsData> getQuestionsusingCRN(String subject, String part, String chapter, String year, String type) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'" + " and " + COLUMN_QUESTIONS1_QUE + " = " + "'" + type + "'" + "ORDER BY " + COLUMN_QUESTIONS1_CNO + " ASC", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
            card_subjectData = new CardQuestionsData(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)), res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_CNO)),res.getString(res.getColumnIndex(ID)),res.getString(res.getColumnIndex(COLUMNT_QUESTIONS1_STATUS)));
            array_list.add(card_subjectData);

            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));

            res.moveToNext();

        }
        db.close();

        return array_list;


    }

    public void setColor(String ques) {

        //UPDATE DB_TABLE SET YOUR_COLUMN='newValue' WHERE id=6
        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "update " + TABLE_NAME_QUESTIONS1 + " SET " + COLUMNT_QUESTIONS1_STATUS + " = 1 " + " where " + ID + " =" + "'" + ques);
        Cursor res = db.rawQuery("update " + TABLE_NAME_QUESTIONS1 + " SET " + COLUMNT_QUESTIONS1_STATUS + " = 1 " + " where " + ID + " = " + "'" + ques + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

//        while (res.isAfterLast() == false) {
//            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));
//
//            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));
//
//
//            // array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
//            card_subjectData = new CardQuestionsData(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)), res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_CNO)),res.getString(res.getColumnIndex(ID)),res.getString(res.getColumnIndex(COLUMNT_QUESTIONS1_STATUS)));
//            array_list.add(card_subjectData);
//
//            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
//
//            res.moveToNext();
//
//        }
        db.close();


    }

    public String getColor(String ques) {


        //UPDATE DB_TABLE SET YOUR_COLUMN='newValue' WHERE id=6 "

        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "update " + TABLE_NAME_QUESTIONS1 + " SET " + COLUMNT_QUESTIONS1_STATUS + " = 1 " + " where " + COLUMN_QUESTIONS1_QUES + " =" + "'" + ques);
        Cursor res = db.rawQuery("select " + COLUMNT_QUESTIONS1_STATUS + " from " + TABLE_NAME_QUESTIONS1 + " where " + ID + " = " + "' " + ques + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();
        String value = null;
        while (res.isAfterLast() == false) {
            value = res.getString(res.getColumnIndex(COLUMNT_QUESTIONS1_STATUS));
            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
            res.moveToNext();
        }
        db.close();

        return value;


    }


    public int getRead(String year) {
        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMNT_QUESTIONS1_STATUS + " = " + "'" + 1 + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();
        int value = 0;
        while (res.isAfterLast() == false) {
            value++;
            // value = Integer.parseInt(res.getString(Integer.parseInt(res.getString(0))));
            Log.v("thisisforquestions", String.valueOf(value));

            res.moveToNext();

        }
        db.close();
        return value;
    }

    public int getReadall(String subject) {


        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMNT_QUESTIONS1_STATUS + " = " + "'" + 1 + "'"+" and "+COLUMN_QUESTIONS1_SUBJECT+" = "+"'"+subject+"'", null);
        Log.v("dasdasdasdasdasdasd","select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMNT_QUESTIONS1_STATUS + " = " + "'" + 1 + "'"+" and "+COLUMN_QUESTIONS1_SUBJECT+" = "+"'"+subject+"'");
        res.moveToFirst();
        int value = 0;
        while (res.isAfterLast() == false) {
            value++;
            // value = Integer.parseInt(res.getString(Integer.parseInt(res.getString(0))));
            Log.v("thisisforquestions", String.valueOf(value));

            res.moveToNext();

        }
        db.close();

        return value;


    }
    public int getUnReadAll(String subject) {


        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
//select * from questions1 where status IS NULL OR status =' ';
        //Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMNT_QUESTIONS1_STATUS + " IS NULL OR " + COLUMNT_QUESTIONS1_STATUS + " = " + "'" + " " + "'"+" and "+COLUMN_QUESTIONS1_SUBJECT+" = "+"'"+subject+"'", null);

        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT+" = "+"'"+subject+"'"+" and "+COLUMNT_QUESTIONS1_STATUS + " IS NULL OR " + COLUMNT_QUESTIONS1_STATUS+ " = " + "'" + " " + "'", null);


        Log.v("dsfdsfdsfsdffdf","select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT+" = "+"'"+subject+"'"+" and "+COLUMNT_QUESTIONS1_STATUS + " IS NULL OR " + COLUMNT_QUESTIONS1_STATUS+ " = " + "'" + " " + "'");
        res.moveToFirst();
        int value = 0;
        while (res.isAfterLast() == false) {
            value++;
            // value = Integer.parseInt(res.getString(Integer.parseInt(res.getString(0))));
            Log.v("thisisforquestions", String.valueOf(value));

            res.moveToNext();

        }
        db.close();

        return value;


    }
    public int getUnRead(String year) {


        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
//select * from questions1 where status IS NULL OR status =' ';
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMNT_QUESTIONS1_STATUS + " IS NULL OR " + COLUMNT_QUESTIONS1_STATUS + " = " + "'" + " " + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();
        int value = 0;
        while (res.isAfterLast() == false) {
            value++;
            // value = Integer.parseInt(res.getString(Integer.parseInt(res.getString(0))));
            Log.v("thisisforquestions", String.valueOf(value));

            res.moveToNext();

        }
        db.close();

        return value;


    }

    public ArrayList<CardQuestionsData> getQuestionsusingRNO(String subject, String part, String chapter, String year, String type) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("forgetchapter", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'" + " and " + COLUMN_QUESTIONS1_QUE + " = " + "'" + type + "'" + "ORDER BY " + COLUMN_QUESTIONS1_RNO + " DESC", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
            card_subjectData = new CardQuestionsData(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)), res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_CNO)),res.getString(res.getColumnIndex(ID)),res.getString(res.getColumnIndex(COLUMNT_QUESTIONS1_STATUS)));
            array_list.add(card_subjectData);

            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));

            res.moveToNext();

        }
        db.close();

        return array_list;


    }

    public ArrayList<CardQuestionsData> getQuestionsusingRead(String subject, String part, String chapter, String year, String type) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("gggggggg", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'" + " and " + COLUMN_QUESTIONS1_QUE + " = " + "'" + type + "'" + "ORDER BY " + COLUMNT_QUESTIONS1_STATUS, null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
            card_subjectData = new CardQuestionsData(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)), res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_CNO)),res.getString(res.getColumnIndex(ID)),res.getString(res.getColumnIndex(COLUMNT_QUESTIONS1_STATUS)));
            array_list.add(card_subjectData);

            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));

            res.moveToNext();

        }
        db.close();

        return array_list;


    }

    public ArrayList<CardQuestionsData> getQuestionsusingReadSpinner(String subject, String part, String chapter, String year, String spinnervalue) {


//        values.put(ID, chapter.getId());
//        values.put(COLUMN_ID, chapter.getColumnid());
//        values.put(COLUMN_CHAPTER_NAME, chapter.getChapter_name());
//        values.put(COLUMN_CHAPTER_SUBJECT, chapter.getSubject());
//        values.put(COLUMN_CHAPTER_YEAR, chapter.getYear());

        ArrayList<CardQuestionsData> array_list = new ArrayList<CardQuestionsData>();
        CardQuestionsData card_subjectData = null;
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Log.v("gggggggg", "select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'");
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_QUESTIONS1 + " where " + COLUMN_QUESTIONS1_SUBJECT + " =" + "'" + subject + "'" + " and " + COLUMN_QUESTIONS1_PART + " = " + "'" + part + "'" + " and " + COLUMN_QUESTIONS1_CHAPTER + " = " + "'" + chapter + "'" + " and " + COLUMN_QUESTIONS1_YEARS + " = " + "'" + year + "'" + " and " + COLUMN_QUESTIONS1_QUE + " = " + "'" + spinnervalue + "'", null);
        // Log.v("forgetchapter","select "+COLUMN_CHAPTER_NAME+" from " + TABLE_NAME_CHAPTER +"where "+COLUMN_CHAPTER_NAME+" ="+"'"+chaptername+"'");
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            //array_list.add(res.getString(res.getColumnIndex(COLUMN_KEY)) +"@@"+res.getString(res.getColumnIndex(COLUMN_VALUE))+"@@"+res.getString(res.getColumnIndex(COLUMN_FLAG)));

            // array_list.add(res.getString(res.getColumnIndex(ID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CID)) + "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_CATEGORY_NAME))+ "@@" + res.getString(res.getColumnIndex(COLUMN_SUB_YEAR)));


            // array_list.add(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));
            card_subjectData = new CardQuestionsData(res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)), res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_CNO)),res.getString(res.getColumnIndex(ID)),res.getString(res.getColumnIndex(COLUMNT_QUESTIONS1_STATUS)));
            array_list.add(card_subjectData);

            // Log.v("thisisforquestions", res.getString(res.getColumnIndex(COLUMN_QUESTIONS1_QUES)));

            res.moveToNext();

        }
        db.close();

        return array_list;


    }

}
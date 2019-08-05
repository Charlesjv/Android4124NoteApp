package fr.colourz.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends  SQLiteOpenHelper {

    Context context;
    public static String TAG;
    private static String DATABASE_NAME = "mydb.db";
    private static int DATABASE_VERSION = 1;
    private static String createTableQuery = "create table imageinfo (image BLOB)";
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;
    SQLiteDatabase db;
    DatabaseHelper mDBHelper;

    public DatabaseHelper(Context context) {
       super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;


    }

    public DatabaseHelper open() throws SQLException {
        db = mDBHelper.getWritableDatabase();
        return this;
    }


    public void storeImages(Note objectModelClass) {
        try {
            SQLiteDatabase objectSqLiteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getImage();


            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, objectByteArrayOutputStream);

            imageInBytes = objectByteArrayOutputStream.toByteArray();

            ContentValues objectContentValues = new ContentValues();


            objectContentValues.put("image", imageInBytes);

            long checkIfQueryRuns = objectSqLiteDatabase.insert("imageinfo", null, objectContentValues);

            if (checkIfQueryRuns != -1) {

                Toast.makeText(context, "Data added to the table", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();

            } else {
                Toast.makeText(context, "Fails to add note", Toast.LENGTH_SHORT).show();
                objectSqLiteDatabase.close();
            }

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    public void close() {
        mDBHelper.close();
    }


    public Note getImages() throws SQLException {

        Bitmap img;
        db = this.getWritableDatabase();
        Cursor cur = db.query(true, "imageinfo", new String[]{"image"}, null, null, null, null, null, null);

        if (cur.getCount()>0){
            Log.d(TAG,"Count present");
        if (cur.moveToFirst()) {
            byte[] blob = cur.getBlob(cur.getColumnIndex("image"));
             img =  BitmapFactory.decodeByteArray(blob, 0, blob.length);
//            return new Note(context, getPhoto(blob));
            return new Note(img);
       }}
        cur.close();
       return null;
    }

//    public static Bitmap getPhoto(byte[] image) {
//        return BitmapFactory.decodeByteArray(image, 0, image.length);
//    }


    public void onCreate(SQLiteDatabase db) {


        try {
            db.execSQL(createTableQuery);
            Toast.makeText(context, "Table created successfully inside our database", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}





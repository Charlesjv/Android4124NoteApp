package fr.colourz.mynotes;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by ColourZ on 13/03/2018.
 */

public class Note {

    int _id;
    String _title;
    String _content;
    String _dateModified;
    String _dateCreated;
    public Bitmap image;
    public byte arry;
    Context context;

    // Empty constructor
    public Note(int id, String string, String cursorString, String dateCreated, String dateModified, byte[] blob) {

    }

    // Constructor with ID
    public Note(int id, String title, String content, String dateCreated, String dateModified) {
        this._id = id;
        this._title = title;
        this._content = content;
        this._dateCreated = dateCreated;
        this._dateModified = dateModified;

    }

    // Constructor without ID
    public Note(String title, String content, String dateCreated, String dateModified) {
        this._title = title;
        this._content = content;
        this._dateCreated = dateCreated;
        this._dateModified = dateModified;
        this.image = image;
    }



    public Note(String griffith_notes, String s, String s1, String s2, byte arry) {
        this._title = griffith_notes;
        this._content = s;
        this._dateCreated = s1;
        this._dateModified = s2;
        this.arry = arry;
    }


    public Note(Bitmap imageToStore) {
        this.image = imageToStore;
    }


    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        this._content = content;
    }

    public String getDateCreated() {
        return _dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this._dateCreated = dateCreated;
    }

    public String getDateModified() {
        return _dateModified;
    }

    public void setDateModified(String dateModified) {
        this._dateModified = dateModified;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}

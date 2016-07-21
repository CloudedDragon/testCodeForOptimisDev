package com.optimum.optimumme.http.gsonclass;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class GsonCommitContainer {
    private final static String TAG = "GsonCommitContainer";

    @SerializedName("commit")
    private GsonCommit gsonCommit;

    public Date getCommitDate() {

        String dateString = getGsonCommit().getGsonCommitAuthor().getDate().substring(0,10);
        Log.d(TAG,"getCommitDate stringDate:"+dateString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        try {
            commitDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return commitDate;
    }

    public Date getCommitDate(Locale locale) {

        String dateString = getGsonCommit().getGsonCommitAuthor().getDate().substring(0,10);
        Log.d(TAG,"getCommitDate stringDate:"+dateString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",locale);
        Date convertedDate = new Date();
        try {
            commitDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return commitDate;
    }

    public void setCommitDate(Date commitDate) {
        this.commitDate = commitDate;
    }

    private Date commitDate;


    public GsonCommit getGsonCommit() {
        return gsonCommit;
    }


    public void setGsonCommit(GsonCommit gsonCommit) {
        this.gsonCommit = gsonCommit;
    }

    public String toString() {

        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        // determine fields declared in this class only (no fields of
        // superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        // print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                // requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }
}

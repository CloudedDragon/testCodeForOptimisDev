package com.optimum.optimumme.http.gsonclass;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class GsonCommit {

    public GsonCommitAuthor getGsonCommitAuthor() {
        return gsonCommitAuthor;
    }

    public void setGsonCommitAuthor(GsonCommitAuthor gsonCommitAuthor) {
        this.gsonCommitAuthor = gsonCommitAuthor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("author")
    private GsonCommitAuthor gsonCommitAuthor;

    @SerializedName("message")
    private String message;

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

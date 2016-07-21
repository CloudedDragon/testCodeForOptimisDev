package com.optimum.optimumme.http.gsonclass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Bruce_Lan on 2016/7/22.
 */
public class GsonRepository implements Parcelable {

    @SerializedName("name")
    private String name;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("description")
    private String description;
    @SerializedName("pushed_at")
    private String pushedDate;
    @SerializedName("updated_at")
    private String updatedDate;
    @SerializedName("forks_count")
    private String forksCount;
    @SerializedName("language")
    private String language;
    @SerializedName("watchers_count")
    private String watchersCount;

    public GsonRepository(Parcel source) {
        this.name = source.readString();
        this.fullName = source.readString();
        this.description = source.readString();
        this.pushedDate = source.readString();
        this.updatedDate = source.readString();
        this.forksCount = source.readString();
        this.language = source.readString();
        this.watchersCount = source.readString();
    }

    public static final Creator<GsonRepository> CREATOR = new Creator<GsonRepository>() {
        @Override
        public GsonRepository createFromParcel(Parcel in) {
            return new GsonRepository(in);
        }

        @Override
        public GsonRepository[] newArray(int size) {
            return new GsonRepository[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(fullName);
        dest.writeString(description);
        dest.writeString(pushedDate);
        dest.writeString(updatedDate);
        dest.writeString(forksCount);
        dest.writeString(language);
        dest.writeString(watchersCount);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForksCount() {
        return forksCount;
    }

    public void setForksCount(String forksCount) {
        this.forksCount = forksCount;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushedDate() {
        return pushedDate;
    }

    public void setPushedDate(String pushedDate) {
        this.pushedDate = pushedDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getWatchersCount() {
        return watchersCount;
    }

    public void setWatchersCount(String watchersCount) {
        this.watchersCount = watchersCount;
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

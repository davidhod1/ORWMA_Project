package com.example.tough;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

public class Exercise implements Parcelable {
    private String name;
    private String image;
    private String equipment;
    private String description;
    private String type;

    public Exercise() {
    }

    public Exercise(String name, String image, String equipment, String description, String type) {
        this.name = name;
        this.image = image;
        this.equipment = equipment;
        this.description = description;
        this.type = type;
    }

    protected Exercise(Parcel in) {
        name = in.readString();
        image = in.readString();
        equipment = in.readString();
        description = in.readString();
        type = in.readString();
    }

    public static final Creator<Exercise> CREATOR = new Creator<Exercise>() {
        @Override
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        @Override
        public Exercise[] newArray(int size) {
            return new Exercise[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeString(equipment);
        dest.writeString(description);
        dest.writeString(type);
    }
}

package com.example.gadalka;

import android.os.Parcel;
import android.os.Parcelable;

public class Card  implements Parcelable {

    private int id_card;
    private String name;
    private String description;
    private String image;


    protected Card(Parcel in) {
        id_card = in.readInt();
        name = in.readString();
        description = in.readString();
        image = in.readString();
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };

    public void setId(int id) {
        this.id_card = id;
    }

    public void setNameCard(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setImage(String image) {
        image = image;
    }



    public int getId() {
        return id_card;
    }

    public String getNameCard() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }


    public Card(int id, String name, String description, String image) {
        this.id_card = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_card);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(image);
    }
}

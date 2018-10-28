package com.example.user_pc.capstonestage2.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Search implements Parcelable {

    private String url;
    private String imageUrl;
    private String title;
    private String synopsis;
    private String type;
    private int episodes;
    private double score;

    public Search(String url, String imageUrl, String title, String synopsis, String type, int episodes, double score) {
        this.url = url;
        this.imageUrl = imageUrl;
        this.title = title;
        this.synopsis = synopsis;
        this.type = type;
        this.episodes = episodes;
        this.score = score;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(url);
        out.writeString(imageUrl);
        out.writeString(title);
        out.writeString(synopsis);
        out.writeString(type);
        out.writeInt(episodes);
        out.writeDouble(score);
    }

    private Search(Parcel in) {
        this.url = in.readString();
        this.imageUrl = in.readString();
        this.title = in.readString();
        this.synopsis = in.readString();
        this.type = in.readString();
        this.episodes = in.readInt();
        this.score = in.readDouble();
    }

    public Search() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Search> CREATOR = new Creator<Search>() {
        @Override
        public Search createFromParcel(Parcel in) {
            return new Search(in);
        }
        @Override
        public Search[] newArray(int i) {
            return new Search[i];
        }
    };

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getType() {
        return type; }

    public int getEpisodes() {
        return episodes;
    }

    public double getScore() {
        return score;
    }

}

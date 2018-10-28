package com.example.user_pc.capstonestage2.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Schedule implements Serializable {

    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("synopsis")
    private String synopsis;
    @SerializedName("type")
    private String type;
    @SerializedName("airing_start")
    private  String airingStart;
    @SerializedName("episodes")
    private int episodes;
    private double score;

    public Schedule(String url , String title, String imageUrl, String synopsis, String type, String airingStart, int episodes,
                    double score) {
        this.url = url;
        this.title = title;
        this.imageUrl = imageUrl;
        this.synopsis = synopsis;
        this.type = type;
        this.airingStart = airingStart;
        this.episodes = episodes;
        this.score = score;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }
    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getAiringStart() {
        return airingStart;
    }
    public void setAiringStart(String airingStart) {
        this.airingStart = airingStart;
    }

    public int getEpisodes() {
        return episodes;
    }
    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }

}

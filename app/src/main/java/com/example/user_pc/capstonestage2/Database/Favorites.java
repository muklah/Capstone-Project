package com.example.user_pc.capstonestage2.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "favorite")

public class Favorites {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String imageUrl;
    private String synopsis;
    private String type;
    private String episodes;
    private String score;

    @Ignore
    public Favorites(String title, String imageUrl, String synopsis, String type, String episodes, String score) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.synopsis = synopsis;
        this.type = type;
        this.episodes = episodes;
        this.score = score;
    }

    public Favorites(int id, String title, String imageUrl, String synopsis, String type, String episodes, String score) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.synopsis = synopsis;
        this.type = type;
        this.episodes = episodes;
        this.score = score;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getEpisodes() {
        return episodes;
    }
    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;
    }

}

package com.chanki.tmi;

public class Board {
    String boardNum;
    String userId;
    String userName;
    String userMajor;
    String title;
    String contents;
    String hit;
    String time;

    public Board(){

    }

    public Board(String boardNum, String userId, String userName, String userMajor, String title, String contents, String hit, String time) {
        this.boardNum = boardNum;
        this.userId = userId;
        this.userName = userName;
        this.userMajor = userMajor;
        this.title = title;
        this.contents = contents;
        this.hit = hit;
        this.time = time;
    }

    public String getBoardNum() {
        return boardNum;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMajor() {
        return userMajor;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getHit() {
        return hit;
    }

    public String getTime() {
        return time;
    }
}

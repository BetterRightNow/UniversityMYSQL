package com.solvd.laba.model;

import java.util.Date;

public class StudentRankings {
    private int id;
    private int ranking;
    Date rankingDate;
    private int studentsId;

    public StudentRankings() {}

    public StudentRankings(int id, int ranking, Date rankingDate, int studentsId) {
        this.id = id;
        this.ranking = ranking;
        this.rankingDate = rankingDate;
        this.studentsId = studentsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Date getRankingDate() {
        return rankingDate;
    }

    public void setRankingDate(Date rankingDate) {
        this.rankingDate = rankingDate;
    }

    public int getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(int studentsId) {
        this.studentsId = studentsId;
    }
}

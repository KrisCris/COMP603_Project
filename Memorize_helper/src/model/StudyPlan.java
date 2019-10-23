/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ThinkPad
 */
public class StudyPlan {
    private String studyPlanName;
    private int ID;
    private int totalNumber;
    private int totalDay;
    private int remainDay;
    private long startTime;
    private int todayTargetNumber;
    private int todayMemorizedNumber;
    private int todayReviewedNumber;
    
    public StudyPlan() {//develop use only
        this.studyPlanName = "小学人教版一年级上";
        this.totalNumber = 35;
    }
    public StudyPlan(String name, int id, int t_num, int t_day, 
            long start, int today_target){
        this.studyPlanName = name;
        this.ID = id;
        this.totalNumber = t_num;
        this.totalDay = t_day;
        this.startTime = start;
        this.todayTargetNumber = today_target;    
        long now = System.currentTimeMillis();
        int keep = (int) ((now - this.startTime)/(1000 * 24 * 60 * 60));
        this.remainDay = this.totalDay - keep;
    }
    public void setTodayMemorized(int num){
        this.todayMemorizedNumber = num;
    }
    public void setTodayReviewd(int num){
        this.todayReviewedNumber = num;
    }
    
    public String getStudyPlanName() {
        return studyPlanName;
    }

    public StudyPlan setStudyPlanName(String studyPlanName) {
        this.studyPlanName = studyPlanName;
        return this;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public StudyPlan setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
        return this;
    }

    public int getID() {
        return ID;
    }

    public int getTotalDay() {
        return totalDay;
    }

    public int getRemainDay() {
        return remainDay;
    }

    public long getStartTime() {
        return startTime;
    }

    public int getTodayTargetNumber() {
        return todayTargetNumber;
    }

    public int getTodayMemorizedNumber() {
        return todayMemorizedNumber;
    }

    public int getTodayReviewedNumber() {
        return todayReviewedNumber;
    }
    
}

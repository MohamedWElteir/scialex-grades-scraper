package org.example;

public class ResultsProcessor {
    private int unmatchedCount = 0;
    private int matchedCount = 0;
    private int totalHours = 0;
    private int pendingCourses = 0;

    public void setTotalCGPA(double totalCGPA) {
        this.totalCGPA = totalCGPA;
    }

    private  double totalCGPA = 0.0;

    public int getUnmatchedCount() {
        return unmatchedCount;
    }

    public int getMatchedCount() {
        return matchedCount;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public int getPendingCourses() {
        return pendingCourses;
    }

    public double getTotalCGPA() {
        return totalCGPA;
    }
    public void incrementUnmatchedCount(){
        unmatchedCount++;
    }
    public void incrementPendingCourses(){
        pendingCourses++;
    }
    public void incrementMatchedCount(){
        matchedCount++;
    }
    public void addHours(int termHours) {
        totalHours += termHours;
    }

    @Override
    public String toString() {
        return "ResultsProcessor{" +
                "unmatchedCount=" + unmatchedCount +
                ", matchedCount=" + matchedCount +
                ", totalHours=" + totalHours +
                ", pending=" + pendingCourses +
                ", totalCGPA=" + totalCGPA +
                '}';
    }


}

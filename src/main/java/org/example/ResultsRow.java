package org.example;

public class ResultsRow {
     final String courseNumber;
     final String courseCode;
     final String courseName;
     final String grade;
     final double points;
     final int hours;
     final double pointsTimesHours;

    public ResultsRow(String courseNumber, String previousCourseCode, String courseName, String grade, double points, int hours, double pointsTimesHours) {
        this.courseNumber = courseNumber;
        this.courseCode = previousCourseCode;
        this.courseName = courseName;
        this.grade = grade;
        this.points = points;
        this.hours = hours;
        this.pointsTimesHours = pointsTimesHours;
    }


    @Override
    public String toString() {
        return "{\n" +
                "  courseNumber='" + courseNumber + "',\n" +
                "  courseCode='" + courseCode + "',\n" +
                "  courseName='" + courseName + "',\n" +
                "  grade='" + grade + "',\n" +
                "  points=" + points + ",\n" +
                "  hours=" + hours + ",\n" +
                "  pointsTimesHours=" + pointsTimesHours + "\n" +
                '}';
    }
}

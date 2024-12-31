package org.example;

public class ResultsRow {
    private final String courseNumber;
    private final String courseCode;
    private final String courseName;
    private final String grade;
    private final double points;
    private final int hours;
    private final double pointsTimesHours;

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

package org.example;

import java.util.List;

public class ResultsTable {

    private final String tableLabel;
    List<ResultsRow> rows;
    private final double semesterGPA;
    private final double cgpa;
    private final int semesterLoad;
    private final int totalHours;

    public ResultsTable(String tableLabel, List<ResultsRow> rows, double semesterGPA, double cgpa, int semesterLoad, int totalHours) {
        this.tableLabel = tableLabel;
        this.rows = rows;
        this.semesterGPA = semesterGPA;
        this.cgpa = cgpa;
        this.semesterLoad = semesterLoad;
        this.totalHours = totalHours;
    }


    @Override
    public String toString() {
        return "ResultsTable{\n" +
                "  tableLabel='" + tableLabel + "',\n" +
                "  rows=" + rows + ",\n" +
                "  semesterGPA=" + semesterGPA + ",\n" +
                "  cgpa=" + cgpa + ",\n" +
                "  semesterLoad=" + semesterLoad + ",\n" +
                "  totalHours=" + totalHours + "\n" +
                '}';
    }
}

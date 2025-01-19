package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

public class Main {
    static int unmatchedCount =0,matchedCount=0,totalHours,pending=0;
    static double totalCGPA;
    public static void main(String[] args){
        File file = new File("src/main/resources/output");

        ArrayList<ResultsTable> tables = new ArrayList<>();
        Dotenv dotenv = Dotenv.load();
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        try{
            driver.get("https://www.scialex.org/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("InputUsername")));
            usernameField.sendKeys(dotenv.get("LOGIN_USERNAME"));
            Thread.sleep(1000);
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("InputPassword")));
            passwordField.sendKeys(dotenv.get("LOGIN_PASSWORD"));
            Thread.sleep(4005);
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginAcc")));
            loginButton.click();
            Thread.sleep(1500);
            String url = driver.getCurrentUrl();
            String token = extractFrom(url);
            System.out.println("Token: "+token);
            String results_page = "https://www.scialex.org/F/" + token + "/2018/Student/Results.aspx";
            System.out.println(results_page);
            driver.get(results_page);

            int numberOfTables = 9;
            ArrayList<ResultsRow> coursesRows;

            for (int counter = 0; counter < numberOfTables; counter++) {
                coursesRows = new ArrayList<>();
                String[] collected = driver.findElement(By.id(dotenv.get("LABEL")+ "_GridView1_" + counter))
                        .getText()
                        .split("\n");
                String tableLabel = driver.findElement(By.id(dotenv.get("LABEL") + "_SemesterNameLabel_" + counter)).getText();
                double semesterGPA = Double.parseDouble(driver.findElement(By.id(dotenv.get("LABEL") + "_FormView1_" + counter + "_GPALabel")).getText());
                double cgpa = Double.parseDouble(driver.findElement(By.id(dotenv.get("LABEL") + "_FormView1_" + counter + "_CGPALabel")).getText());
                int semesterLoad = Integer.parseInt(driver.findElement(By.id(dotenv.get("LABEL") + "_FormView1_" + counter + "_SemesterLoadLabel")).getText());
                int totalHoursLeft = Integer.parseInt(driver.findElement(By.id(dotenv.get("LABEL") + "_FormView1_" + counter + "_SemesterCHLabel")).getText());
                int totalHoursRight = Integer.parseInt(driver.findElement(By.id(dotenv.get("LABEL") + "_FormView1_" + counter + "_SemesterSUCHLabe")).getText());
                int termHours = totalHoursLeft + totalHoursRight;
                totalHours += termHours;
                if(counter == numberOfTables - 1){
                    totalCGPA = cgpa;
                }
                for (int i = 7; i < collected.length; i++) {
                    String record = collected[i];
                    ResultsRow parsedRow = parseRecord(record);
                    if (parsedRow != null)  coursesRows.add(parsedRow);
                    if(parsedRow != null && parsedRow.grade.equals("P")) pending++;
                }
                tables.add(new ResultsTable(tableLabel, coursesRows, semesterGPA, cgpa, semesterLoad, termHours));

            }


            FileWriter writer = new FileWriter(file);
            for (ResultsTable table : tables) {
                if(file.canWrite()){
                    writer.write(table.toString());
                }
                System.out.println(table);
            }
            writer.close();

        }catch (Exception e){
            System.err.println("An error occurred: " + e.getMessage());
        }finally {
            driver.quit();
            System.out.println("CGPA: "+ totalCGPA + "\nTotal Credit Hours: " + totalHours);
            System.out.println("Matched: " + matchedCount + " rows.");
            System.out.println("Pending grades: " + pending);
            System.err.println("Unmatched: " + unmatchedCount + " rows.");
        }

    }

    static String extractFrom(String url) {
        int startIndex = url.indexOf("/S") + 3;
        int endIndex = url.indexOf("/Student");

        return (startIndex > 2 && endIndex > startIndex) ? url.substring(startIndex, endIndex) : null;
    }

    static ResultsRow parseRecord(String record) {
        String regex1 = "(\\d{9})\\s+(.*?)\\s+([A-D][+-]?|P|حذف|إستبيان|F)\\s+(\\d\\.\\d{2})\\s+(\\d)\\s+(\\d\\.\\d{2})";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher = pattern.matcher(record);

        if (matcher.find()) {
            try {
                String courseCode = matcher.group(1);
                String[] nameParts = matcher.group(2).split(" ", 2);
                String oldCourseCode = nameParts.length > 1 ? nameParts[0] : "";
                String courseName = nameParts.length > 1 ? nameParts[1] : nameParts[0];
                String grade = matcher.group(3);
                double points = parseDouble(matcher.group(4));
                int hours = Integer.parseInt(matcher.group(5));
                double totalPoints = parseDouble(matcher.group(6));
                matchedCount++;
                return new ResultsRow(courseCode, oldCourseCode, courseName, grade, points, hours, totalPoints);
            } catch (Exception e) {
                System.err.println("Error parsing record: " + record + " " + e.getMessage());
            }
        } else {
            String regex2 = "(\\d{9})\\s+(\\d{5})\\s+(.+?)\\s+([A-D][+-]?|P|حذف|إستبيان|F)\\s+(\\d\\.\\d{2})\\s+(\\d+)\\s+(\\d+\\.\\d{2})";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(record);

            if (matcher2.find()) {
                try {
                    String courseCode = matcher2.group(1);
                    String oldCourseCode = matcher2.group(2);
                    String courseName = matcher2.group(3);
                    String grade = matcher2.group(4);
                    double points = parseDouble(matcher2.group(5));
                    int hours = Integer.parseInt(matcher2.group(6));
                    double totalPoints = parseDouble(matcher2.group(7));
                    matchedCount++;
                    return new ResultsRow(courseCode, oldCourseCode, courseName, grade, points, hours, totalPoints);
                } catch (Exception e) {
                    System.err.println("Error parsing record with secondary regex: " + record + " " + e.getMessage());
                    unmatchedCount++;
                }
            } else {
                System.err.println("No match found for record: " + record);
                unmatchedCount++;
            }
        }
        return null;
    }

}


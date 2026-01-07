/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cch2;

/**
 *
 * @author summer
 */
public class Cch2 {
    public static void main(String[] args) {
        // Step 1: Initialize the array
        String[] winningNumbers = {"12-34-56-78-90", "33-44-11-66-22", "01-02-03-04-05"};

        double highestAverage = 0.0;
        String highestAverageNumber = "";

        // Step 2: Analyze each number
        for (String number : winningNumbers) {
            System.out.println("Analyzing: " + number);

            // Remove dashes
            String noDashes = number.replace("-", "");

            // Convert to char array and then to integer array
            char[] chars = noDashes.toCharArray();
            int[] digits = new int[chars.length];

            int sum = 0;
            int index = 0;

            // Use for-each loop to convert characters to integers and calculate sum
            for (char ch : chars) {
                int digit = Character.getNumericValue(ch);
                digits[index++] = digit;
                sum += digit;
            }

            double average = (double) sum / digits.length;

            System.out.println("Digit Sum: " + sum + ", Digit Average: " + average);

            // Keep track of the highest average
            if (average > highestAverage) {
                highestAverage = average;
                highestAverageNumber = number;
            }
        }

        // Step 4: Print final result
        System.out.println("The winning number with the highest average is: " 
            + highestAverageNumber + " with an average of " + highestAverage);
    }
}


    
     
    


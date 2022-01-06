package com.teachmeskills;

import com.teachmeskills.model.DocumentNumbers;

import java.io.IOException;

/**
 * Let's say there is a file with document numbers.
 * The document number is a string consisting of letters and numbers (without service characters).
 * Let this file contain each document number on a new line and no other information on the line,
 * only the document number.
 * A valid document number must be 15 characters long and begin with the sequence docnum
 * (hereinafter any sequence of letters / numbers) or сontract (hereinafter any sequence of letters / numbers).
 * Write a program to read information from the input file - the path to the input
 * file must be specified via the console.
 * The program should check the document numbers for validity.
 * Valid document numbers should be recorded in one report file.
 * Invalid document numbers should be recorded in another report file, but after the document numbers,
 * you should add information about why this document is invalid.
 */

public class Main {
    public static void main(String[] args) {
        DocumentNumbers documentNumbers = new DocumentNumbers();
        try {
            documentNumbers.selectDocumentNumbersFile();
            documentNumbers.generateReport();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
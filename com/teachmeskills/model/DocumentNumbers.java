package com.teachmeskills.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DocumentNumbers {
    private static int reportId = 0;

    private List<String> documentNumbers;
    private List<String> wrongDocumentNumbers;

    public void selectDocumentNumbersFile() throws IOException {
        System.out.print("Specify the path to the file with document numbers: ");
        Path path = enterPath();
        if (!Files.exists(path)) {
            throw new FileNotFoundException("The file was not found in the specified path.");
        }
        List<String> linesFromFile = Files.readAllLines(path);
        documentNumbers = new ArrayList<>();
        wrongDocumentNumbers = new ArrayList<>();
        for (String line : linesFromFile) {
            if (isDocumentNumber(line)) {
                documentNumbers.add(line);
            } else {
                wrongDocumentNumbers.add(line);
            }
        }
    }

    public void generateReport() throws IOException {
        Path reportPath = Path.of("src/com/teachmeskills/report/" + "report_" + (++reportId) + ".txt");
        Path reportWrongPath = Path.of("src/com/teachmeskills/report/" + "report_wrong_" + reportId + ".txt");
        try {
            Files.createFile(reportPath);
            Files.createFile(reportWrongPath);
        } catch (FileAlreadyExistsException exception) {
            System.out.println("Report files already exist");
            return;
        }
        for (String documentNumber : documentNumbers) {
            Files.writeString(reportPath, documentNumber + '\n', StandardOpenOption.APPEND);
        }
        for (String wrongDocumentNumber : wrongDocumentNumbers) {
            Files.writeString(reportWrongPath, wrongDocumentNumber + " - " +
                    getDocumentNumberProblem(wrongDocumentNumber) + '\n', StandardOpenOption.APPEND);
        }
    }

    public static boolean isDocumentNumber(String documentNumber) {
        return documentNumber.matches("contract\\w{7}") || documentNumber.matches("docnum\\w{9}");
    }

    private Path enterPath() {
        Scanner scanner = new Scanner(System.in);
        return Path.of(scanner.nextLine());
    }

    private String getDocumentNumberProblem(String wrongDocumentNumber) {
        if (wrongDocumentNumber.length() != 15) {
            return "The document number must be 15 characters long!";
        } else if (!wrongDocumentNumber.startsWith("contract") && !wrongDocumentNumber.startsWith("docnum")) {
            return "Document number must start with sequences 'contract' or 'docnum'!";
        } else {
            return "Document number contains invalid characters!";
        }
    }
}
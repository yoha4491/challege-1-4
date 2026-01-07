package com.example.chapter4challenge1;

import java.util.List;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        DiaryManager diaryManager = new DiaryManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Personal Diary Manager!");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Write Entry");
            System.out.println("2. Read Entry");
            System.out.println("3. Search Entries");
            System.out.println("4. Backup Entries");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter your diary entry (press Enter to save):");
                    String content = scanner.nextLine();
                    diaryManager.writeEntry(content);
                    break;
                case "2":
                    List<String> files = diaryManager.getEntryFiles();
                    if (files.isEmpty()) {
                        System.out.println("No entries found.");
                    } else {
                        System.out.println("Available entries:");
                        for (int i = 0; i < files.size(); i++) {
                            System.out.println((i + 1) + ". " + files.get(i));
                        }
                        System.out.print("Enter the number of the entry to read: ");
                        try {
                            int fileIndex = Integer.parseInt(scanner.nextLine()) - 1;
                            if (fileIndex >= 0 && fileIndex < files.size()) {
                                diaryManager.readEntry(files.get(fileIndex));
                            } else {
                                System.out.println("Invalid selection.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                        }
                    }
                    break;
                case "3":
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    diaryManager.searchEntries(keyword);
                    break;
                case "4":
                    diaryManager.backupEntries();
                    break;
                case "5":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }
}

package com.example.chapter4challenge1;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DiaryManager {
    private static final String ENTRIES_DIR = "entries";
    private static final String CONFIG_FILE = "diary_config.ser";
    private static final DateTimeFormatter FILE_NAME_FORMATTER = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
    private DiaryConfig config;

    public DiaryManager() {
        try {
            Files.createDirectories(Paths.get(ENTRIES_DIR));
            loadConfig();
        } catch (IOException e) {
            System.err.println("Could not create entries directory: " + e.getMessage());
        }
    }

    private void loadConfig() {
        Path configPath = Paths.get(CONFIG_FILE);
        if (Files.exists(configPath)) {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(configPath))) {
                config = (DiaryConfig) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading config: " + e.getMessage());
                config = new DiaryConfig("User");
            }
        } else {
            config = new DiaryConfig("User");
        }
    }

    private void saveConfig() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(CONFIG_FILE)))) {
            oos.writeObject(config);
        } catch (IOException e) {
            System.err.println("Error saving config: " + e.getMessage());
        }
    }

    public void writeEntry(String content) {
        String timestamp = LocalDateTime.now().format(FILE_NAME_FORMATTER);
        String filename = "diary_" + timestamp + ".txt";
        Path path = Paths.get(ENTRIES_DIR, filename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(content);
            System.out.println("Entry saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing entry: " + e.getMessage());
        }
    }

    public List<String> getEntryFiles() {
        try (Stream<Path> stream = Files.list(Paths.get(ENTRIES_DIR))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error listing entries: " + e.getMessage());
            return List.of();
        }
    }

    public void readEntry(String filename) {
        Path path = Paths.get(ENTRIES_DIR, filename);
        if (!Files.exists(path)) {
            System.out.println("File not found: " + filename);
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println("--- Reading " + filename + " ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("-----------------------------");
        } catch (IOException e) {
            System.err.println("Error reading entry: " + e.getMessage());
        }
    }

    public void searchEntries(String keyword) {
        try (Stream<Path> stream = Files.list(Paths.get(ENTRIES_DIR))) {
            List<Path> files = stream
                    .filter(file -> !Files.isDirectory(file))
                    .collect(Collectors.toList());

            boolean found = false;
            for (Path file : files) {
                try (BufferedReader reader = Files.newBufferedReader(file)) {
                    String line;
                    int lineNumber = 1;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains(keyword)) {
                            System.out.println("Found in " + file.getFileName() + " (Line " + lineNumber + "): " + line);
                            found = true;
                        }
                        lineNumber++;
                    }
                }
            }
            if (!found) {
                System.out.println("No entries found containing keyword: " + keyword);
            }
        } catch (IOException e) {
            System.err.println("Error searching entries: " + e.getMessage());
        }
    }

    public void backupEntries() {
        String timestamp = LocalDateTime.now().format(FILE_NAME_FORMATTER);
        String zipFileName = "backup_" + timestamp + ".zip";
        Path zipPath = Paths.get(zipFileName);

        try (ZipOutputStream zos = new ZipOutputStream(Files.newOutputStream(zipPath));
             Stream<Path> stream = Files.list(Paths.get(ENTRIES_DIR))) {

            List<Path> files = stream
                    .filter(file -> !Files.isDirectory(file))
                    .collect(Collectors.toList());

            for (Path file : files) {
                ZipEntry zipEntry = new ZipEntry(file.getFileName().toString());
                zos.putNextEntry(zipEntry);
                Files.copy(file, zos);
                zos.closeEntry();
            }
            System.out.println("Backup created: " + zipFileName);
            
            config.setLastBackup(LocalDateTime.now());
            saveConfig();

        } catch (IOException e) {
            System.err.println("Error creating backup: " + e.getMessage());
        }
    }
}

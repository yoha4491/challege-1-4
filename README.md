# Personal Diary Manager

A command-line diary application that allows users to write entries, read previous entries, and manage their diary files.

## Features

*   **Write Mode**: Create new diary entries with automatic timestamping.
*   **Read Mode**: View a list of all entries and select one to read.
*   **Search Functionality**: Search for entries containing specific keywords.
*   **Backup**: Create a ZIP archive of all diary entries.

## Design Choices

*   **File Storage**: Each entry is stored as a separate text file in the `entries/` directory. The filename includes the timestamp (e.g., `diary_2023_10_25_14_30_45.txt`) for easy organization and retrieval.
*   **Java NIO**: The `java.nio.file` package (Files, Paths) is used for all file operations, ensuring modern and efficient I/O handling.
*   **Timestamps**: `LocalDateTime` and `DateTimeFormatter` are used to generate precise and readable timestamps for filenames.
*   **Menu System**: A simple console-based menu allows users to navigate the application's features.
*   **Backup**: The backup feature uses `ZipOutputStream` to compress all entry files into a single ZIP archive.

## How to Use

1.  **Run the Application**: Execute the `Launcher` class.
2.  **Menu Options**:
    *   **1. Write Entry**: Type your entry and press Enter to save.
    *   **2. Read Entry**: Select an entry from the list to view its contents.
    *   **3. Search Entries**: Enter a keyword to find matching entries.
    *   **4. Backup Entries**: Create a ZIP backup of all your entries.
    *   **5. Exit**: Close the application.

## Requirements

*   Java 11 or higher

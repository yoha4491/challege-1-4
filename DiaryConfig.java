package com.example.chapter4challenge1;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DiaryConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ownerName;
    private LocalDateTime lastBackup;

    public DiaryConfig(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getLastBackup() {
        return lastBackup;
    }

    public void setLastBackup(LocalDateTime lastBackup) {
        this.lastBackup = lastBackup;
    }
}

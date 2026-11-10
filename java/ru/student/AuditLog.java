package ru.student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuditLog {

    private final List<String> entries = new ArrayList<>();

    public void record(String entry) {
        entries.add(entry);
    }

    public List<String> getEntries() {
        return Collections.unmodifiableList(entries);
    }

    public void clear() {
        entries.clear();
    }
}
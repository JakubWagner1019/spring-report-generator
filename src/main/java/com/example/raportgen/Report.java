package com.example.raportgen;

import java.text.MessageFormat;
import java.time.LocalDateTime;

public class Report {
    private final String name;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String reportContent;

    public Report(String name, LocalDateTime from, LocalDateTime to) {
        this.name = name;
        this.from = from;
        this.to = to;
        this.reportContent = MessageFormat.format("{2} {0}:{1}", this.from, this.to, this.name);
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public String getReportContent() {
        return reportContent;
    }
}

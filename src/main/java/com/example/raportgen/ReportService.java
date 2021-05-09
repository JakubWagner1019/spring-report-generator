package com.example.raportgen;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface ReportService {
    long generateReport(String name, LocalDateTime from, LocalDateTime to);

    Optional<Report> getReport(long id);

    Map<Long, Report> getAllReports();
}

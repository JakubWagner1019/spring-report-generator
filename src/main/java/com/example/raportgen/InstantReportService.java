package com.example.raportgen;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Profile("instant")
public class InstantReportService implements ReportService {

    protected AtomicLong current = new AtomicLong();
    protected Map<Long, Report> reportMap = new ConcurrentHashMap<>();

    @Override
    public long generateReport(String name, LocalDateTime from, LocalDateTime to) {
        long id = current.getAndIncrement();
        reportMap.put(id, new Report(name, from, to));
        return id;
    }

    @Override
    public final Optional<Report> getReport(long id) {
        return Optional.ofNullable(reportMap.get(id));
    }

    @Override
    public final Map<Long, Report> getAllReports() {
        return Collections.unmodifiableMap(reportMap);
    }
}

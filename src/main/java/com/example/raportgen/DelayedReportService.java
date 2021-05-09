package com.example.raportgen;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Profile("delayed")
public class DelayedReportService extends InstantReportService {
    private static final int DELAY = 1000;

    @Override
    public long generateReport(String name, LocalDateTime from, LocalDateTime to) {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return super.generateReport(name, from, to);
    }
}

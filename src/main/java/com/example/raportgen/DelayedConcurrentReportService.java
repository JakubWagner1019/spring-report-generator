package com.example.raportgen;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Profile("concurrent")
public class DelayedConcurrentReportService extends InstantReportService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public long generateReport(String name, LocalDateTime from, LocalDateTime to) {
        long id = current.getAndIncrement();
        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reportMap.put(id, new Report(name, from, to));
        });
        return id;
    }
}

package com.example.raportgen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping
public class ReportController {

    private final Logger logger = LoggerFactory.getLogger(ReportController.class);

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping
    public String showIndexPage() {
        return "index";
    }

    @PostMapping("/report")
    public String generateReport(@RequestParam("name") String name, @RequestParam("from") String from, @RequestParam("to") String to, Model model){
        logger.info("from: {} to: {}", from, to);
        LocalDateTime fromConverted = LocalDateTime.parse(from + ":00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime toConverted = LocalDateTime.parse(to + ":00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        long id = reportService.generateReport(name, fromConverted, toConverted);
        return "redirect:/report/" + id;
    }

    @GetMapping("/report/{id}")
    public String checkReport(@PathVariable("id") long id, Model model){
        Optional<Report> optionalReport = reportService.getReport(id);
        if (optionalReport.isPresent()) {
            Report report = optionalReport.get();
            model.addAttribute("report", report);
            model.addAttribute("report_id", id);
            return "report";
        } else {
            model.addAttribute("report_id", id);
            return "wait";
        }
    }

}

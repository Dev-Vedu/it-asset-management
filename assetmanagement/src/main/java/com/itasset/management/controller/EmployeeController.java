package com.itasset.management.controller;

import com.itasset.management.model.User;
import com.itasset.management.model.Issue;
import com.itasset.management.service.AssetService;
import com.itasset.management.service.IssueService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private AssetService assetService;

    @Autowired
    private IssueService issueService;

    // Employee Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        // ✅ USE employeeId (NOT id)
        model.addAttribute("assets",
                assetService.getAssetsByUser(user.getEmployeeId()));

        return "employee/dashboard";
    }

    // Show Report Issue Page
    @GetMapping("/report-issue")
    public String reportIssuePage(Model model) {
        model.addAttribute("issue", new Issue());
        return "employee/report-issue";
    }
    @GetMapping("/profile")
    public String employeeProfile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "employee/profile";
    }

    @PostMapping("/report-issue")
    public String reportIssue(@ModelAttribute Issue issue,
                              HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        issue.setStatus("OPEN");

        issue.setEmployee(user);

        issueService.save(issue);

        return "redirect:/employee/dashboard";
    }


}

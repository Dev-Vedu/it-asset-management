package com.itasset.management.controller;

import com.itasset.management.model.Issue;
import com.itasset.management.model.User;
import com.itasset.management.service.IssueService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private IssueService issueService;

    // Worker Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        if (user.getWorkerType() == null) {
            model.addAttribute("issues", issueService.getAllIssues());
        } else {
            model.addAttribute("issues",
                    issueService.getIssuesByType(user.getWorkerType()));
        }

        return "worker/dashboard";
    }


    // Update Issue Status
    @GetMapping("/update/{id}")
    public String updateStatus(@PathVariable Long id) {

        Issue issue = issueService.getAllIssues()
                .stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (issue != null) {
            issue.setStatus("RESOLVED");
            issueService.save(issue);
        }

        return "redirect:/worker/dashboard";
    }
    @GetMapping("/profile")
    public String workerProfile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/";
        }

        model.addAttribute("user", user);

        return "worker/profile";
    }

}

package com.itasset.management.service;

import com.itasset.management.model.Issue;
import com.itasset.management.model.User;
import com.itasset.management.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    // ✅ SAVE ISSUE
    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }
    public long countByStatusAndType(String status, String type) {
        return issueRepository.countByStatusAndType(status, type);
    }

    // ✅ GET BY TYPE (Hardware / Software)
    public List<Issue> getIssuesByType(String type) {
        return issueRepository.findByType(type);
    }

    // ✅ GET EMPLOYEE ISSUES (FIXED)
    public List<Issue> getIssuesByEmployee(User user) {
        return issueRepository.findByEmployee(user);
    }

    // ✅ GET WORKER ASSIGNED ISSUES
    public List<Issue> getIssuesByWorker(User user) {
        return issueRepository.findByWorker(user);
    }

    // ✅ GET ALL ISSUES
    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    // ✅ FILTER BY TYPE (OPTIONAL)
    public List<Issue> getIssuesByTypeAndStatus(String type) {
        return issueRepository.findAll()
                .stream()
                .filter(i -> i.getType() != null &&
                        i.getType().equalsIgnoreCase(type))
                .toList();
    }

    // ✅ TOTAL ISSUE COUNT (FOR ADMIN DASHBOARD)
    public long countAllIssues() {
        return issueRepository.count();
    }

    // ✅ COUNT BY STATUS (FAST - DB LEVEL)
    public long countByStatus(String status) {
        return issueRepository.countByStatusIgnoreCase(status);
    }


    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElse(null);
    }

}

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

    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    public List<Issue> getIssuesByType(String type) {
        return issueRepository.findByType(type);
    }

    public List<Issue> getIssuesByEmployee(User user) {
        return issueRepository.findByEmployee_Id(user.getId());
    }


    public List<Issue> getIssuesByWorker(User user) {
        return issueRepository.findByWorker(user);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public List<Issue> getIssuesByTypeAndStatus(String type) {
        return issueRepository.findAll()
                .stream()
                .filter(i -> i.getType().equals(type))
                .toList();
    }


}

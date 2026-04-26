package com.itasset.management.repository;

import com.itasset.management.model.Issue;
import com.itasset.management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByType(String type);
    List<Issue> findByEmployee(User employee);

    List<Issue> findByWorker(User worker);
    List<Issue> findByEmployee_Id(Long id);
    long countByStatusAndType(String status, String type);

    long countByStatusIgnoreCase(String status);


}

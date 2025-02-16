package com.repotracker.controller;

import com.repotracker.client.GitHubClient;
import com.repotracker.controller.dto.GetAllReposWithBranchesResponseDto;
import com.repotracker.service.RepoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class RepoTrackerRestController {

    RepoService repoService;
    GitHubClient gitHubClient;

    public RepoTrackerRestController(RepoService repoService, GitHubClient gitHubClient) {
        this.repoService = repoService;
        this.gitHubClient = gitHubClient;
    }

    @GetMapping(path = "/{userName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GetAllReposWithBranchesResponseDto>> getRepo(@PathVariable String userName) {
        List<GetAllReposWithBranchesResponseDto> response = repoService.fetchRepositoriesWithBranches(userName);
        log.info("fetched repos with branches owner: " + userName);
        return ResponseEntity.ok(response);
    }
}

package com.repotracker.service;

import com.repotracker.client.GitHubClient;
import com.repotracker.client.dto.AllRepositoriesResult;
import com.repotracker.client.dto.Commit;
import com.repotracker.controller.dto.GetAllBranchesResponseDto;
import com.repotracker.controller.dto.GetAllReposWithBranchesResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RepoService {

    private final GitHubClient gitHubClient;

    public RepoService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public List<GetAllReposWithBranchesResponseDto> fetchRepositoriesWithBranches(String userName) {
        return fetchRepositories(userName)
                .stream()
                .filter(repo -> !repo.fork())
                .map(repo ->
                        new GetAllReposWithBranchesResponseDto(
                                repo.owner().login(),
                                repo.name(),
                                false,
                                fetchBranches(repo.owner().login(), repo.name()))
                )
                .toList();
    }

    private List<AllRepositoriesResult> fetchRepositories(String userName) {
        return gitHubClient.getOwnerAndRepositories(userName);
    }

    private List<GetAllBranchesResponseDto> fetchBranches(String owner, String repoName) {
        return gitHubClient.getBranchesByOwnerAndRepositories(owner, repoName).stream()
                .map(branch -> new GetAllBranchesResponseDto(
                        branch.name(),
                        Optional.ofNullable(branch.commit())
                                .map(Commit::sha)
                                .orElse("UNKNOWN_SHA")
                ))
                .toList();
    }
}



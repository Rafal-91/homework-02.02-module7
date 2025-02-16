package com.repotracker.client;

import com.repotracker.config.FeignConfig;
import com.repotracker.client.dto.AllBranchesResult;
import com.repotracker.client.dto.AllRepositoriesResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//https://api.github.com/users/kalqa/repos
//https://api.github.com/repos/kalqa/03-open-feign/branches
@FeignClient(name = "git-hub-client", url = "https://api.github.com", configuration = FeignConfig.class)
public interface GitHubClient {

    @GetMapping("/users/{userName}/repos")
    List<AllRepositoriesResult> getOwnerAndRepositories(@PathVariable("userName") String userName, @RequestParam("per_page") int perPage);

    @GetMapping("/repos/{userName}/{repositoryName}/branches")
    List<AllBranchesResult> getBranchesByOwnerAndRepositories(@PathVariable("userName") String userName, @PathVariable("repositoryName") String repositoryName);

}

package com.repotracker.controller.dto;

import java.util.List;

public record GetAllReposWithBranchesResponseDto(String ownerLogin,
                                                 String repositoryName,
                                                 boolean fork,
                                                 List<GetAllBranchesResponseDto> branches) {
}

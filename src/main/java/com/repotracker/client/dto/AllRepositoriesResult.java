package com.repotracker.client.dto;

public record AllRepositoriesResult(Owner owner, String name, boolean fork) {

}

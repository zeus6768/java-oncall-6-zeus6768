package oncall.service;

import oncall.repository.Repository;

public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }
}

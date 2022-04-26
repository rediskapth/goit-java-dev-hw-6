package ua.goit.service;

import ua.goit.model.convert.CompaniesConverter;
import ua.goit.model.dao.CompaniesDao;
import ua.goit.model.dto.CompaniesDto;
import ua.goit.repository.Repository;

public class CompaniesService {
    private final CompaniesConverter converter;
    private final Repository<CompaniesDao> repository;

    public CompaniesService(CompaniesConverter converter, Repository<CompaniesDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(CompaniesDto dto) {
        repository.save(converter.convert(dto));
    }

    public CompaniesDto findById(int id) {
        return converter.convert(repository.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Company with id %d not found", id))));
    }

    public int update(CompaniesDto dto) {
        return repository.update(converter.convert(dto));
    }

    public void remove(CompaniesDto dto) {
        repository.remove(converter.convert(dto));
    }

}

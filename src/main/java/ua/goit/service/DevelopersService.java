package ua.goit.service;

import ua.goit.model.convert.DevelopersConverter;
import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;
import ua.goit.repository.Repository;

public class DevelopersService {
    private final DevelopersConverter converter;
    private final Repository<DevelopersDao> repository;

    public DevelopersService(DevelopersConverter converter, Repository<DevelopersDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(DevelopersDto dto) {
        repository.save(converter.convert(dto));
    }

    public DevelopersDto findById(int id) {
        return converter.convert(repository.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Developer with id %d not found", id))));
    }

    public int update(DevelopersDto dto) {
        return repository.update(converter.convert(dto));
    }

    public void remove(DevelopersDto dto) {
        repository.remove(converter.convert(dto));
    }

}

package ua.goit.service;

import ua.goit.model.convert.CustomersConverter;
import ua.goit.model.dao.CustomersDao;
import ua.goit.model.dto.CustomersDto;
import ua.goit.repository.Repository;

public class CustomersService {
    private final CustomersConverter converter;
    private final Repository<CustomersDao> repository;

    public CustomersService(CustomersConverter converter, Repository<CustomersDao> repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(CustomersDto dto) {
        repository.save(converter.convert(dto));
    }

    public CustomersDto findById(int id) {
        return converter.convert(repository.findById(id).orElseThrow(() -> new IllegalArgumentException
                (String.format("Customer with id %d not found", id))));
    }

    public int update(CustomersDto dto) {
        return repository.update(converter.convert(dto));
    }

    public void remove(CustomersDto dto) {
        repository.remove(converter.convert(dto));
    }

}

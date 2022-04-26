package ua.goit.service;

import ua.goit.model.convert.CustomersProjectsConverter;
import ua.goit.model.dto.CustomersProjectsDto;
import ua.goit.repository.CustomersProjectsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomersProjectsService {
    private final CustomersProjectsConverter converter;
    private final CustomersProjectsRepository repository;

    public CustomersProjectsService(CustomersProjectsConverter converter, CustomersProjectsRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(CustomersProjectsDto dto) {
        repository.save(converter.convert(dto));
    }

    public List<CustomersProjectsDto> findByCustomerId(int id) {
        final List<CustomersProjectsDto> collect = repository.findByCustomerId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Customer with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public List<CustomersProjectsDto> findByProjectId(int id) {
        final List<CustomersProjectsDto> collect = repository.findByProjectId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Project with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public int update(CustomersProjectsDto dtoNew, CustomersProjectsDto dtoOld) {
        return repository.update(converter.convert(dtoNew), converter.convert(dtoOld));
    }

    public void remove(CustomersProjectsDto dto) {
        repository.remove(converter.convert(dto));
    }
}

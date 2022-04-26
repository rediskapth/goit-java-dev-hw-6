package ua.goit.service;

import ua.goit.model.convert.CompaniesProjectsConverter;
import ua.goit.model.dto.CompaniesProjectsDto;
import ua.goit.repository.CompaniesProjectsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CompaniesProjectsService {
    private final CompaniesProjectsConverter converter;
    private final CompaniesProjectsRepository repository;

    public CompaniesProjectsService(CompaniesProjectsConverter converter, CompaniesProjectsRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(CompaniesProjectsDto dto) {
        repository.save(converter.convert(dto));
    }

    public List<CompaniesProjectsDto> findByCompanyId(int id) {
        final List<CompaniesProjectsDto> collect = repository.findByCompanyId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Company with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public List<CompaniesProjectsDto> findByProjectId(int id) {
        final List<CompaniesProjectsDto> collect = repository.findByProjectId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Projects with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public int update(CompaniesProjectsDto dtoNew, CompaniesProjectsDto dtoOld) {
        return repository.update(converter.convert(dtoNew), converter.convert(dtoOld));
    }

    public void remove(CompaniesProjectsDto dto) {
        repository.remove(converter.convert(dto));
    }
}

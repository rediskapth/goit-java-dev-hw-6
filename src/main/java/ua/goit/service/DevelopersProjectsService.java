package ua.goit.service;

import ua.goit.model.convert.DevelopersProjectsConverter;
import ua.goit.model.dto.DevelopersProjectsDto;
import ua.goit.repository.DevelopersProjectsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DevelopersProjectsService {
    private final DevelopersProjectsConverter converter;
    private final DevelopersProjectsRepository repository;

    public DevelopersProjectsService(DevelopersProjectsConverter converter, DevelopersProjectsRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(DevelopersProjectsDto dto) {
        repository.save(converter.convert(dto));
    }

    public List<DevelopersProjectsDto> findByDeveloperId(int id) {
        final List<DevelopersProjectsDto> collect = repository.findByDeveloperId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Developer with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public List<DevelopersProjectsDto> findByProjectId(int id) {
        final List<DevelopersProjectsDto> collect = repository.findByProjectId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Project with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public int update(DevelopersProjectsDto dtoNew, DevelopersProjectsDto dtoOld) {
        return repository.update(converter.convert(dtoNew), converter.convert(dtoOld));
    }

    public void remove(DevelopersProjectsDto dto) {
        repository.remove(converter.convert(dto));
    }
}

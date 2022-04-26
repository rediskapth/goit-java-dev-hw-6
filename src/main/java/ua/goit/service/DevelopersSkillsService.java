package ua.goit.service;

import ua.goit.model.convert.DevelopersSkillsConverter;
import ua.goit.model.dto.DevelopersSkillsDto;
import ua.goit.repository.DevelopersSkillsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DevelopersSkillsService {
    private final DevelopersSkillsConverter converter;
    private final DevelopersSkillsRepository repository;

    public DevelopersSkillsService(DevelopersSkillsConverter converter, DevelopersSkillsRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(DevelopersSkillsDto dto) {
        repository.save(converter.convert(dto));
    }

    public List<DevelopersSkillsDto> findByDeveloperId(int id) {
        final List<DevelopersSkillsDto> collect = repository.findByDeveloperId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Developer with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public List<DevelopersSkillsDto> findBySkillId(int id) {
        final List<DevelopersSkillsDto> collect = repository.findBySkillId(id).orElseThrow(() -> new IllegalArgumentException
                        (String.format("Skill with id %d not found", id))).stream()
                .map(converter::convert)
                .collect(Collectors.toList());
        return collect;
    }

    public int update(DevelopersSkillsDto dtoNew, DevelopersSkillsDto dtoOld) {
        return repository.update(converter.convert(dtoNew), converter.convert(dtoOld));
    }

    public void remove(DevelopersSkillsDto dto) {
        repository.remove(converter.convert(dto));
    }
}

package ua.goit.model.convert;

import ua.goit.model.dao.DevelopersSkillsDao;
import ua.goit.model.dto.DevelopersSkillsDto;

public class DevelopersSkillsConverter {

    public DevelopersSkillsDto convert(DevelopersSkillsDao dao) {
        DevelopersSkillsDto dto = new DevelopersSkillsDto();
        dto.setDeveloperId(dao.getDeveloperId());
        dto.setSkillId(dao.getSkillId());
        return dto;
    }

    public DevelopersSkillsDao convert(DevelopersSkillsDto dto) {
        DevelopersSkillsDao dao = new DevelopersSkillsDao();
        dao.setDeveloperId(dto.getDeveloperId());
        dao.setSkillId(dto.getSkillId());
        return dao;
    }
}

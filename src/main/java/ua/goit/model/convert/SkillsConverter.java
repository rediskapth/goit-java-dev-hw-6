package ua.goit.model.convert;

import ua.goit.model.dao.SkillsDao;
import ua.goit.model.dto.SkillsDto;

public class SkillsConverter {

    public SkillsDto convert(SkillsDao skillsDao) {
        SkillsDto dto = new SkillsDto();
        dto.setSkillId(skillsDao.getSkillId());
        dto.setLanguage(skillsDao.getLanguage());
        dto.setSkill(skillsDao.getSkill());
        return dto;
    }

    public SkillsDao convert(SkillsDto skillsDto) {
        SkillsDao dao = new SkillsDao();
        dao.setSkillId(skillsDto.getSkillId());
        dao.setLanguage(skillsDto.getLanguage());
        dao.setSkill(skillsDto.getSkill());
        return dao;
    }
}

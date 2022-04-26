package ua.goit.model.convert;

import ua.goit.model.dao.DevelopersProjectsDao;
import ua.goit.model.dto.DevelopersProjectsDto;

public class DevelopersProjectsConverter {

    public DevelopersProjectsDto convert(DevelopersProjectsDao dao) {
        DevelopersProjectsDto dto = new DevelopersProjectsDto();
        dto.setDeveloperId(dao.getDeveloperId());
        dto.setProjectId(dao.getProjectId());
        return dto;
    }

    public DevelopersProjectsDao convert(DevelopersProjectsDto dto) {
        DevelopersProjectsDao dao = new DevelopersProjectsDao();
        dao.setDeveloperId(dto.getDeveloperId());
        dao.setProjectId(dto.getProjectId());
        return dao;
    }
}

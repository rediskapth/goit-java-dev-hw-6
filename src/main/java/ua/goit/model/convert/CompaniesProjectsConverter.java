package ua.goit.model.convert;

import ua.goit.model.dao.CompaniesProjectsDao;
import ua.goit.model.dto.CompaniesProjectsDto;

public class CompaniesProjectsConverter {

    public CompaniesProjectsDto convert(CompaniesProjectsDao dao) {
        CompaniesProjectsDto dto = new CompaniesProjectsDto();
        dto.setCompanyId(dao.getCompanyId());
        dto.setProjectId(dao.getProjectId());
        return dto;
    }

    public CompaniesProjectsDao convert(CompaniesProjectsDto dto) {
        CompaniesProjectsDao dao = new CompaniesProjectsDao();
        dao.setCompanyId(dto.getCompanyId());
        dao.setProjectId(dto.getProjectId());
        return dao;
    }
}

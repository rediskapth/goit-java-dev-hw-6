package ua.goit.model.convert;

import ua.goit.model.dao.CompaniesDao;
import ua.goit.model.dto.CompaniesDto;

public class CompaniesConverter {

    public CompaniesDto convert(CompaniesDao companiesDao) {
        CompaniesDto dto = new CompaniesDto();
        dto.setCompanyId(companiesDao.getCompanyId());
        dto.setName(companiesDao.getName());
        dto.setLocation(companiesDao.getLocation());
        return dto;
    }

    public CompaniesDao convert(CompaniesDto companiesDto) {
        CompaniesDao dao = new CompaniesDao();
        dao.setCompanyId(companiesDto.getCompanyId());
        dao.setName(companiesDto.getName());
        dao.setLocation(companiesDto.getLocation());
        return dao;
    }
}

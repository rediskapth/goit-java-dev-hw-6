package ua.goit.model.convert;

import ua.goit.model.dao.DevelopersDao;
import ua.goit.model.dto.DevelopersDto;

public class DevelopersConverter {

    public DevelopersDto convert(DevelopersDao developersDao) {
        DevelopersDto dto = new DevelopersDto();
        dto.setDeveloperId(developersDao.getDeveloperId());
        dto.setName(developersDao.getName());
        dto.setAge(developersDao.getAge());
        dto.setSalary(developersDao.getSalary());
        return dto;
    }

    public DevelopersDao convert(DevelopersDto developersDto) {
        DevelopersDao dao = new DevelopersDao();
        dao.setDeveloperId(developersDto.getDeveloperId());
        dao.setName(developersDto.getName());
        dao.setAge(developersDto.getAge());
        dao.setSalary(developersDto.getSalary());
        return dao;
    }
}

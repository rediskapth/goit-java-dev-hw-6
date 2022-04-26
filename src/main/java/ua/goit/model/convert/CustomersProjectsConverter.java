package ua.goit.model.convert;

import ua.goit.model.dao.CustomersProjectsDao;
import ua.goit.model.dto.CustomersProjectsDto;

public class CustomersProjectsConverter {

    public CustomersProjectsDto convert(CustomersProjectsDao dao) {
        CustomersProjectsDto dto = new CustomersProjectsDto();
        dto.setCustomerId(dao.getCustomerId());
        dto.setProjectId(dao.getProjectId());
        return dto;
    }

    public CustomersProjectsDao convert(CustomersProjectsDto dto) {
        CustomersProjectsDao dao = new CustomersProjectsDao();
        dao.setCustomerId(dto.getCustomerId());
        dao.setProjectId(dto.getProjectId());
        return dao;
    }
}

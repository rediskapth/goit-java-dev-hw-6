package ua.goit.model.convert;


import ua.goit.model.dao.CustomersDao;
import ua.goit.model.dto.CustomersDto;

public class CustomersConverter {

    public CustomersDto convert(CustomersDao customersDao) {
        CustomersDto dto = new CustomersDto();
        dto.setCustomerId(customersDao.getCustomerId());
        dto.setName(customersDao.getName());
        dto.setLocation(customersDao.getLocation());
        return dto;
    }

    public CustomersDao convert(CustomersDto customersDto) {
        CustomersDao dao = new CustomersDao();
        dao.setCustomerId(customersDto.getCustomerId());
        dao.setName(customersDto.getName());
        dao.setLocation(customersDto.getLocation());
        return dao;
    }
}

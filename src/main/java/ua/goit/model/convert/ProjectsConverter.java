package ua.goit.model.convert;

import ua.goit.model.dao.ProjectsDao;
import ua.goit.model.dto.ProjectsDto;

public class ProjectsConverter {

    public ProjectsDto convert(ProjectsDao projectsDao) {
        ProjectsDto dto = new ProjectsDto();
        dto.setProjectId(projectsDao.getProjectId());
        dto.setName(projectsDao.getName());
        dto.setDescription(projectsDao.getDescription());
        dto.setCreationDate(projectsDao.getCreationDate());
        return dto;
    }

    public ProjectsDao convert(ProjectsDto projectsDto) {
        ProjectsDao dao = new ProjectsDao();
        dao.setProjectId(projectsDto.getProjectId());
        dao.setName(projectsDto.getName());
        dao.setDescription(projectsDto.getDescription());
        dao.setCreationDate(projectsDto.getCreationDate());
        return dao;
    }
}

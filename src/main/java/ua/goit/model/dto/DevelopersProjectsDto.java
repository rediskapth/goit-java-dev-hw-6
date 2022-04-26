package ua.goit.model.dto;

public class DevelopersProjectsDto {
    private Integer developerId;
    private Integer projectId;

    public DevelopersProjectsDto(Integer developerId, Integer projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }

    public DevelopersProjectsDto() {
    }

    public Integer getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Integer developerId) {
        this.developerId = developerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "DevelopersProjectsDto{" +
                "developerId=" + developerId +
                ", projectId=" + projectId +
                '}';
    }
}

package ua.goit.model.dto;

public class CompaniesProjectsDto {
    private Integer companyId;
    private Integer projectId;

    public CompaniesProjectsDto(Integer companyId, Integer projectId) {
        this.companyId = companyId;
        this.projectId = projectId;
    }

    public CompaniesProjectsDto() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "CompaniesProjectsDto{" +
                "companyId=" + companyId +
                ", projectId=" + projectId +
                '}';
    }
}

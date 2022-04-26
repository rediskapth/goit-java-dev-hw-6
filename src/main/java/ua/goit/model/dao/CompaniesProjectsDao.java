package ua.goit.model.dao;

public class CompaniesProjectsDao {
    private Integer companyId;
    private Integer projectId;

    public CompaniesProjectsDao(Integer companyId, Integer projectId) {
        this.companyId = companyId;
        this.projectId = projectId;
    }

    public CompaniesProjectsDao() {
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
        return "CompaniesProjectsDao{" +
                "companyId=" + companyId +
                ", projectId=" + projectId +
                '}';
    }
}

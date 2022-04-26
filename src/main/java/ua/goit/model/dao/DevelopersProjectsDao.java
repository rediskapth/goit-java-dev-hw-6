package ua.goit.model.dao;

public class DevelopersProjectsDao {
    private Integer developerId;
    private Integer projectId;

    public DevelopersProjectsDao(Integer developerId, Integer projectId) {
        this.developerId = developerId;
        this.projectId = projectId;
    }

    public DevelopersProjectsDao() {
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
        return "DevelopersProjectsDao{" +
                "developerId=" + developerId +
                ", projectId=" + projectId +
                '}';
    }
}

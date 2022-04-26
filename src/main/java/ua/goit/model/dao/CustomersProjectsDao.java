package ua.goit.model.dao;

public class CustomersProjectsDao {
    private Integer customerId;
    private Integer projectId;

    public CustomersProjectsDao(Integer customerId, Integer projectId) {
        this.customerId = customerId;
        this.projectId = projectId;
    }

    public CustomersProjectsDao() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "CustomersProjectsDao{" +
                "customerId=" + customerId +
                ", projectId=" + projectId +
                '}';
    }
}

package ua.goit.model.dto;

public class CustomersProjectsDto {
    private Integer customerId;
    private Integer projectId;

    public CustomersProjectsDto(Integer customerId, Integer projectId) {
        this.customerId = customerId;
        this.projectId = projectId;
    }

    public CustomersProjectsDto() {
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
        return "CustomersProjectsDto{" +
                "customerId=" + customerId +
                ", projectId=" + projectId +
                '}';
    }
}

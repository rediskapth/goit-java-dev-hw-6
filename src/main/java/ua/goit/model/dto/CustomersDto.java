package ua.goit.model.dto;

public class CustomersDto {
    private Integer customerId;
    private String name;
    private String location;

    public CustomersDto(Integer customerId, String name, String location) {
        this.customerId = customerId;
        this.name = name;
        this.location = location;
    }

    public CustomersDto() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "CustomersDto{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

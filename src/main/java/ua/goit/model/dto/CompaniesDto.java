package ua.goit.model.dto;

public class CompaniesDto {
    private Integer companyId;
    private String name;
    private String location;

    public CompaniesDto(Integer companyId, String name, String location) {
        this.companyId = companyId;
        this.name = name;
        this.location = location;
    }

    public CompaniesDto() {
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
        return "CompaniesDto{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

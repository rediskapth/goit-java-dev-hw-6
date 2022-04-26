package ua.goit.model.dao;

public class CompaniesDao {
    private Integer companyId;
    private String name;
    private String location;

    public CompaniesDao(Integer companyId, String name, String location) {
        this.companyId = companyId;
        this.name = name;
        this.location = location;
    }

    public CompaniesDao() {
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
        return "CompaniesDao{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}

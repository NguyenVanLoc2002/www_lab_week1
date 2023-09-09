package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models;

public class Role {
    private String id;
    private String name;
    private String discription;
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Role() {
    }

    public Role(String id, String name, String discription, int status) {
        this.id = id;
        this.name = name;
        this.discription = discription;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", discription='" + discription + '\'' +
                ", status=" + status +
                '}';
    }
}

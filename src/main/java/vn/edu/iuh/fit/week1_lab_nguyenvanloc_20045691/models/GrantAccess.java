package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models;

public class GrantAccess {
    private String role_id;
    private String account_id;
    private Is_Grant_Enum is_grant;
    private String note;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Is_Grant_Enum getIs_grant() {
        return is_grant;
    }

    public void setIs_grant(Is_Grant_Enum is_grant) {
        this.is_grant = is_grant;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public GrantAccess() {
    }

    public GrantAccess(String role_id, String account_id, Is_Grant_Enum is_grant, String note) {
        this.role_id = role_id;
        this.account_id = account_id;
        this.is_grant = is_grant;
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "role_id='" + role_id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }
}

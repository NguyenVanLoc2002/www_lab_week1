package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models;

public class GrantAccess {
    private Role role;
    private Account account;
    private Is_Grant_Enum is_grant;
    private String note;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public GrantAccess(Role role, Account account, Is_Grant_Enum is_grant, String note) {
        this.role = role;
        this.account = account;
        this.is_grant = is_grant;
        this.note = note;
    }

    @Override
    public String toString() {
        return "GrantAccess{" +
                "role=" + role +
                ", account=" + account +
                ", is_grant=" + is_grant +
                ", note='" + note + '\'' +
                '}';
    }
}

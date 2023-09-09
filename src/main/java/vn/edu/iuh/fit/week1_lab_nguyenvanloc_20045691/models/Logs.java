package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models;

import java.time.LocalDateTime;

public class Logs {
    private int id;
    private String account_id;
    private LocalDateTime login_time;
    private LocalDateTime logout_time;
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public LocalDateTime getLogin_time() {
        return login_time;
    }

    public void setLogin_time(LocalDateTime login_time) {
        this.login_time = login_time;
    }

    public LocalDateTime getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(LocalDateTime logout_time) {
        this.logout_time = logout_time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Logs() {
    }

    public Logs(int id, String account_id, LocalDateTime login_time, LocalDateTime logout_time, String notes) {
        this.id = id;
        this.account_id = account_id;
        this.login_time = login_time;
        this.logout_time = logout_time;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", account_id='" + account_id + '\'' +
                ", login_time=" + login_time +
                ", logout_time=" + logout_time +
                ", notes='" + notes + '\'' +
                '}';
    }
}

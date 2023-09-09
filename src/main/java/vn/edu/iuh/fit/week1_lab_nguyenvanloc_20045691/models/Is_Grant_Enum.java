package vn.edu.iuh.fit.week1_lab_nguyenvanloc_20045691.models;

public enum Is_Grant_Enum {
    ZERO(0),
    ONE(1);

    private final int value;

    Is_Grant_Enum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

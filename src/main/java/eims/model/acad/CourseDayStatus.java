package eims.model.acad;

public enum CourseDayStatus {
    PENDING("Pending"),
    DONE("Done"),
    CANCEL("Cancel");

    private final String name;

    private CourseDayStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

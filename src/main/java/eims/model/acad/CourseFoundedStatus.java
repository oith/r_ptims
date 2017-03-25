package eims.model.acad;

public enum CourseFoundedStatus {
    NOT_STARTED("Not Started"),
    RUNNING("Running"),
    OTHER("Other"),
    CLOSE("Close");

    private final String name;

    private CourseFoundedStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

package Components;

public class Journal {
    private int grade;
    private int ID;
    private String name;

    public Journal(int grade, int ID, String name, int skips) {
        this.grade = grade;
        this.ID = ID;
        this.name = name;
        this.skips = skips;
    }

    public int getGrad() {
        return grade;
    }

    public void setGrad(int grade) {
        this.grade = grade;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkips() {
        return skips;
    }

    public void setSkips(int skips) {
        this.skips = skips;
    }

    private int skips;
}

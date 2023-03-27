package pl.edu.pwr.ztw.model;

public class Author {
    private int id;
    private String firstName;
    private String latsName;

    public Author(int id, String firstName, String latsName) {
        this.id = id;
        this.firstName = firstName;
        this.latsName = latsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLatsName() {
        return latsName;
    }

    public void setLatsName(String latsName) {
        this.latsName = latsName;
    }
}

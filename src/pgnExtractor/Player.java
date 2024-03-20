package pgnExtractor;

public class Player {

    private String lastName;
    private String firstName;

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Player() {
        this.lastName = "";
        this.firstName = "";
    }

    public Player(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return (firstName == null ? "" : firstName) + " " + lastName;
    }
}

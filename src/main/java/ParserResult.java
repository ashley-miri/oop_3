import java.util.Objects;

public class ParserResult {

    private String city;
    private String street;
    private String house;
    private int floor;

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public int getFloor() {
        return floor;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int hashCode() {
        return Objects.hash(city, street, house, floor);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParserResult)) return false;
        ParserResult other = (ParserResult) o;
        return (
            floor == other.floor &&
            Objects.equals(city, other.city) &&
            Objects.equals(street, other.street) &&
            Objects.equals(house, other.house)
        );
    }
}

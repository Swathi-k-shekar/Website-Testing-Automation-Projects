package miniProject;

public class Hotel {
	private String name;
    private String price;
    private double rating;

    public Hotel(String name, String price, double rating) {
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Ingredient {
    private String name;
    private double amount;
    private String unit;
    private Date expiration;
    private double cost;

    public Ingredient(String name, double amount, String unit, String expiration, double cost) throws ParseException {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.expiration = sdf.parse(expiration);
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public Date getExpirationDate() {
        return expiration;
    }

    public double getCost() {
        return cost;
    }

    public String getUnit() {
        return unit;
    }

    public double getCostPerUnit() {
        return cost / amount;
    }

    public void addAmount(double amount, double cost) {
        this.amount += amount;
        this.cost += cost;
    }

    public void reduceAmount(double amount) {
        this.cost -= getCostPerUnit() * amount;
        this.amount -= amount;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return name + ", " + amount + " " + unit + ", utløpsdato: " + sdf.format(expiration) + ", pris: " + cost + " kr";
    }
}
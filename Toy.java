public class Toy {
    private String name;
    private double cost, sellingPrice;

    public Toy (String defaultName, double startCost, double startSellingPrice){
        name = defaultName;
        cost = startCost;
        sellingPrice = startSellingPrice;
    }
    public Toy () {
        name = "default";
        cost = 0.00;
        sellingPrice = 0.00;
    }
    public String getName () {
        return name;
    }
    public double getCost () {
        return cost;
    }
    public double getSellingPrice () {
        return sellingPrice;
    }
    public double getProfit () {
        return sellingPrice - cost;
    }
    public void changeName (String newName) {
        name = newName;
    }
    public void changeCost (double newCost) {
        cost = newCost;
    }
    public void changeSellingPrice (double newSellingPrice) {
        sellingPrice = newSellingPrice;
    }
}

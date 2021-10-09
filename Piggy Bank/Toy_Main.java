public class Toy_Main {
    public static void main (String args[]) {
        Toy James = new Toy ("Magicalsoup", 4.99, 14.99);

        James.changeName("Depressedsoup");
        James.changeCost(0.99);
        James.changeSellingPrice(99.99);

        System.out.println("By selling James, you will make $" + James.getProfit());
        System.out.println("The cost to make James is $" + James.getCost());
        System.out.println("You can sell James for $" + James.getSellingPrice());
    }
}

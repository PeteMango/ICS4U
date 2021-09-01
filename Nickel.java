public class Nickel extends Coin { /* nickel Class */
    public Nickel () {} /* constructor */
    @Override
    public double getValue () { /* returns the value of the nickel */
        return 0.05;
    } /* get value method */
    @Override
    public String getType () { return "Nickel"; } /* get type method */
}

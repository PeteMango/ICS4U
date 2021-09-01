public class Dime extends Coin { /* dime Class */
    public Dime () {} /* constructor */
    @Override
    public double getValue () { /* returns the value of the dime */
        return 0.1;
    } /* get value method */
    @Override
    public String getType () { return "Dime"; } /* get type method */
}

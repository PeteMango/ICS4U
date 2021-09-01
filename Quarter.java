public class Quarter extends Coin { /* quarter Class */
    public Quarter () {} /* constructor */
    @Override
    public double getValue () { /* returns the value of the quarter */
        return 0.25;
    } /* get value method */
    @Override
    public String getType () { return "Quarter"; } /* get type method */
}

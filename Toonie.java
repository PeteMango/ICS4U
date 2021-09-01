public class Toonie extends Coin{ /* toonie Class */
    public Toonie () {} /* constructor */
    @Override
    public double getValue () { /* returns the value of the toonie */
        return 2.0;
    } /* get value method */
    @Override
    public String getType () { return "Toonie"; } /* get type method */
}

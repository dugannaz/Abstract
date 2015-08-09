
public class Hilbert extends Ring{

	int n;
	int s;
	
	/* Constructor with lower limit 0, higher limit 1000
	 */
	public Hilbert(int n, int s) {
		this.n = n;
		this.s = s;
		limitLow = 0;
	}
	
	/* Constructor with lower and higher limit parameters
	 */
	public Hilbert(int n, int s, int ll, int lh) {
		this.n = n;
		this.s = s;
		limitLow = ll;
		limitHigh = lh;
	}
	
	// definition of set mapping
	public int element(int n1) {

		return n*n1+s;		
	}
	
	// iverse mapping
	public int cardinal(int e1) {
		
		return (e1-s)/n;
	}
	
	// addition
	protected int add(int e1, int e2) {
		int n1 = getCardinal(e1);
		int n2 = getCardinal(e2);
		return getElement(n1+n2);
	}
	
	// inverse of addition
	protected int inverseAdd(int e1) {
		int n1 = getCardinal(e1);
		int en = getCardinal(getIdentity());
		return getElement(en-n1);
	}
	
	// multiplication
	protected int mult(int e1, int e2) {
		return e1*e2;
	}
}

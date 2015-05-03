
public abstract class Ring extends Group {
	
	boolean identityZero;
	boolean multiplicationMonoid;
	boolean distributive;
	boolean ring;
	
	Ring me;
	Monoid multMonoid;
	
	int zero;
	
	public Ring() {
		this.me = this;
		
		multMonoid = new Monoid() { 
			
			public int element(int n1) {
				return me.element(n1);
			}
			
			public int cardinal(int e1) {
				return me.cardinal(e1);
			}
			
			protected int add(int e1, int e2) {
				return mult(e1,e2);
			}
			
		};
	}
	
	abstract int mult(int e1, int e2);
	
	public int multCardinal(int n1, int n2) {
		return getCardinal(mult(getElement(n1), getElement(n2)));
	}
	
	/* Check if multiplication operation defines a Monoid
	 */
	public boolean isMultMonoid() {
		
		boolean result = multMonoid.isMonoid();
		multiplicationMonoid = result;
		return result;
	}
	
public boolean isDistributive() {
		
		boolean result = true;
		
		for (int n1=limitLow/div;n1<limitHigh/div; n1++) {
			int e1 = getElement(n1);
			for (int n2=limitLow/div;n2<limitHigh/div; n2++) {
				int e2 = getElement(n2);
				for (int n3=limitLow/div;n3<limitHigh/div; n3++) {
					int e3 = getElement(n3);
					if (mult(e1,add(e2,e3)) != add(mult(e1,e2),mult(e1,e3)) ) {
						result = false;
						n1 = limitHigh;
						n2 = limitHigh;
						n3 = limitHigh;
					}
				}
			}
		}
		distributive = result;
		return result;
	}
	
	public boolean isRing() {
		
		ring = false;
		
		if (!isGroup()) return false;
		
		if (getIdentity() != 0) {
			System.out.println("Identity is non zero! (" + getIdentity() + ") (Not Ring)");
			identityZero = false;
			return false;
		}
	
		identityZero = true;
		
		if (!isMultMonoid()) {
			System.out.println("Multiplication doesn't define Monoid! (Not Ring)");
			return false;
		}
		
		if (!isDistributive()) {
			System.out.println("Operations are not distributive! (Not Ring)");
			return false;
		}
		
		ring = true;
		return true;
	}
	
	/* Cardinality of nth irreducible
	 */
	public int[] getIrreducibles(int n) {
		
		int en = getCardinal(multMonoid.getIdentity());
		int zeron = getCardinal(zero);
		
		int[] irr = new int[n];
		
		int i=0;
		int in = 0;
		while (i<n) {
			boolean irreducible = true;
			for (int i1=0; i1<in; i1++)
				for (int i2=0; i2<in; i2++) {
					if (multCardinal(i1,i2) == in) {
						irreducible = false;
						i1 = in;
						i2 = in;
					}
						
				}
			if (irreducible)
				if (in != en) 
					if (in != zeron) {
						irr[i] = in;
						i++;
			}
			in++;
		}
		
		return irr;
	}
	
	/* nth irreducible element 
	 */
	public int[] getIrreducibleElements(int n) {
		
		int[] irr = getIrreducibles(n);
		
		for (int i=0; i<n; i++)
			irr[i] = getElement(irr[i]);
		
		return irr;
		
	}
	
	/* find zero element
	 */
	public void findZero() {
		
		for (int i=limitLow;i<limitHigh; i++) {
			int e1 = getElement(i);
			boolean same = true;
			for (int j=limitLow/div;j<limitHigh/div; j++) {
				if (mult(e1,getElement(j)) != e1) {
					same = false;
				}
			}
			if (same) {
				zero = e1;
				return;
			}
		}
	}
	
}

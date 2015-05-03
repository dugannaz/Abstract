
public abstract class Monoid extends Set {
	
	int e;
	boolean closed;
	boolean associative;
	boolean monoid;
	
	abstract protected int add(int e1, int e2); 
	
	public int addCardinal(int n1, int n2) {
		
		return getCardinal(add(getElement(n1), getElement(n2)));
	}
	
	public boolean isClosed() {
		
		boolean result = true;
		
		for (int n1=limitLow/div;n1<limitHigh/div; n1++) {
			int e1 = getElement(n1);
			for (int n2=limitLow/div;n2<limitHigh/div; n2++) {
				int e2 = getElement(n2);
				if (!isElement(add(e1,e2))) {
					result = false;
					n1 = limitHigh;
					n2 = limitHigh;
				}
			}
		}
		
		closed = result;
		return result;
	}
	
	public int getIdentity() {
		
		for (int n1=limitLow/div;n1<limitHigh/div; n1++) {
			int e1 = getElement(n1);
			boolean result = true;
			for (int n2=limitLow/div;n2<limitHigh/div; n2++) {
				int e2 = getElement(n2);
				if (add(e1,e2) != e2 || add(e2,e1) != e2) {
					result = false;
					n2 = limitHigh;
				}
			}
			if (result) {
				e = e1;
				return e;
			}
		}
		
		e = NaN;
		return e;
	}
	
	public boolean isAssociative() {
		
		boolean result = true;
		
		for (int n1=limitLow/div;n1<limitHigh/div; n1++) {
			int e1 = getElement(n1);
			for (int n2=limitLow/div;n2<limitHigh/div; n2++) {
				int e2 = getElement(n2);
				for (int n3=limitLow/div;n3<limitHigh/div; n3++) {
					int e3 = getElement(n3);
					if (add(add(e1,e2),e3) != add(e1,add(e2,e3)) ) {
						result = false;
						n1 = limitHigh;
						n2 = limitHigh;
						n3 = limitHigh;
					}
				}
			}
		}
		
		associative = result;
		return result;
	}
	
	public boolean isMonoid() {
		
		monoid = false;
		
		if (!isClosed()) {
			System.out.println("Not closed under addition! (Not Monoid)");
			return false;
		}
		
		int e = getIdentity();
		if (e==NaN) {
			System.out.println("Addition identity non-zero! (Not Monoid)");
			return false;
		}
		
		if (!isAssociative()) {
			System.out.println("Addition is not associative! (Not Monoid)");
			return false;
		}
		
		monoid = true;
		return true;
	}
}

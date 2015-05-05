
public abstract class Group extends Monoid {

	boolean inverse;
	boolean commutative;
	boolean group;
	
	protected int inverseAdd(int e1) {
		return NaN;
	}
	
	public int getInverse(int e1) {
		return inverseAdd(e1);
	}
	
	public boolean isThereInverse() {
		
		boolean result = true;
		
		for (int n1=limitLow/div;n1<limitHigh/div; n1++) {
			int e1 = getElement(n1);
			if (!isElement(getInverse(e1))) {
				result = false;
				n1 = limitHigh;
			}
		}
		
		inverse = result;
		return result;
	}
	
	public boolean isCommutative() {
		
		boolean result = true;
		
		for (int n1=limitLow/div;n1<limitHigh/div; n1++) {
			int e1 = getElement(n1);
			for (int n2=limitLow/div;n2<limitHigh/div; n2++) {
				int e2 = getElement(n2);
				if (add (e1,e2) != add(e2, e1)) {
					result = false;
					n1 = limitHigh;
					n2 = limitHigh;
				}
			}
		}
		
		commutative = result;
		return result;
	}
	
	public boolean isGroup() {
		
		group = false;
		
		if (!isMonoid()) return false;
		
		isCommutative();
		
		if (!isThereInverse()) {
			System.out.println("Addition has no inverse element! (Not Group)");
			return false;
		}
		
		group = true;
		System.out.println("Group? : Yes (Abellian:" + commutative + ")");
		return true;
	}
}

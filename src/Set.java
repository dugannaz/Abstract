
public abstract class Set {

	int NaN = -100000;
	
	int limitLow = -1000;
	int limitHigh = 1000;
	
	int div=100;
	
	abstract int element(int n1);
	
	public int getElement(int n1) {
		return element(n1);
	}
	
	abstract int cardinal(int e1);
	
	public int getCardinal(int e1) {
		int n1 = cardinal(e1);
		if (getElement(n1) == e1)
			return n1;
		else
			return NaN;
	}
	
	public boolean isElement(int e1) {
		int n1 = getCardinal(e1);

		if (n1>limitLow-1 && n1<limitHigh)
			return true;
		else
			return false;
	}
}

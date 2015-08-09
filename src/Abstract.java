public class Abstract {

	public static void main(String[] args) {
		
		int n = 10;
		int maxN = 100;
		Hilbert[] h = new Hilbert[n];
		int[][] irr = new int[n][]; 
		
		for (int i=0; i<n; i++) {
			h[i] = new Hilbert(i+1, 1);
			h[i].findZero();
			irr[i] = h[i].getIrreducibleElements(maxN);

		}
		
		// check category of Hilbert numbers
		h[3].isRing();
		
		
		// Plot irreducible elements
		MultiPlot irrPlot = MultiPlot.newMultiPlot();
		for (int i=0; i<n; i++) {
			irrPlot.addDataSet(irr[i]);
		}
		
		System.out.println();
		System.out.println("Irreducible elements of h1 (N+) and h4");
		for (int i=0; i<maxN; i++)
			System.out.println(irr[0][i] + " " + irr[3][i]); 
		
		
	}
}

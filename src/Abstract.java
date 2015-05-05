public class Abstract {

	public static void main(String[] args) {
		
		int n = 10;
		int maxN = 100;
		Hilbert[] h = new Hilbert[n];
		int[][] irr = new int[n][]; 
		
		for (int i=0; i<n; i++) {
			h[i] = new Hilbert(i+1, 1,-1000,1000);
			h[i].findZero();
			irr[i] = h[i].getIrreducibleElements(maxN);
			//irr[i] = h[i].getIrreducibles(maxN);
		}
		
		// check category of Hilbert numbers
		h[3].isRing();
		
		
		// Plot irreducible elements
		MultiPlot irrPlot = MultiPlot.newMultiPlot();
		for (int i=0; i<n-1; i++) {
			irrPlot.addDataSet(irr[i]);
		}
		
		System.out.println();
		System.out.println("Irreducible elements of h1 (N+) and h4");
		for (int i=0; i<maxN; i++)
			System.out.println(irr[0][i] + " " + irr[3][i]); 
		
		
		// print differences
		/*if (true) {
		  int x=5;
		  for (int i=0; i<25; i++)
			  System.out.println(i + " " + (irr[i+1][x*(i+2)] - irr[i][x*(i+2)]) + " " + irr[i][x*(i+2)] + " " + irr[i+1][x*(i+2)]); 
		}*/
		
	}
	
	/*static double[] divide(int[] a, int[] b) {
		
		if (a.length != b.length) return null;
		
		double[] c = new double[a.length];
		
		for (int i=0; i<a.length; i++)
			c[i] = (double)a[i]/(double)b[i];
		
		return c;
	}*/

}

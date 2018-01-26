import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class quicksort {

	public static int[] permut(int[] entree) {
		int[] ret = new int[entree.length];
		int i = 0;
		Random randomGenerator = new Random();
		while (entree.length > 1) {
			int randomIndex = randomGenerator.nextInt(entree.length);
			ret[i] = entree[randomIndex];
			entree = ArrayUtils.remove(entree, randomIndex);
			i++;
		}
		ret[i] = entree[0];
		return ret;
	}
	
	public static int[] quicksort(int[] entree) {
		if(entree.length <= 1) return entree;
		
		int pivot = entree[0];
		int[] R = {pivot};
		int p = 0;
		for(int i = 1; i < entree.length; i++) {
			if(entree[i] < pivot) {
				int[] beforeP = Arrays.copyOfRange(R, 0, p > 0 ? p : 0);
				int[] afterP = Arrays.copyOfRange(R, p, R.length > p ? R.length : p);
				beforeP = ArrayUtils.addAll(beforeP, new int[]{entree[i]});
				R = ArrayUtils.addAll(beforeP,afterP);
				p++;
			}
			else {
				R = ArrayUtils.addAll(R, new int[]{entree[i]});
			}
		}	
		return ArrayUtils.addAll(ArrayUtils.addAll(quicksort(Arrays.copyOfRange(R,0,p > 0 ? p : 0)), R[p]), quicksort(Arrays.copyOfRange(R, p+1, R.length > p+1 ? R.length : p+1)));
	}
	
	public static int[] quicksortSansCopie(int[] entree) {
		if(entree.length <= 1) return entree;
		
		int pivot = entree[0];
		int[] R = {pivot};
		int p = 0;
		for(int i = 1; i < entree.length; i++) {
			if(entree[i] < pivot) {
				int[] beforeP = new int[] {};
				int[] afterP = new int[] {};
				int d;
				for(d = 0; d < R.length; d++) {
					if(R[d] != pivot) beforeP = ArrayUtils.add(beforeP, R[d]);
					else break;
				}		
				for(int e = d; e < R.length; e++) {
					afterP = ArrayUtils.add(afterP, R[e]);
				}
				beforeP = ArrayUtils.addAll(beforeP, new int[]{entree[i]});
				R = ArrayUtils.addAll(beforeP,afterP);
				p++;
			}
			else {
				R = ArrayUtils.addAll(R, new int[]{entree[i]});
			}
		}	
		int[] beforeP = new int[] {};
		int[] afterP = new int[] {};
		int d;
		for(d = 0; d < p; d++) {
			beforeP = ArrayUtils.add(beforeP, R[d]);			
		}		
		for(int e = p + 1; e < R.length; e++) {
			afterP = ArrayUtils.add(afterP, R[e]);
		}
		return ArrayUtils.addAll(ArrayUtils.addAll(quicksortSansCopie(beforeP), R[p]), quicksortSansCopie(afterP));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stu
		int[] test = new int[] {1,2,3,4,5};
		int[] ret = permut(test);
		for(int i = 0; i < ret.length;i++) {
			System.out.println(ret[i]);
		}
		
		System.out.println("Resultat avec copie : ");
		long debut2 = System.nanoTime();
		int[] sort2 = quicksort(ret);
		System.out.println("Temps exec : " + (System.nanoTime() - debut2));
		for(int i = 0; i < sort2.length;i++) {
			System.out.println(sort2[i]);
		}
		
		System.out.println("Resultat : ");
		long debut = System.nanoTime();
		int[] sort = quicksortSansCopie(ret);
		System.out.println("Temps exec : " + (System.nanoTime() - debut));
		for(int i = 0; i < sort.length;i++) {
			System.out.println(sort[i]);
		}
		
	}
}

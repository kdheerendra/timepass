import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Dheerendra Kulkarni
 * Compiled with Java 1.7
 * @param <T>
 */
public class IterativeSubsetGenerator<T> {

	public Set<Set<T>> getSubsets(Set<T> set) {
		if(set == null) {
			return null;
		}
		
		int size = set.size();
		long iterCount = 1L << size;
		Set<Set<T>> returnSet = new HashSet<>();
		for (int i = 0; i < iterCount; i++) {
			long mask = 1L;
			Set<T> subset = new HashSet<>();
			for (T element : set) {
				if((mask & i) > 0) {
					subset.add(element);
				}
				mask = mask << 1;
			}
			returnSet.add(subset);
		}
		
		return returnSet;
	}
	
	public void print(Set<Set<T>> subsets) {
		for (Set<T> set : subsets) {
			System.out.print("[");
			for (T element : set) {
				System.out.print(element + ", ");
			}
			System.out.println("]");
		}
	}
	
	public static void main(String[] args) {
		IterativeSubsetGenerator<String> stringSubsetGenerator = new IterativeSubsetGenerator<>();
		Set<String> set1 = new HashSet<>();
		set1.add("One");
		set1.add("Two");
		set1.add("Three");
		Set<Set<String>> subsets1 = stringSubsetGenerator.getSubsets(set1);
		stringSubsetGenerator.print(subsets1);
		
		IterativeSubsetGenerator<Integer> intSubsetGenerator = new IterativeSubsetGenerator<>();
		Set<Integer> set2 = new HashSet<>();
		set2.add(1);
		set2.add(2);
		set2.add(3);
		set2.add(4);
		Set<Set<Integer>> subsets2 = intSubsetGenerator.getSubsets(set2);
		intSubsetGenerator.print(subsets2);
	}
}

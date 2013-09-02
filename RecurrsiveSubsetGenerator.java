import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Dheerendra Kulkarni
 * Compiled with Java 1.7
 * @param <T>
 */
public class RecurrsiveSubsetGenerator<T> {

	public Set<Set<T>> getSubsets(Set<T> set) {
		if(set == null) {
			return null;
		}
		Set<Set<T>> returnSet = null;
		if(set.size() < 2) {
			returnSet = new HashSet<>();
			Set<T> emptySet = new HashSet<>();
			returnSet.add(emptySet);
			if(set.size() == 1) {
				Set<T> singleElementSet = new HashSet<>();
				singleElementSet.addAll(set);
				returnSet.add(singleElementSet);
			}
			return returnSet;
		}
		
		boolean firstElementAdded = false;
		Set<T> leftSet = new HashSet<>();
		Set<T> rightSet = new HashSet<>();
		for (T element : set) {
			if(!firstElementAdded) {
				leftSet.add(element);
				firstElementAdded = true;
				continue;
			}
			rightSet.add(element);
		}
		
		Set<Set<T>> leftSubsets = getSubsets(leftSet);
		Set<Set<T>> rightSubsets = getSubsets(rightSet);
		
		return merge(leftSubsets, rightSubsets);
	}
	
	private Set<Set<T>> merge(Set<Set<T>> leftSubsets, Set<Set<T>> rightSubsets) {
		Set<Set<T>> mergedSet = new HashSet<>();
		for (Set<T> leftSubset : leftSubsets) {
			for (Set<T> rightSubset : rightSubsets) {
				if(leftSubset.size() == 0) {
					mergedSet.add(rightSubset);
				} else {
					Set<T> temp = new HashSet<>();
					temp.addAll(leftSubset);
					temp.addAll(rightSubset);
					mergedSet.add(temp);
				}
			}
		}
		return mergedSet;
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
		RecurrsiveSubsetGenerator<String> stringSubsetGenerator = new RecurrsiveSubsetGenerator<>();
		Set<String> set1 = new HashSet<>();
		set1.add("One");
		set1.add("Two");
		set1.add("Three");
		Set<Set<String>> subsets1 = stringSubsetGenerator.getSubsets(set1);
		stringSubsetGenerator.print(subsets1);
		
		RecurrsiveSubsetGenerator<Integer> intSubsetGenerator = new RecurrsiveSubsetGenerator<>();
		Set<Integer> set2 = new HashSet<>();
		set2.add(1);
		set2.add(2);
		set2.add(3);
		set2.add(4);
		Set<Set<Integer>> subsets2 = intSubsetGenerator.getSubsets(set2);
		intSubsetGenerator.print(subsets2);
	}
}

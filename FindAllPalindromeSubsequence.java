import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllPalindromeSubsequence {
	static class Pair{
		int first;
		int second;
		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}
		
		public String toString() {
			return "(" + this.first + " " + this.second + ")";
		}
		
		public boolean equals(Object o) {
			Pair p = (Pair)o;
			return this.first == p.first && this.second == p.second;
		}
		
		public int hashCode() {
			return this.first * this.second;
		}
	}
	static class Pairs {
		List<Pair> pairs;
		public Pairs(List<Pair> pa) {
			this.pairs = new ArrayList<Pair>();
			for (Pair p : pa) {
				this.pairs.add(new Pair(p.first, p.second));
			}
		}
		public Pairs() {
			pairs = new ArrayList<Pair>();
		}
		public void add(Pair p) {
			pairs.add(p);
			Collections.sort(pairs, new Comparator<Pair>() {
				public int compare(Pair p1, Pair p2) {
					if (p1.first == p2.first) {
						return p1.second - p2.second;
					} else {
						return p1.first - p2.first;
					}
				}
			});
		}
		
		public List<Pair> getPairs() {
			return this.pairs;
		}
		
		public String toString() {
			
			StringBuilder sb = new StringBuilder();
			for (Pair p : pairs) {
				sb.append(p.toString()+"-");
			}
			sb.append("\n");
			return sb.toString();
		}
		@Override  
	    public boolean equals(Object obj) {  
			if (obj == null) return false;
			Pairs pairs = (Pairs)obj;
	        return pairs.toString().equals(this.toString());
	    }  
		@Override
		public int hashCode() {
			return this.pairs.hashCode();
		}
	}
	  
	public void findAllSubsequence(String t) {
		Set<Pairs> set = new HashSet<Pairs>();
		char[] temp = t.toCharArray();
		List<List<Pairs>> palindromeSet = new ArrayList<List<Pairs>>();
		for (int i = 0; i < temp.length; i++) {
			palindromeSet.add(new ArrayList<Pairs>());
		}
		
		for (int i = 0; i < temp.length; i++) {
			for (int j = i+ 1; j < temp.length; j++) {
				if (temp[i] == temp[j]) {
					System.out.println(i + " " + j);
					Pairs pairs = new Pairs();
					pairs.add(new Pair(i,j));
					palindromeSet.get(0).add(pairs);
				}
			}
		}
		for (List<Pairs> a : palindromeSet) {
			for (Pairs p : a) {
				System.out.println(p);
			}
		}
		System.out.println("------");
		for (int i = 1; i < temp.length / 2; i++) {
			
			List<Pairs> pss = palindromeSet.get(i-1);
			for (int j = 0; j < pss.size(); j++) {
				Pairs ps = pss.get(j);
				
				for (int k = 0; k < ps.getPairs().size(); k++) {
					Pair p = ps.getPairs().get(k);
					//List<Pairs> set0 = palindromeSet.get(0);
					for (Pairs u1 : palindromeSet.get(0)) {
						
						if (u1.getPairs().get(0).first < p.first && u1.getPairs().get(0).second > p.second) {
							Pairs newPs = new Pairs(ps.getPairs());
							Pair pp = new Pair(u1.getPairs().get(0).first,u1.getPairs().get(0).second);
							if (!newPs.getPairs().contains(pp)) {
								newPs.add(pp);
							}
							palindromeSet.get(i).add(newPs);
						}
					}
				}
			}
		}
		
		for (List<Pairs> a : palindromeSet) {
			for (Pairs p : a) {
				if (!set.contains(p)) {
					set.add(p);
					System.out.print(p);
				}
			}
			System.out.println();
		}
	}
	
	private int findCountOfsubsequence(String t) {
		return 0;
	}
	
	public static void main(String[] args) {
		FindAllPalindromeSubsequence f = new FindAllPalindromeSubsequence();
		f.findAllSubsequence("ACGATGTAC");

	}

}

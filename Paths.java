import java.util.*;
public class Paths{
	static Map<String,List<String>> roots = new HashMap<String,List<String>>();
	static {
		List<String> bangalore = new ArrayList<String>();
		bangalore.add("Singapore");
		List<String> singapore = new ArrayList<String>();
		singapore.add("Seoul");
		singapore.add("Dubai");
		List<String> seoul = new ArrayList<String>();
		seoul.add("Beijing");
		List<String> beijing = new ArrayList<String>();
		beijing.add("Tokyo");

		roots.put("Bangalore",bangalore);
		roots.put("Singapore",singapore);
		roots.put("Seoul",seoul);
		roots.put("Beijing",beijing);
	}

	public static boolean isPresent(String city){
		Set<String> keys =roots.keySet();
		if(keys.contains(city)){
			return true;
		}
		else{
			for(String src:keys){
				List<String> dests = roots.get(src);
				if(dests.contains(city)){
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isPath(String source,String destination){ 
		return roots.get(source).contains(destination);
	}


	public static void main(String a[]){
		String source = a[0];
		String destination = a[1];
		if(!isPresent(source)){
			System.out.println("No city named \""+source+"\" in database");
			return;
		}
		if(!isPresent(destination)){
			System.out.println("No city named \""+destination+"\" in database");
			return;
		}
		// System.out.println(isPath(source,destination));
		PathFinder pf =new PathFinder(roots);
		Queue<String> root = pf.findPath(source,destination);
		if(root !=null){
			for(String city: root){
				System.out.print(city+"->");
			}
		}
	}
}
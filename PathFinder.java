import java.util.*;

class PathUtil implements ListMapper<String,ArrayDeque<String>>,ListFilter<ArrayDeque<String>>{
	PathFinder pf;
	String destination;
	public PathUtil(PathFinder pf,String destination){
		this.pf =pf;
		this.destination =destination;
	}
	public ArrayDeque<String> map(String source){
		if(pf.visited.contains(source)){
			return null;
		}
		return pf.findPath(source,destination);
	}
	public boolean check(ArrayDeque<String> queue){
		return queue != null;
	}
}

public class PathFinder{
	Map roots;
	List<String> visited;
	public PathFinder(Map roots){
		this.roots = roots;
		visited = new ArrayList();
	}

	public ArrayDeque<String> findPath(String source,String destination){
		List<ArrayDeque<String>>  paths; 
		ArrayDeque<String> firstPath;
		visited.add(source);
		List<String> destinations =(List<String>)roots.get(source);
		if(destinations ==null){
			return null;
		}
		if(destinations.contains(destination)){
			ArrayDeque<String> path = new ArrayDeque<String>();
			path.add(source);
			path.add(destination);
			visited.clear();		
			return  path;
		}
		PathUtil util = new PathUtil(this,destination);  
		paths =CollectionUtils.<String,ArrayDeque<String>>map(destinations,util);
		paths = CollectionUtils.<ArrayDeque<String>>filter(paths,util);
		firstPath = (ArrayDeque<String>)paths.get(0);
		firstPath.addFirst(source);
		return firstPath;
	}

	public void printPath(ArrayDeque<String> root,CountryMapper map){
		String path=root.pollFirst();
		path = path+"["+map.getCountry(path)+"]";
		String node;
		while((node=root.pollFirst())!=null){
			path =path+"->"+node+"["+map.getCountry(node)+"]";
		}
		System.out.println(path);
	}

	public void printReversePath(ArrayDeque<String> root,CountryMapper map){
		String path=root.pollLast();
		path = path+"["+map.getCountry(path)+"]";
		String node;
		while((node=root.pollLast())!=null){
			path =path+"->"+node+"["+map.getCountry(node)+"]";
		}
		System.out.println(path);
	}
}
import java.util.*;

class PathUtil implements ListMapper<String,List<ArrayDeque<String>>>,ListFilter<List<ArrayDeque<String>>>{
	PathFinder pf;
	String destination;
	public PathUtil(PathFinder pf,String destination){
		this.pf =pf;
		this.destination =destination;
	}
	public List<ArrayDeque<String>> map(String source){
		if(pf.visited.contains(source)){
			return null;
		}
		List<ArrayDeque<String>> paths = pf.findPath(source,destination);
		pf.visited.remove(source);
		return paths;
	}
	public boolean check(List<ArrayDeque<String>> queue){
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

	public List<ArrayDeque<String>> findPath(String source,String destination){
		List<List<ArrayDeque<String>>> allPaths;
		List<ArrayDeque<String>> posiblePaths =new ArrayList<ArrayDeque<String>>();
		visited.add(source);
		List<String> destinations =(List<String>)roots.get(source);
		if(destinations ==null){
			return null;
		}
		if(destinations.contains(destination)){
			List<ArrayDeque<String>> paths =new ArrayList<ArrayDeque<String>>();
			ArrayDeque<String>path = new ArrayDeque<String>();
			path.add(source);
			path.add(destination);
			paths.add(path);
			return  paths;
		}
		PathUtil util = new PathUtil(this,destination);  
		allPaths =CollectionUtils.<String,List<ArrayDeque<String>>>map(destinations,util);
		allPaths = CollectionUtils.<List<ArrayDeque<String>>>filter(allPaths,util);
		for (List<ArrayDeque<String>> paths: allPaths){
			posiblePaths.addAll(paths);
		}
		for(ArrayDeque<String> path :posiblePaths){
			path.addFirst(source);
		}
		return posiblePaths;
	}
	public void printPath(ArrayDeque<String> root,CountryMapper map,int index){
		String path=root.pollFirst();
		path = path+"["+map.getCountry(path)+"]";
		String node;
		while((node=root.pollFirst())!=null){
			path =path+"->"+node+"["+map.getCountry(node)+"]";
		}
		System.out.println((++index)+"."+path);
	}

	public void printReversePath(ArrayDeque<String> root,CountryMapper map,int index){
		String path=root.pollLast();
		path = path+"["+map.getCountry(path)+"]";
		String node;
		while((node=root.pollLast())!=null){
			path =path+"->"+node+"["+map.getCountry(node)+"]";
		}
		System.out.println((++index) +"."+path);
	}
}
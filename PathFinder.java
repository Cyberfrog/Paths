import java.util.*;
class PathUtil implements ListMapper<String,Queue<String>>,ListFilter<Queue<String>>{
	PathFinder pf;
	String destination;
	public PathUtil(PathFinder pf,String destination){
		this.pf =pf;
		this.destination =destination;
	}
	public Queue<String> map(String source){
		if(pf.visited.contains(source)){
			return null;
		}
		return pf.findPath(source,destination);
	}
	public boolean check(Queue<String> queue){
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

	public Queue<String> findPath(String source,String destination){
		List<Queue<String>>  paths; 
		ArrayDeque<String> firstPath;
		visited.add(source);
		if(((List<String>)roots.get(source)).contains(destination)){
			Queue<String> path = new ArrayDeque<String>();
			path.add(source);
			path.add(destination);
			visited.clear();
			return  path;
		}
		PathUtil util = new PathUtil(this,destination);  
		paths =CollectionUtils.<String,Queue<String>>map(util);
		paths = CollectionUtils.<Queue<String>>filter(util);
		firstPath = (ArrayDeque<String>)paths.get(0);
		return firstPath.addFirst(source);
	}
}
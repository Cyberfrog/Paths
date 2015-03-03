import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class PathFinderTest{
	@Test
	public void findPath_returns_null_if_pathNotFound(){
		PathFinder pf = new PathFinder(Paths.roots);
		Queue<String> path = pf.findPath("Tokyo","Bangalore");
		assertEquals(path,null);
	}
}
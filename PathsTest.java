import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class PathsTest{
	@Test
	public void isPresent_checks_wheter_given_city_prsent_in_database(){
		assertEquals(Paths.isPresent("Chennai",Paths.defoultRoots),false);
		assertEquals(Paths.isPresent("Dubai",Paths.defoultRoots),true);
		assertEquals(Paths.isPresent("Tokyo",Paths.defoultRoots),true);
	}
	@Test
	public void there_is_path_from_bangalore_to_singhapore(){
		assertEquals(Paths.isPath("Bangalore","Singapore",Paths.defoultRoots),true);
	}
	@Test
	public void there_is_nopath_from_bangalore_to_Tokyo(){
		assertEquals(Paths.isPath("Bangalore","Tokyo",Paths.defoultRoots),false);
	}
}
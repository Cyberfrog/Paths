import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;
public class PathsTest{
	@Test
	public void isPresent_checks_wheter_given_city_prsent_in_database(){
		assertEquals(Paths.isPresent("Chennai"),false);
		assertEquals(Paths.isPresent("Dubai"),true);
		assertEquals(Paths.isPresent("Tokyo"),true);
	}
	@Test
	public void there_is_path_from_bangalore_to_singhapore(){
		assertEquals(Paths.isPath("Bangalore","Singapore"),true);
	}
	@Test
	public void there_is_nopath_from_bangalore_to_Tokyo(){
		assertEquals(Paths.isPath("Bangalore","Tokyo"),false);
	}
}
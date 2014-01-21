import java.awt.Rectangle;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TileTests {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Tile tile = new Tile(0, 0, 5);
		
		Rectangle rleft = new Rectangle();
		Rectangle rright = new Rectangle();
		
		//tile.checkSideCollision(rleft, rright, null, null);
	}

}

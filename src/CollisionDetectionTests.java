import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.awt.Rectangle;

import org.junit.Test;
 
 
public class CollisionDetectionTests {
 
	@Test
	public void rectanglesShouldNotCollide() {		
		Rectangle rectangle1 = new Rectangle(0, 0, 10, 10);
		Rectangle rectangle2 = new Rectangle(11, 11, 10, 10);
		
		assertFalse(areColliding(rectangle1, rectangle2));
	}
	
	@Test
	public void rectanglesColliding() {		
		Rectangle rectangle1 = new Rectangle(0, 0, 10, 10);
		Rectangle rectangle2 = new Rectangle(0, 0, 10, 10);
		
		assertTrue(areColliding(rectangle1, rectangle2));
	}
 
	@Test
	public void rectanglesCollidingOnTop() {		
		Rectangle rectangle1 = new Rectangle(0, 0, 2, 2);
		Rectangle rectangle2 = new Rectangle(0, 2, 2, 2);
		
		assertTrue(recatanglesCollideOnTop(rectangle1, rectangle2));
	}
	
	@Test
	public void rectanglesCollidingOnTop2() {		
		Rectangle rectangle1 = new Rectangle(1, 1, 1, 1);
		Rectangle rectangle2 = new Rectangle(1, 2, 1, 1);
		assertTrue(recatanglesCollideOnTop(rectangle1, rectangle2));
	}
	
	@Test
	public void rectanglesCollideOnTopWhenOnlyBottomRightPointIsTouching() {
		Rectangle rectangle1 = new Rectangle(0, 0, 2, 2);
		Rectangle rectangle2 = new Rectangle(1, 2, 2, 2);
		assertTrue(recatanglesCollideOnTop(rectangle1, rectangle2));
	}
 
	public boolean recatanglesCollideOnTop(Rectangle rectangle1, Rectangle rectangle2) {
		int rightx = rectangle1.x + rectangle1.width;
		Point rightPoint = new Point(rectangle1.x + rectangle1.width, rectangle1.y);
		Point bottomLeftPoint = new Point(rectangle1.x, rectangle1.y + rectangle1.height);
		
		//int bottomy = rectangle1.y + rectangle1.height;
		//int leftx = rectangle2.x;
		//int rightx2 = rectangle2.x + rectangle2.width;
		
		//if(bottomy >= rectangle2.y || bottomy + rectangle1.width <= rightx2)
		//	return true;
		
		return false;
	}
	
	private boolean areColliding(Rectangle rectangle1, Rectangle rectangle2) {
		return rectangle1.intersects(rectangle2);
		//return ! ( rectangle1.y < rectangle2.y || rectangle1.y > rectangle2.y || rectangle1.x < rectangle2.x || rectangle1.x > rectangle2.x );
	}
	
	
}
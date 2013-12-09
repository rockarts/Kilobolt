
public class Projectile {

	private int x, y, speedX;
	private boolean visible;
	
	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
	}
	
	public void update(){ 
		//continually update the x coordinate by adding to it the speed in the x direction
		x += speedX;
		
		//checks if the bullet is off the screen, and makes it invisible. 
		//In the other classes, we will remove these bullets so they do not take up unnecessary memory.
		if (x > 800) {
		   visible = false;
		}
	}
	
	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getSpeedX() {
		return speedX;
	}


	public boolean isVisible() {
		return visible;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}


	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}

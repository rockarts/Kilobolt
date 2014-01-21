import java.awt.Rectangle;

public class Projectile {

	private int x, y, speedX;
	private boolean visible;
	private Rectangle r;

	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;

		r = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		// continually update the x coordinate by adding to it the speed in the
		// x direction
		x += speedX;
		r.setBounds(x, y, 10, 5);

		// checks if the bullet is off the screen, and makes it invisible.
		// In the other classes, we will remove these bullets so they do not
		// take up unnecessary memory.
		if (x > 800) {
			visible = false;
			r = null;
		}
		if (x < 801) {
			checkCollision();
		}
	}

	private void checkCollision() {
		if (r.intersects(StartingClass.bact.r)) {
			visible = false;

			if (StartingClass.bact.health > 0) {
				StartingClass.bact.health -= 1;
			}
			if (StartingClass.bact.health == 0) {
				StartingClass.bact.setCenterX(-100);
				StartingClass.score += 5;

			}
		}

		if (r.intersects(StartingClass.hb2.r)) {
			visible = false;

			if (StartingClass.hb2.health > 0) {
				StartingClass.hb2.health -= 1;
			}
			if (StartingClass.hb2.health == 0) {
				StartingClass.hb2.setCenterX(-100);
				StartingClass.score += 5;

			}
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

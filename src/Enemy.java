import java.awt.Rectangle;

public class Enemy {

	private int power, centerX, speedX, centerY;
	private Background bg = StartingClass.getBg1();
	private PiggtailGirl piggtailGirl = StartingClass.getPiggtailGirl();

	public Rectangle r = new Rectangle(0, 0, 0, 0);
	public int health = 5;

	private int movementSpeed;

	// Behavioral Methods
	public void update() {
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		r.setBounds(centerX - 25, centerY - 25, 50, 60);

		if (r.intersects(PiggtailGirl.rect)) {
			checkCollision();
		}

	}

	private void checkCollision() {
		/*if (r.intersects(PiggtailGirl.rect) || r.intersects(PiggtailGirl.rect2)
				|| r.intersects(PiggtailGirl.rect3) || r.intersects(PiggtailGirl.rect4)) */
		if (r.intersects(PiggtailGirl.rect)){
			System.out.println("collision");
		}
	}

	public void follow() {
		
		if (centerX < -95 || centerX > 810){
			movementSpeed = 0;
		}

		else if (Math.abs(piggtailGirl.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (piggtailGirl.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

	}

	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
}


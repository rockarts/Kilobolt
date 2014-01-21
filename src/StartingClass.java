import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import kiloboltgame.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

	private static PiggtailGirl piggtailGirl;
	private Image image, currentSprite, character, character2, character3,
			characterDown, characterJumped, background, heliboy, heliboy2,
			heliboy3, heliboy4, heliboy5, lifeHeart, walk0, walk1, walk2, walk3,
			walk4, walk5, walk6, walk7;

	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tiledirt;

	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	public static Heliboy hb, hb2;
	private Animation anim, hanim, walkingAnimation;

	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	private ArrayList<Image> lives = new ArrayList<Image>();
	
	private Music music = new Music();

	@Override
	public void init() {

		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("PiggtailGirl");

//		music.startBGMusic();

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		character = getImage(base, "data/piggtailgirlsmall.png");
		character2 = getImage(base, "data/piggtailgirlsmall.png");
		character3 = getImage(base, "data/piggtailgirlsmall.png");
		
		walk0 = getImage(base, "data/walking/frame_000.gif");
		walk1 = getImage(base, "data/walking/frame_001.gif");
		walk2 = getImage(base, "data/walking/frame_002.gif");
		walk3 = getImage(base, "data/walking/frame_003.gif");
		walk4 = getImage(base, "data/walking/frame_004.gif");
		walk5 = getImage(base, "data/walking/frame_005.gif");
		walk6 = getImage(base, "data/walking/frame_006.gif");
		walk7 = getImage(base, "data/walking/frame_007.gif");

		lifeHeart = getImage(base, "data/heart.png");

		// characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/piggtailgirlsmall.png");

		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		heliboy5 = getImage(base, "data/heliboy5.png");

		background = getImage(base, "data/background.png");

		tiledirt = getImage(base, "data/tiledirt.png");
		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");

		lives.add(lifeHeart);
		lives.add(lifeHeart);
		lives.add(lifeHeart);
		lives.add(lifeHeart);
		
		/*walkingAnimation = new Animation();
		walkingAnimation.addFrame(walk0, 100);
		walkingAnimation.addFrame(walk1, 100);
		walkingAnimation.addFrame(walk2, 100);
		walkingAnimation.addFrame(walk3, 100);
		walkingAnimation.addFrame(walk4, 100);
		walkingAnimation.addFrame(walk5, 100);
		walkingAnimation.addFrame(walk6, 100);
		walkingAnimation.addFrame(walk7, 100);
		currentSprite = walkingAnimation.getImage();*/
		
		anim = new Animation();
		anim.addFrame(walk0, 100);
		anim.addFrame(walk1, 100);
		anim.addFrame(walk2, 100);
		anim.addFrame(walk3, 100);
		anim.addFrame(walk4, 100);
		anim.addFrame(walk5, 100);
		anim.addFrame(walk6, 100);
		anim.addFrame(walk7, 100);

		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);

		//currentSprite = anim.getImage();
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);

		piggtailGirl = new PiggtailGirl();

		// Initialize Tiles
		try {
			loadMap("data/map1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hb = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);

		Thread thread = new Thread(this);
		thread.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			// no more lines to read
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				// System.out.println(i + "is i ");

				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}

			}
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		if (state == GameState.Running) {
			while (true) {
				piggtailGirl.update();
				if (piggtailGirl.isJumped()) {
					currentSprite = characterJumped;
				} else if (piggtailGirl.isJumped() == false
						&& piggtailGirl.isDucked() == false) {
					currentSprite = anim.getImage();
				}

				ArrayList projectiles = piggtailGirl.getProjectiles();
				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = (Projectile) projectiles.get(i);
					if (p.isVisible() == true) {
						p.update();
					} else {
						projectiles.remove(i);
					}
				}

				updateTiles();

				hb.update();
				hb2.update();
				bg1.update();
				bg2.update();

				animate();
				repaint();

				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (piggtailGirl.getCenterY() > 500) {
					state = GameState.Dead;
				}

			}
		}
	}

	public void animate() {
		anim.update(10);
		hanim.update(50);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {

		if (state == GameState.Running) {
			// Images are painted in the order they appear. So if you want the
			// character to be above the background, you need to put these two
			// lines
			// above the line that paints the character!
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

			paintTiles(g);

			for(int i = 0; i < lives.size(); i++) {
				//32 is the size of hearts in pixels.
				g.drawImage(lives.get(i), i * 32 + 5, 5, 32, 32, this);
			}
				
			ArrayList projectiles = piggtailGirl.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(Color.GRAY);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}

			// Debug collision detection
			 g.drawRect((int)piggtailGirl.rect.getX(), (int)piggtailGirl.rect.getY(),
			 (int)piggtailGirl.rect.getWidth() - 32, (int)piggtailGirl.rect.getHeight() - 32);
			 //g.drawRect((int)piggtailGirl.rect2.getX(), (int)piggtailGirl.rect2.getY(),
			//(int)piggtailGirl.rect2.getWidth(), (int)piggtailGirl.rect2.getHeight());

			 g.drawImage(currentSprite, piggtailGirl.getCenterX(),
						piggtailGirl.getCenterY(), this);
			 
			 //g.drawImage(currentSprite, piggtailGirl.getCenterX() - 61,
			//			piggtailGirl.getCenterY() - 63, this);
			 
			// Hands
			 //g.drawRect((int)piggtailGirl.rect3.getX(), (int)piggtailGirl.rect3.getY(),
			 //(int)piggtailGirl.rect3.getWidth(), (int)piggtailGirl.rect3.getHeight());
			 //g.drawRect((int)piggtailGirl.rect4.getX(), (int)piggtailGirl.rect4.getY(),
			 //(int)piggtailGirl.rect4.getWidth(), (int)piggtailGirl.rect4.getHeight());

			//g.drawImage(currentSprite, piggtailGirl.getCenterX() - 61,
					//piggtailGirl.getCenterY(), this);
			
			//g.drawImage(hanim.getImage(), hb.getCenterX() - 48,
			//		hb.getCenterY() - 48, this);
			//g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
			//		hb2.getCenterY() - 48, this);

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			if(lives.size() > 0) {
				lives.remove(lives.size() - 1);
			}
				
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
		}
	}

	private void updateTiles() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;

		case KeyEvent.VK_DOWN:
			// currentSprite = characterDown;
			// if (robot.isJumped() == false) {
			// robot.setDucked(true);
			// robot.setSpeedX(0);
			// }
			break;

		case KeyEvent.VK_LEFT:
			piggtailGirl.moveLeft();
			piggtailGirl.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			piggtailGirl.moveRight();
			piggtailGirl.setMovingRight(true);
			break;

		case KeyEvent.VK_SPACE:
			piggtailGirl.jump();
			break;

		case KeyEvent.VK_CONTROL:
			if (piggtailGirl.isDucked() == false && piggtailGirl.isJumped() == false) {
				piggtailGirl.shoot();
				piggtailGirl.setReadyToFire(false);
			}
			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;

		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			piggtailGirl.setDucked(false);
			break;

		case KeyEvent.VK_LEFT:
			piggtailGirl.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			piggtailGirl.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			break;

		case KeyEvent.VK_CONTROL:
			piggtailGirl.setReadyToFire(true);
			break;
		}

	}

	GameState state = GameState.Running;

	enum GameState {
		Running, Dead
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static PiggtailGirl getPiggtailGirl() {
		return piggtailGirl;
	}
}
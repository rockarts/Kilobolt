import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
	
	//tileX represents the x coordinate (horizontal position) of the tile.
	//tileY represents the y coordinate (vertical position) of the tile).
	//speedX is equal to the speed of the tile.
	//type represents the graphical representation of the tile.
	private int tileX, tileY, speedX, type;
	public Image tileImage;
	private Rectangle tileContainer;
	
	private PiggtailGirl piggtailGirl = StartingClass.getPiggtailGirl();
	private Background bg = StartingClass.getBg1();
	
	private final int tileGrassBot = 2;
	private final int tileGrassLeft = 4;
	private final int tileDirt = 5;
	private final int tileGrassRight = 6;
	private final int tileGrassTop = 8;
	
	private int tileSize = 40;
	
	public Tile(int x, int y, int typeInt)	{
		/* Each tile will have the value of 40 pixels. 
		We will be creating Tiles using indexes, rather than pixels. 
		This is why we multiple each index by 40 to get the pixel location.*/
		tileX = x * tileSize;
        tileY = y * tileSize;

        type = typeInt;
        
        tileContainer = new Rectangle();

        if (type == tileDirt) {
            tileImage = StartingClass.tiledirt;
        } else if (type == tileGrassTop) {
            tileImage = StartingClass.tilegrassTop;
        } else if (type == tileGrassLeft) {
            tileImage = StartingClass.tilegrassLeft;

        } else if (type == tileGrassRight) {
            tileImage = StartingClass.tilegrassRight;

        } else if (type == tileGrassBot) {
            tileImage = StartingClass.tilegrassBot;
        }else{
        	type = 0;
        }
	}
	
	public void update() {
        speedX = bg.getSpeedX() * 5;
        tileX += speedX;
        
        tileContainer.setBounds(tileX, tileY, tileSize, tileSize);
        
        if (tileContainer.intersects(PiggtailGirl.yellowRed) && type != 0) {
			checkVerticalCollision(PiggtailGirl.rect, PiggtailGirl.rect2);
			checkSideCollision(PiggtailGirl.rect3, PiggtailGirl.rect4, PiggtailGirl.footleft, PiggtailGirl.footright);
		}
    }
	
	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
        if (rtop.intersects(tileContainer)) { 
        	System.out.println("upper collision");
        	piggtailGirl.setSpeedY(0);
        }
        
        if (rbot.intersects(tileContainer) && type == tileGrassTop) {
        	System.out.print("rbot collision X ");
        	System.out.print(rbot.x);
        	System.out.print(" Y ");
        	System.out.println(rbot.y);
        	
        	System.out.print("rtop collision X ");
        	System.out.print(rtop.x);
        	System.out.print(" Y ");
        	System.out.println(rtop.y);
        	
            piggtailGirl.setJumped(false);
            piggtailGirl.setSpeedY(0);
            piggtailGirl.setCenterY(tileY - 70);
            
        }
    }

    public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot) {

    	if (type != tileDirt && type != tileGrassBot && type != 0){
            if (rleft.intersects(tileContainer)) {
                piggtailGirl.setCenterX(tileX + 102);
                piggtailGirl.setSpeedX(0);
    
            }//else if (leftfoot.intersects(r)) {
            //    piggtailGirl.setCenterX(tileX + 85);
            //    piggtailGirl.setSpeedX(0);
            //}
            
            if (rright.intersects(tileContainer)) {
                piggtailGirl.setCenterX(tileX - 62);
                piggtailGirl.setSpeedX(0);
            }
            
            //else if (rightfoot.intersects(r)) {
            //    piggtailGirl.setCenterX(tileX - 45);
            //    piggtailGirl.setSpeedX(0);
            //}
        }
    }
	
	public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }
}

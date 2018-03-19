package plecs.games.bubbles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Vibrator;

public class Bubble {
	
	private boolean isTouched;
	private boolean isMoving;
	private boolean isGone;
	private boolean isDisplayingScore;
	
	private Context context;
	
	private int scoreTimer;
	double initX, initY;
	private double x, y;
	
	private RectF rect;
	
	private Bitmap bitmap;
	
	private Speed speed;
	
	private Vibrator v;
	
	public Bubble(Context c,int xPos, int yPos, int type) {
		
		context = c;
		initX = xPos;
		initY = yPos;
		x = xPos;
		y = yPos;
		isMoving = false;
		isGone = false;
		
		bitmap = ((BitmapDrawable) context.getResources().
					getDrawable(type)).getBitmap();
		
		int w = (int) (bitmap.getWidth()*.98);
		int h = (int) (bitmap.getHeight()*.98);
				
		bitmap = Bitmap.createScaledBitmap(
				bitmap, w, h, false);
		
		rect = new RectF((float) x - (bitmap.getWidth()/2),
						(float) y - (bitmap.getHeight()/2), 
						(float) x + (bitmap.getWidth()/2), 
						(float) y + (bitmap.getHeight()/2) );
		
		speed = new Speed();
		
		v = (Vibrator) this.context.getSystemService(
							Context.VIBRATOR_SERVICE);		 
	}
	
	public void goTo(double d, double e) {
		
		x = d;
		y = e;
		
		rect.set((float) x - (bitmap.getWidth()/2),
				(float) y - (bitmap.getHeight()/2), 
				(float) x + (bitmap.getWidth()/2), 
				(float) y + (bitmap.getHeight()/2) );
	}
	
	public void draw(Canvas c, Paint p) {
		
		c.drawBitmap(bitmap, (int)x-(bitmap.getWidth()/2), 
						(int)y-(bitmap.getHeight()/2), p);
	}
	
	public void checkIfTouched(int eX, int eY) {
		
		setTouched(rect.contains(eX, eY));		
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public Bitmap getBitmap() {
		return getBitmap();
	}
	
	private RectF getRect() {
		return rect;
	}

	public boolean isTouched() {
		return isTouched;
	}

	public void setTouched(boolean isTouched) {
		this.isTouched = isTouched;
	}
	
	public Speed getSpeed() {
		return speed;
	}
	
	public void setXv(double x) {
		speed.setXv(x);
	}
	
	public double getXv() {
		return speed.getXv();
	}	
	
	public void setXDirection(int d) {
		speed.setXDirection(d);
	}
	
	public int getXDirection() {
		return speed.getXDirection();
	}

	public void setYv(double y) {
		speed.setYv(y);
	}
	
	public double getYv() {
		return speed.getYv();
	}
	
	public void setYDirection(int d) {
		speed.setYDirection(d);
	}
	
	public int getYDirection() {
		return speed.getYDirection();
	}
	
	public void updateDirection(double initX, double postX, 
						double initY, double postY) {		

		if (postX >= initX) {						
			
			setXDirection(Speed.DIRECTION_RIGHT);						
		}
		else {
			
			setXDirection(Speed.DIRECTION_LEFT);
		}
		
		if (postY >= initY) {
			
			setYDirection(Speed.DIRECTION_DOWN);
			
		}
		else {
			
			setYDirection(Speed.DIRECTION_UP);							
		}		
	}
	
	public void update(int w, int h) {
		
		if ((speed.getYDirection() == Speed.DIRECTION_DOWN)) {
			
			if ((rect.bottom >= h-rect.height()/2 ) && 
					(rect.right <= 300) && (rect.left >= 170)) {
					
					isGone= true;
					
					speed.reset();
					
					v.vibrate(400);
			}
			
			else if (rect.bottom >= h-rect.height()/2) {
					speed.toggleYDirection();
			}			
		}		

		if ((speed.getXDirection() == Speed.DIRECTION_RIGHT) &&
			(rect.right >= w)) {					
		        
			speed.toggleXDirection();	
		}
		
		if ((speed.getXDirection() == Speed.DIRECTION_LEFT) &&
				(rect.left <= 0)){
			
			speed.toggleXDirection();
		}		
			
		if ((speed.getYDirection() == Speed.DIRECTION_UP) &&
				(rect.top <= 0)) {					
			        
			speed.toggleYDirection();				
		}
	}

	public void setGone(boolean b) {
		
		isGone = b;		
	}

	public boolean isGone() {
		
		return isGone;
	}
	
public void setMoving(boolean b) {
		
		isMoving = b;		
	}

	public boolean isMoving() {
		
		return isMoving;
	}
}

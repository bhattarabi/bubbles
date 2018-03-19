package plecs.games.bubbles;

import android.content.Context;
import android.graphics.Canvas;

public abstract class MotherBubble {

	float x;
	float y;
	int timer;
	
	Context context;
	Speed speed;
	
	public MotherBubble(Context context, float x, float y) {
		
		this.context = context;
		this.x = x;
		this.y = y;
		
		speed = new Speed();
	}
	
	abstract void goTo(float x, float y);
	
	abstract void draw(Canvas c);
	
}

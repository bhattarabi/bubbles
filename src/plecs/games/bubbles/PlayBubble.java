package plecs.games.bubbles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;

public class PlayBubble extends MotherBubble {
	
	float radius;
	boolean isTouched;
	boolean isOutOfPlay;
	
	RectF rect;
	
	public PlayBubble (Context context, float x, float y,
			float radius) {
		super(context, x, y);
		
		this.radius = radius;
	}

	@Override
	void goTo(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void draw(Canvas c) {
		// TODO Auto-generated method stub
		
	}

}

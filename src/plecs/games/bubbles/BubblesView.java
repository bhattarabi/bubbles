package plecs.games.bubbles;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class BubblesView extends View {
	
	private Paint paint;
	private Paint textPaint;
	private Random rand;
	
	private Handler h;
	
	public Bubble[] bubble = new Bubble[6];
	
	private final int FRAME_RATE = 30;
	
	private long startTime;
	private int score = 0;
	private int elapsed = 0;
	
	RectF rect;
	Intent intent;
	
	public BubblesView(Context c, AttributeSet attrs) {
		super(c, attrs);
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(25);
		paint.setStyle(Style.FILL);	
		
		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(30);
		
		h = new Handler();
		
		intent = new Intent(this.getContext(), GameOver.class);
		
		bubble[0] = new Bubble(c, 60, 25, R.drawable.circle_red);		
		bubble[1] = new Bubble(c, 425, 25, R.drawable.circle_red);
		bubble[2] = new Bubble(c, 300, 25, R.drawable.circle_red);
		bubble[3] = new Bubble(c, 360, 25, R.drawable.circle_red);
		bubble[4] = new Bubble(c, 125, 25, R.drawable.circle_red);
		bubble[5] = new Bubble(c, 190, 25, R.drawable.circle_red);
		
		startTime = System.currentTimeMillis();
		
		rect = new RectF(170, getHeight()-15, 300, getHeight());
	}
	
	private Runnable r = new Runnable() {
		@Override
		public void run() {
			invalidate();
		}		
	};
	
	protected void onDraw(Canvas canvas) {
		
		canvas.drawARGB(140, 50, 100, 200);
		
		int time = (int) (System.currentTimeMillis()-startTime)/1000;
		elapsed = 50 - time;
		
		for (int i = 0; i<bubble.length; i++) {	
			
			if (bubble[i].isGone() == true) {

				double tempX = (int) elapsed*Math.abs(
						bubble[i].getYv());	
				 
				score += tempX; 	
				
				canvas.drawText(" "+tempX, (float) bubble[i].getX(), 
						(float) bubble[i].getY(), paint);
				
				double gotoX = Math.floor((30+Math.random()*400));
				double gotoY = Math.floor((Math.random()*100));
				
				while ((gotoX > 170) && (gotoX < 300)) {
					
					gotoX = Math.floor((60+Math.random()*360));
				}
				
				bubble[i].goTo(gotoX, gotoY);
				
				bubble[i].setGone(false);					
			}
			
			bubble[i].setXv(bubble[i].getXv()*(.999));
			bubble[i].setYv(bubble[i].getYv()*(.999));

			bubble[i].update(canvas.getWidth(), canvas.getHeight());			
			
			double newX = 
					bubble[i].getXDirection()*bubble[i].getXv();
					
			double newY = 
					bubble[i].getYDirection()*bubble[i].getYv();		
			
			bubble[i].goTo(bubble[i].getX()+newX, bubble[i].getY()+newY);
			
			bubble[i].draw(canvas, paint);
			
			//canvas.drawCircle((float) bubble[i].getX()+2, 
				//	(float) bubble[i].getY()+2, (float) 25, paint);
		}		
		//rect = new RectF(170, getHeight()-15, 300, getHeight());
		canvas.drawArc(rect, 45, 10, true, textPaint);
		
		if (elapsed > 0) {

			canvas.drawText("Time: "+elapsed,
								0, 30, textPaint);
		
			canvas.drawText("Score: "+score, 
					canvas.getWidth()-200, 30, textPaint);

			h.postDelayed(r, FRAME_RATE);
		}
		else {		
			
			/*
			intent.putExtra("score", score);
			
			getContext().startActivity(intent);
			*/
			
			textPaint.setTextSize(50);
			canvas.drawText("Game Over!", 100, 70, textPaint);
			canvas.drawText("Score: "+score, 105, 150, textPaint);	
			
			canvas.drawText("Play again",25,240,textPaint);
			canvas.drawText("Exit", 355,240, textPaint);
		}
		
		//50,180,100,250,p
		
	}
}

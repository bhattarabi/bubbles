package plecs.games.bubbles;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class Bubbles extends ActionBarActivity {
	
	private BubblesView bubblesView;
	
	GestureDetector gestureDetector;
	
	VelocityTracker velocity;
	
	float initX, initY;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_bubbles);
        
        bubblesView = (BubblesView) findViewById(R.id.bubblesView);
        
        gestureDetector = new GestureDetector(this, gestureListener);         

		velocity = VelocityTracker.obtain();
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent m) {

    	for (int i = 0; i<bubblesView.bubble.length; i++) {	       		

    		int pointerId = 0;     		
    		
            int action = m.getActionMasked();  	

			int c = 0;
			
			for (int j = 0; j < bubblesView.bubble.length; j++) {	
				
				if ((i != j) && 
						(bubblesView.bubble[j].isTouched())) {
					
					c++ ;						
				}
			}
			
			if (c == 0) {
				bubblesView.bubble[i].
				checkIfTouched((int)m.getX()-20, (int)m.getY()-30); 
			}
			
			switch (action) {
			
				case MotionEvent.ACTION_DOWN:
					
					int pointerIndex = m.getActionIndex();
					pointerId = m.getPointerId(pointerIndex);
					
					initX = m.getX(pointerIndex);
					initY = m.getY(pointerIndex);
					
					break;
							
				case MotionEvent.ACTION_MOVE:					
					
					if ((bubblesView.bubble[i].isTouched () == true) &&
							(c == 0)){
						
						int postIndex = m.findPointerIndex(pointerId);
																
						float postX = m.getX(postIndex);
						float postY = m.getY(postIndex);
						
						bubblesView.bubble[i].updateDirection( 
											initX, postX, initY, postY);
						
						velocity.addMovement(m);
			    		velocity.computeCurrentVelocity(15);
			    		
			    		double x = velocity.getXVelocity();
			    		double y = velocity.getYVelocity();
			    		
						bubblesView.bubble[i].setXv(Math.abs(x));
						bubblesView.bubble[i].setYv(Math.abs(y));
					}
					break;			
						
				case MotionEvent.ACTION_UP:   					
		
		    		if (bubblesView.bubble[i].isTouched() == true) {	    			
						    			
		    			bubblesView.bubble[i].setTouched(false);	    
		    		}
		    		break;		    	
			}
    	}				
    	return true;
    }
    
    private SimpleOnGestureListener gestureListener = 
    		new SimpleOnGestureListener(){    			
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bubbles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bubbles, container, false);
            return rootView;
        }
    }

}

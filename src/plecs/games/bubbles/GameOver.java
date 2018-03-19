package plecs.games.bubbles;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOver extends Activity {
	
	private View screen;
	private TextView scoreView;
	private Button exitButton;
	private Button restartButton;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Bundle fromGame = getIntent().getExtras();
        
        int s = fromGame.getInt("score");
        
        //screen = new TextView(this);
        
        scoreView = new TextView(this);
		scoreView.setTextSize(40);
		scoreView.setText(s);		

		setContentView(scoreView);
	
        /*
	public GameOver(Context c, AttributeSet attrs) {
		super(c, attrs);
		
		scoreView = new TextView(c);
		scoreView.setTextSize(40);
		scoreView.setText("Hello!");*/        
	}

}

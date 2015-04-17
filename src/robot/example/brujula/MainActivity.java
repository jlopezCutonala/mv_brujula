package robot.example.brujula;

import android.support.v7.app.ActionBarActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements SensorEventListener{
	private ImageView image;
	private float currentDegree=0f;
	private SensorManager mSensorManager;
	TextView tvCompass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.image = (ImageView) findViewById(R.id.imageViewCompass);
		this.tvCompass=(TextView) findViewById(R.id.tvCompass);
		this.mSensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		this.mSensorManager.registerListener(this,
					this.mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
					SensorManager.SENSOR_DELAY_GAME
		);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		this.mSensorManager.unregisterListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		float degree = Math.round(arg0.values[0]);
		this.tvCompass.setText("Giro: "+ Float.toString(degree) + " Grados");
		RotateAnimation ra = new RotateAnimation(
				this.currentDegree,
				-degree,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f
		);
		ra.setDuration(210);
		ra.setFillAfter(true);
		image.startAnimation(ra);
		this.currentDegree = -degree;
	}
}

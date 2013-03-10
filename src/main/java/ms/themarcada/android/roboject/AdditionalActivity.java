package ms.themarcada.android.roboject;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import de.akquinet.android.roboject.RobojectActivity;
import de.akquinet.android.roboject.annotations.InjectExtra;
import de.akquinet.android.roboject.annotations.InjectLayout;
import de.akquinet.android.roboject.annotations.InjectResource;
import de.akquinet.android.roboject.annotations.InjectView;

@InjectLayout("activity_another")
public class AdditionalActivity extends RobojectActivity {

	private static final String TAG = "AdditionalActivity";
	
	@InjectView
	private TextView textFromIntent;
	
	@InjectView
	private TextView textFromResources;
	
	// Here we're getting the value of a string passed in as a param
	// to the intent.
	@InjectExtra(HelloAndroidActivity.INTENT_TEXT)
	private String intentText;

	@InjectResource(name="random_string")
	private String randomString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		
		textFromIntent.setText(intentText);
		textFromResources.setText(randomString);
	}
	
	
}

package ms.themarcada.android.roboject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;
import de.akquinet.android.roboject.RobojectActivity;
import de.akquinet.android.roboject.annotations.InjectLayout;
import de.akquinet.android.roboject.annotations.InjectResource;
import de.akquinet.android.roboject.annotations.InjectView;

// Demonstrates injecting the layout instead of calling setContentView()
// Not a substantial improvement over the standard, but it does make the
// onCreate a little cleaner to look at.
//
// Additionally, Roboject requires us to extend RobojectActivity to make
// injection possible. This isn't the most optimal scenario, but I think
// we can probably deal with the repercussions.
@InjectLayout("main")
public class HelloAndroidActivity extends RobojectActivity {

    public static final String INTENT_TEXT = "intentText";

	private static String TAG = "roboject";

	// Demonstrates injecting a view object
	// rather than using findContentById().
	// This specific instance assumes the ID
	// of the view object matches the variable
	// name specified here. Kind of brittle...
    @InjectView
    private TextView helloText;
    
    // Demonstrates injecting a view object
    // like above, but here we're specifying
    // the layout ID as specified in the layout
    // xml file. Less brittle, but would allow
    // a class variable to have a name that
    // doesn't reflect the name in the view.
    @InjectView("activateToggleButton")
    private Button activateToggleButton;
    
    @InjectView
    private Button nextButton;
    
    @InjectView
    private ToggleButton toggleButton;
    
    // Demonstrates the injection of a variable
    // from the strings.xml file. This can also
    // apply to any resource, be it integer, string etc...
    @InjectResource(name="text_from_intent_action")
    private String intentTextFromResource;
    
    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		
		// The onCreate (and any other class method) is now much cleaner and more
		// straight forward to read since we don't have to retrieve resources
		// or call findViewById() for each view element we want to interact with.
		activateToggleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Activate toggle button clicked. Changing the state of the toggle button");
				toggleButton.toggle();
			}
		});
		
		toggleButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				Log.i(TAG, "Toggle state changed!");
				helloText.setText("Toggle active? " + isChecked);
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Log.i(TAG, "Next button clicked!");
				openNextActivity();
			}
		});
    }
   
    private void openNextActivity() {
    	Log.i(TAG, "openNextActivity");
    	
    	// Here we're going to demonstrate the ability to set content
    	// in the intent call and retrieve the values by injection in
    	// the next activity.
    	Intent intent = new Intent(this, AdditionalActivity.class);
    	intent.putExtra(INTENT_TEXT, intentTextFromResource);
    	startActivity(intent);
    }
}


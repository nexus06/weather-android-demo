package com.puliware.watherminiproject.activities;

import android.os.Bundle;
import com.puliware.watherminiproject.R.layout;
import com.puliware.watherminiproject.operations.WeatherOps;
import com.puliware.watherminiproject.utils.RetainedFragmentManager;

;

/**
 * Help/about Activity
 */
public class HelpActivity extends LifecycleLoggingActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.help);
	}

}

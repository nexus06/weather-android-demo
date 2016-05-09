package com.puliware.watherminiproject.activities;

import android.os.Bundle;
import com.puliware.watherminiproject.R.layout;
import com.puliware.watherminiproject.operations.WeatherOps;
import com.puliware.watherminiproject.utils.RetainedFragmentManager;

;

/**
 * The main Activity that prompts the user for Weather to expand via various
 * implementations of WeatherServiceSync and WeatherServiceAsync and view via
 * the results. Extends LifecycleLoggingActivity so its lifecycle hook methods
 * are logged automatically.
 */
public class HelpActivity extends LifecycleLoggingActivity {

	/**
	 * Used to retain the state between runtime configuration changes. Handles
	 * rotations
	 */
	protected final RetainedFragmentManager mRetainedFragmentManager = new RetainedFragmentManager(
			this.getFragmentManager(), TAG);

	/**
	 * Provides weather-related operations.
	 */
	private WeatherOps mWeatherOps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.help);
	}

}

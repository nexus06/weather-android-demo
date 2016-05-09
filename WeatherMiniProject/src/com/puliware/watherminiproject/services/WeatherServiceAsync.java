package com.puliware.watherminiproject.services;

import java.util.ArrayList;
import java.util.List;

import com.puliware.watherminiproject.aidl.WeatherData;
import com.puliware.watherminiproject.aidl.WeatherRequest;
import com.puliware.watherminiproject.aidl.WeatherResults;
import com.puliware.watherminiproject.utils.Utils;

import android.content.Context;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * @class WeatherServiceAsync
 * 
 * @brief This class uses asynchronous AIDL interactions to expand Weathers via
 *        an Weather Web service. The WeatherActivity that binds to this Service
 *        will receive an IBinder that's an instance of WeatherRequest, which
 *        extends IBinder. The Activity can then interact with this Service by
 *        making one-way method calls on the WeatherRequest object asking this
 *        Service to lookup the Weather's meaning, passing in an WeatherResults
 *        object and the Weather string. After the lookup is finished, this
 *        Service sends the Weather results back to the Activity by calling
 *        sendResults() on the WeatherResults object.
 * 
 *        AIDL is an example of the Broker Pattern, in which all interprocess
 *        communication details are hidden behind the AIDL interfaces.
 */
public class WeatherServiceAsync extends LifecycleLoggingService {
	/**
	 * Factory method that makes an Intent used to start the WeatherServiceAsync
	 * when passed to bindService().
	 * 
	 * @param context
	 *            The context of the calling component.
	 */
	public static Intent makeIntent(Context context) {
		return new Intent(context, WeatherServiceAsync.class);
	}

	/**
	 * Called when a client (e.g., WeatherActivity) calls bindService() with the
	 * proper Intent. Returns the implementation of WeatherRequest, which is
	 * implicitly cast as an IBinder.
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return mWeatherRequestImpl;
	}

	/**
	 * The concrete implementation of the AIDL Interface WeatherRequest, which
	 * extends the Stub class that implements WeatherRequest, thereby allowing
	 * Android to handle calls across process boundaries. This method runs in a
	 * separate Thread as part of the Android Binder framework.
	 * 
	 * This implementation plays the role of Invoker in the Broker Pattern.
	 */
	private final WeatherRequest.Stub mWeatherRequestImpl = new WeatherRequest.Stub() {
		/**
		 * Implement the AIDL WeatherRequest getCurrentWeather() method, which
		 * forwards to DownloadUtils getResults() to obtain the results from the
		 * Weather Web service and then sends the results back to the Activity
		 * via a callback.
		 */
		@Override
		public void getCurrentWeather(String weather, WeatherResults callBack)
				throws RemoteException {
			List<WeatherData> weatherResult = Utils.getResults(weather);
			if (weatherResult != null && weatherResult.size() > 0) {
				callBack.sendResults(weatherResult);
			} else {
				callBack.sendError("Sorry, city not found");
			}

		}
	};
}

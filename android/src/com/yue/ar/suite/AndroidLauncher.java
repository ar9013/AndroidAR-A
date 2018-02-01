package com.yue.ar.suite;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.os.Bundle;

import android.util.Log;
import android.view.SurfaceView;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {

	private int origWidth;
	private int origHeight;
	private String TAG = "AndroidLauncher";


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 設定手機螢幕垂直顯示
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Intent it2ARFileService = new Intent(this,ARFileService.class);
		startService(it2ARFileService);

		// 設定 Android app
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.r = 8 ;
		config.g = 8 ;
		config.b = 8 ;
		config.a = 8;
		config.hideStatusBar = true;

		CameraControl cameraControl = new AndroidCameraController(this);

		initialize(new ARSuite(cameraControl), config);

		if(graphics.getView() instanceof SurfaceView)
		{
			SurfaceView glView = (SurfaceView) graphics.getView();

			glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		}

		// 設定 螢幕不會關閉
		graphics.getView().setKeepScreenOn(true);

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG,"onResume");

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.r = 8;
		config.g = 8;
		config.b = 8;
		config.a = 8;

		CameraControl cameraControl = new AndroidCameraController(this);

		initialize(new ARSuite(cameraControl),config);

		if(graphics.getView() instanceof SurfaceView){
			SurfaceView glView = (SurfaceView) graphics.getView();

			glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
		}

		graphics.getView().setKeepScreenOn(true);

		origHeight = graphics.getHeight();
		origWidth = graphics.getWidth();

	}


	public void restoreFixedSize() {
		if (graphics.getView() instanceof SurfaceView) {
			SurfaceView glView = (SurfaceView) graphics.getView();
			glView.getHolder().setFormat(PixelFormat.TRANSLUCENT);
			glView.getHolder().setFixedSize(origWidth, origHeight);
		}
	}

	public void post(Runnable r) {
		handler.post(r);
	}
}

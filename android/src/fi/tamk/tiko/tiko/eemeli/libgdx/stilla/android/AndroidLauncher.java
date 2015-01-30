package fi.tamk.tiko.tiko.eemeli.libgdx.stilla.android;

import android.os.Bundle;
import android.view.KeyEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import fi.tamk.tiko.eemeli.libgdx.stilla.StillaGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new StillaGame(), config);
	}
	
	@Override 
	public boolean onKeyDown (int keyCode, KeyEvent event) {
		
		Gdx.app.log("Android launcher", "Key Down");
		
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Gdx.app.log("Android launcher", "Application going to be finished");
			finish();
			onDestroy();
			Gdx.app.log("Android launcher", "Application finished");
		}
		
		return super.onKeyDown(keyCode, event); 
	}
	
}

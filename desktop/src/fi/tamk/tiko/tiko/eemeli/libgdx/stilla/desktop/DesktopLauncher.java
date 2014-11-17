package fi.tamk.tiko.tiko.eemeli.libgdx.stilla.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import fi.tamk.tiko.tiko.eemeli.libgdx.stilla.StillaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new StillaGame(), config);
	}
}

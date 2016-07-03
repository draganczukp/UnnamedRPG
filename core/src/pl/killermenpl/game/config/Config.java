package pl.killermenpl.game.config;

import java.io.File;
import java.io.IOException;

import org.ini4j.Ini;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Input.Keys;

public class Config {

	protected static final class IniProperty {
		private String prop;

		IniProperty(String s) {
			prop = s;
		}

		public int asInt() {
			return Integer.parseInt(prop);
		}

		public float asFloat() {
			return Float.parseFloat(prop);
		}

		public char asChar() {
			return prop.charAt(0);
		}

		public boolean asBool() {
			return Boolean.parseBoolean(prop);
		}
	}

	protected static Ini ini;

	// some default values

	// [Display
	public static int screenHeight = 540;
	public static int screenWidth = 950;

	public static boolean fullscreen = false;

	// [Audio]
	public static int musicVolume = 100;
	public static int soundVolume = 100;

	// [Controlls]
	public static int keyUp = Keys.W;
	public static int keyDown = Keys.S;
	public static int keyLeft = Keys.A;
	public static int keyRight = Keys.D;
	public static int keyInterract = Keys.E;
	public static int keyInventory = Keys.I;

	// public static int keyZoom = Input.Buttons.BACK;

	private static File configFile = new File(Gdx.files.getLocalStoragePath() + "config.ini");


	public static boolean debug = false;


	public static void init() throws Exception {
		if (!configFile.exists()) {
			// configFile.mkdirs();
			configFile.createNewFile();
		}
		ini = new Ini(configFile);

		if (ini.isEmpty())
			addDefaults(ini);
		else
			readIni(ini);
		// System.out.println(ini.get("Display", "width"));
	}

	private static void readIni(Ini ini) {

		screenHeight = getProperty(ini, "Display", "screen.height").asInt();
		screenWidth = getProperty(ini, "Display", "screen.width").asInt();
		fullscreen = getProperty(ini, "Display", "screen.fullscreen").asBool();

		musicVolume = getProperty(ini, "Audio", "music.volume").asInt();
		soundVolume = getProperty(ini, "Audio", "sound.volume").asInt();

		keyUp = getProperty(ini, "Controlls", "key.up").asInt();
		keyDown = getProperty(ini, "Controlls", "key.down").asInt();
		keyLeft = getProperty(ini, "Controlls", "key.left").asInt();
		keyRight = getProperty(ini, "Controlls", "key.right").asInt();
		keyInterract = getProperty(ini, "Controlls", "key.interract").asInt();
		keyInventory = getProperty(ini, "Controlls", "key.inventory").asInt();

		debug = getProperty(ini, "Debug", "debug").asBool();
		if(Gdx.graphics.supportsDisplayModeChange()){
			DisplayMode mode = Gdx.graphics.getDisplayMode();
			if(fullscreen){
				Gdx.graphics.setFullscreenMode(mode);
			}else{
				Gdx.graphics.setWindowedMode(screenWidth, screenHeight);
			}
		}

	}

	private static IniProperty getProperty(Ini ini, String section, String name) {
		return new IniProperty(ini.fetch(section, name));
	}

	private static void addDefaults(Ini ini) throws IOException {
		ini.clear();
		// Initialize all the configs
		// [Display]
		ini.add("Display", "screen.width", screenWidth);
		ini.add("Display", "screen.height", screenHeight);
		ini.add("Display", "screen.fullscreen", fullscreen);

		// [Audio]
		ini.add("Audio", "music.volume", musicVolume);
		ini.add("Audio", "sound.volume", soundVolume);

		// [Controlls]
		ini.add("Controlls", "key.up", keyUp);
		ini.add("Controlls", "key.down", keyDown);
		ini.add("Controlls", "key.left", keyLeft);
		ini.add("Controlls", "key.right", keyRight);
		ini.add("Controlls", "key.interract", keyInterract);
		ini.add("Controlls", "key.inventory", keyInventory);

		ini.add("Debug", "debug", debug);
		
		ini.store();

	}

	public static void reload() {
		try {
			addDefaults(ini);
			readIni(ini);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
package pl.killermenpl.game.util;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader.FreeTypeFontGeneratorParameters;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

import pl.killermenpl.game.log.Log;
import pl.killermenpl.game.log.LogLevel;

public class FontUtil {
	public static HashMap<Integer, BitmapFont> borderlessFonts = new HashMap<>();
	public static HashMap<Integer, BitmapFont> borderedFonts = new HashMap<>();

	protected static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
			Gdx.files.internal("font/Comfortaa Regular.ttf"));
	protected static FreeTypeFontParameter params = new FreeTypeFontParameter();

	public static void init(){
		FreeTypeFontGenerator.setMaxTextureSize(FreeTypeFontGenerator.NO_MAXIMUM);
		params.borderColor = Color.BLACK;
		params.characters = "A•BC∆DE FGHIJKL£MN—O”PQRSåTUVWXYZèØaπbcÊdeÍfghijkl≥mnÒoÛpqrsútuvwxyzüø1234567890\"!`?'.,;:()[]{}<>|/@\\^$-%+=#_&~*";
	}
	
	public static BitmapFont generateFont(Color color, int size){
		params.color = color;
		params.size = size;
		return generator.generateFont(params);
	}
	
	public static void addFonts() {
		for (int i = 10; i <= 72; i++) {
			Log.log(LogLevel.DEBUG, "Generating font with size " + i);
			params.size = i;
			params.borderWidth = 1;

			borderedFonts.put(i, generator.generateFont(params));
			params.borderWidth = 0;
			borderlessFonts.put(i, generator.generateFont(params));

		}
		generator.dispose();
	}

}

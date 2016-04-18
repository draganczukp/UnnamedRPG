package pl.killermenpl.game.assets;

import pl.killermenpl.game.assets.Asset.AssetType;

public class Assets {
	private static final AssetDescriptor MAIN_BACK = new AssetDescriptor(AssetType.SPRITE, "MAIN_BACK",AssetCategory.MAIN ,
			"img/mainBack.png");
	private static final AssetDescriptor BUTTON_DEFAUL = new AssetDescriptor(AssetType.SPRITE, "BUTTON_DEFAUL", AssetCategory.GUI,
			"img/buttonDefault.png");
	private static final AssetDescriptor TEMP_STATIC_OBJECT = new AssetDescriptor(AssetType.SPRITE, "TEMP_STATIC_OBJECT", AssetCategory.TEMP,
			"img/tempObjectx32.png");
	private static final AssetDescriptor BACKGROUND_DEFAULT = new AssetDescriptor(AssetType.SPRITE,	"BACKGROUND_DEFAULT", AssetCategory.MAIN,
			"img/mainBack.png");
	private static final AssetDescriptor PLAYER_SPRITE_SHEET = new AssetDescriptor(AssetType.SPRITE, "PLAYER_SPRITE_SHEET", AssetCategory.MAIN,
			"img/template_c.png");
	private static final AssetDescriptor TEMP_TILED_MAP = new AssetDescriptor(AssetType.MAP, "TEMP_TILED_MAP", AssetCategory.TEMP,
			"maps/testIsland.tmx");
	private static final AssetDescriptor TEMP_NPC1 = new AssetDescriptor(AssetType.SPRITE, "testNpc", AssetCategory.TEMP,
			"img/template_entity_test.png");
	private static final AssetDescriptor COLLISION = new AssetDescriptor(AssetType.SPRITE, "collision", AssetCategory.MAIN,
			"img/collision.png");
	private static final AssetDescriptor HPBAR = new AssetDescriptor(AssetType.SPRITE, "hpBar", AssetCategory.GUI,
			"img/redBar.9.png");

	public static final void addAssets() {
		AssetManager.addToLoad(MAIN_BACK);
		AssetManager.addToLoad(BUTTON_DEFAUL);
		AssetManager.addToLoad(TEMP_STATIC_OBJECT);
		AssetManager.addToLoad(BACKGROUND_DEFAULT);
		AssetManager.addToLoad(PLAYER_SPRITE_SHEET);
		AssetManager.addToLoad(TEMP_TILED_MAP);
		AssetManager.addToLoad(TEMP_NPC1);
		AssetManager.addToLoad(COLLISION);
		AssetManager.addToLoad(HPBAR);
	}
}

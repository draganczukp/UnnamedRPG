package pl.killermenpl.game.gui;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import pl.killermenpl.game.objects.NPC;

public class DialogWindowManager extends GuiManager {

	protected Array<NPC> npcArray = new Array<>();

	public void addNpc(NPC npc) {
		npcArray.add(npc);
	}

	@Override
	public void initAll() {
		for (NPC npc : npcArray) {
			HashMap<String, DialogWindow> npcDialog = npc.dialogs();
			Set<String> keys = npcDialog.keySet();
			for (String s : keys) {
				this.addElement(npcDialog.get(s));
			}
		}
		super.initAll();
	}

	@Override
	public void render(SpriteBatch batch, float dt) {
		super.render(batch, dt);

	}

}

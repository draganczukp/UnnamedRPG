package pl.killermenpl.game.objects;

import com.badlogic.gdx.math.Vector2;

public class GameObjectData {

	public static enum GameObjectType{
		STATIC, LIVING, BASE;
		
		public String getType(){
			return this.name();
		}
	}

	public String name;

//	public float x, y;
	public Vector2 pos;
	public GameObjectType type;

//	public JSONObject toJSONObject() {
//
//		JSONObject object = new JSONObject();
//		object.put("name", name);
//		object.put("pos", new JSONObject().put("x", pos.x).put("y", pos.y));
//		object.put("type", type);
//
//		return object;
//	}
//
//	public static GameObjectData fromJson(String json) {
//		return fromJson(new JSONObject(json));
//	}
//
//	public static GameObjectData fromJson(JSONObject jsonObject) {
//		GameObjectData data = new GameObjectData();
//		data.name = (String) jsonObject.get("name");
//
//		JSONObject pos = jsonObject.getJSONObject("pos");
//		data.pos = new Vector2((float) pos.getDouble("x"), (float) pos.getDouble("y"));
//
//		data.type = (GameObjectType) jsonObject.get("type");
//		
//		return data;
//	}
//
//	
	public GameObject toGameObject(){
		GameObject o = null;
		
		switch(type){
		case LIVING:
			o = new LivingObject(name, pos);
			break;
		case STATIC:
			o = new StaticObject(name, pos);
			break;
		case BASE:
			//Shouldn't happen, but just in case
			o = new GameObject(name, pos) {};
			break;
		default:
			break;
		}
		return o;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public GameObjectType getType() {
		return type;
	}

	public void setType(GameObjectType type) {
		this.type = type;
	}

//	public float getX() {
//		return x;
//	}
//
//	public void setX(float x) {
//		this.x = x;
//	}
//
//	public float getY() {
//		return y;
//	}
//
//	public void setY(float y) {
//		this.y = y;
//	}

	public void setPos(Vector2 pos) {
		this.pos=  pos;
	}
	
	public Vector2 getPos() {
		return pos;
	}

	

}

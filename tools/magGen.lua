local args = {...};

local mapFile;
local npcTable = {};

function NPC(image, facing, posX, posY)
	return {
		["image"] = image,
		["position"] = {
			["dir"] = facing,
			["x"]=posX,
			["y"]=posY
		}
};
end

function run()
	print("Map Generator v0.1");
	local image, dir, posX, posY;
	
	io.write("Map file name: ")
	mapFile=io.read();
	while io.read() ~= "*end" do
		io.write("\nNPC image file name: ");
		image = io.read();
		io.write("\nDirection (up, down, left, right): ");
		dir = io.read();
		io.write("\nPosX and PosY");
		posX=io.read();
		posY=io.read();
		
		npcTable[#npcTable+1]=NPC(image, dir, posX, posY);
	end
	
	saveToFile();
end


run();
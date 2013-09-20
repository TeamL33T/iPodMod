package mods.TeamL33T.IpodMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="ipod")
public class Main {
	
	public static Item playerIpod = new Ipod(500);
	
	@EventHandler
	public void init(FMLInitializationEvent ev) {
		GameRegistry.registerItem(playerIpod, "iPod");
		LanguageRegistry.addName(playerIpod, "iPod");
		GameRegistry.addRecipe(new ItemStack(500,1,1), new Object[] {
			"sfs", "fcf", "rfr", 
			's', new ItemStack(501,1,1), 
			'f', new ItemStack(502,1,1),
			'c', new ItemStack(503,1,1),
			'r', new ItemStack(Block.redstoneWire)
		});
	}
	
}

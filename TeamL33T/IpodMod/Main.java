package iPodMod.TeamL33T.IpodMod;

import iPodMod.TeamL33T.IpodMod.battery.BatteryBasic;
import iPodMod.TeamL33T.IpodMod.battery.BatteryGalactic;
import iPodMod.TeamL33T.IpodMod.battery.BatteryMega;
import iPodMod.TeamL33T.IpodMod.battery.BatteryPro;
import iPodMod.TeamL33T.IpodMod.battery.BatteryUltra;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "iPodMod", name = "iPodMod", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Main {
	
	public static CreativeTabs tabIpod = new CreativeTabs("tabIpod") {

		public ItemStack getIconItemStack(){
			return new ItemStack(Ipod);
		}
	};
	
	public static Item Ipod = new Ipod(500);
	public static Item batteryBasic = new BatteryBasic();
	public static Item batteryPro = new BatteryPro();
	public static Item batteryMega = new BatteryMega();
	public static Item batteryUltra = new BatteryUltra();
	public static Item batteryGalactic = new BatteryGalactic();
	public static Block IpodCharger = new BlockIpodCharger(506, false);
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		// Game Registries
		GameRegistry.registerItem(Ipod, "iPod");
		GameRegistry.registerItem(batteryBasic, "Basic iPod Battery");
		GameRegistry.registerItem(batteryPro, "Pro iPod Battery");
		GameRegistry.registerItem(batteryMega, "Mega iPod Battery");
		GameRegistry.registerItem(batteryUltra, "Ultra iPod Battery");
		GameRegistry.registerItem(batteryGalactic, "Galactic iPod Battery");
		GameRegistry.registerBlock(IpodCharger, "IpodCharger");
		
		// Language Registries
		LanguageRegistry.addName(Ipod, "iPod");
		LanguageRegistry.addName(batteryBasic, "Basic iPod Battery");
		LanguageRegistry.addName(batteryPro, "Pro iPod Battery");
		LanguageRegistry.addName(batteryMega, "Mega iPod Battery");
		LanguageRegistry.addName(batteryUltra, "Ultra iPod Battery");
		LanguageRegistry.addName(batteryGalactic, "Galactic iPod Battery");
		LanguageRegistry.addName(IpodCharger, "iPod Charger");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabIpod", "en_US", "iPod Mod");
		
		// Minecraft Forge Registries
		MinecraftForge.setBlockHarvestLevel(IpodCharger, "pickaxe", 1);
		
		// Crafting Recipes
		GameRegistry.addRecipe(new ItemStack(Ipod), new Object[] {
			"sfs", "fcf", "rfr", 
			's', new ItemStack(501,1,1), 
			'f', new ItemStack(502,1,1),
			'c', new ItemStack(503,1,1),
			'r', new ItemStack(Block.redstoneWire)
		});
	}
	
}

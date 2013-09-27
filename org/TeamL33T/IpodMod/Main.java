package org.TeamL33T.IpodMod;

import java.io.IOException;

import org.TeamL33T.IpodMod.battery.*;

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
			return new ItemStack(iPod);
		}
	};
	
	public static Item iPod = new Ipod();
	public static Item iPodCircuit = new IpodCircuit();
	public static Item batteryBasic = new BatteryBasic();
	public static Item batteryPro = new BatteryPro();
	public static Item batteryMega = new BatteryMega();
	public static Item batteryUltra = new BatteryUltra();
	public static Item batteryGalactic = new BatteryGalactic();
	public static Block IpodCharger = new BlockIpodCharger(506, false);
	public static IpodSoundLoader eventSoundLoader;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws IOException {
		eventSoundLoader = new IpodSoundLoader();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		// Game Registries
		GameRegistry.registerItem(iPod, "iPod");
		GameRegistry.registerItem(batteryBasic, "Basic iPod Battery");
		GameRegistry.registerItem(batteryPro, "Pro iPod Battery");
		GameRegistry.registerItem(batteryMega, "Mega iPod Battery");
		GameRegistry.registerItem(batteryUltra, "Ultra iPod Battery");
		GameRegistry.registerItem(batteryGalactic, "Galactic iPod Battery");
		GameRegistry.registerBlock(IpodCharger, "IpodCharger");
		
		// Language Registries
		LanguageRegistry.addName(iPod, "iPod");
		LanguageRegistry.addName(batteryBasic, "Basic iPod Battery");
		LanguageRegistry.addName(batteryPro, "Pro iPod Battery");
		LanguageRegistry.addName(batteryMega, "Mega iPod Battery");
		LanguageRegistry.addName(batteryUltra, "Ultra iPod Battery");
		LanguageRegistry.addName(batteryGalactic, "Galactic iPod Battery");
		LanguageRegistry.addName(IpodCharger, "iPod Charger");
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabIpod", "en_US", "iPod Mod");
		
		// Minecraft Forge Registries
		MinecraftForge.EVENT_BUS.register(eventSoundLoader);
		MinecraftForge.setBlockHarvestLevel(IpodCharger, "pickaxe", 1);
		
		// Crafting Recipes
		GameRegistry.addRecipe(new ItemStack(iPod), new Object[] {
			"sfs", "fcf", "rfr", 
			's', new ItemStack(501,1,1), 
			'f', new ItemStack(502,1,1),
			'c', new ItemStack(iPodCircuit),
			'r', new ItemStack(Item.redstone)
		});
		
		GameRegistry.addRecipe(new ItemStack(batteryBasic), new Object[] {
			" i ", " b ", " i ",
			'i', new ItemStack(Item.ingotIron),
			'b', new ItemStack(iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(batteryPro), new Object[] {
			" g ", "rbr", " g ",
			'g', new ItemStack(Item.ingotGold),
			'r', new ItemStack(Item.redstone),
			'b', new ItemStack(iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(batteryMega), new Object[] {
			"igi", "rbr", "igi",
			'i', new ItemStack(Item.ingotIron),
			'g', new ItemStack(Item.ingotGold),
			'r', new ItemStack(Item.redstone),
			'b', new ItemStack(iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(batteryUltra), new Object[] {
			"gdg", "rbr", "gdg",
			'd', new ItemStack(Item.diamond),
			'g', new ItemStack(Item.ingotGold),
			'r', new ItemStack(Item.redstone),
			'b', new ItemStack(iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(batteryGalactic), new Object[] {
			"ddd", "lbl", "ddd",
			'd', new ItemStack(Block.blockDiamond),
			'l', new ItemStack(Item.bucketLava), 
			'b', new ItemStack(iPodCircuit)
		});
	}
	
}

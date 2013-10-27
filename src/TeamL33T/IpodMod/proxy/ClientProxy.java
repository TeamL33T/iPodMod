package TeamL33T.IpodMod.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import TeamL33T.IpodMod.GuiHandler;
import TeamL33T.IpodMod.IpodCraftingManager;
import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.database.LocalizedData;
import TeamL33T.IpodMod.database.UnlocalizedData;
import TeamL33T.IpodMod.entity.EntityAppleDealer;
import TeamL33T.IpodMod.model.ModelAppleDealer;
import TeamL33T.IpodMod.render.RenderAppleDealer;
import TeamL33T.IpodMod.worldgen.MainWorldGen;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderHandlers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityAppleDealer.class, new RenderAppleDealer(new ModelAppleDealer(), 0.5F));
	}

	@Override
	public void registerBlocks() {
		GameRegistry.registerBlock(Main.iPodWorkbench, UnlocalizedData.iPodWorkbench);
	}

	@Override
	public void registerItems() {
		GameRegistry.registerItem(Main.iPodCircuit, UnlocalizedData.iPodCircuit);
		GameRegistry.registerItem(Main.iPodMemCard, UnlocalizedData.iPodMemCard);
		GameRegistry.registerItem(Main.iPodScreen, UnlocalizedData.iPodScreen);
		GameRegistry.registerItem(Main.iPodSpeaker, UnlocalizedData.iPodSpeaker);
		GameRegistry.registerItem(Main.iPodUSBSlot, UnlocalizedData.iPodUSBSlot);
		GameRegistry.registerItem(Main.iPodFrame, UnlocalizedData.iPodFrame);
		GameRegistry.registerItem(Main.iPodCamera, UnlocalizedData.iPodCamera);
		GameRegistry.registerItem(Main.iPodButton, UnlocalizedData.iPodButton);
		
		GameRegistry.registerItem(Main.osPackOriginal, UnlocalizedData.OsOriginal);
		GameRegistry.registerItem(Main.osPackJailbreak, UnlocalizedData.OsJailbreak);
		
		GameRegistry.registerItem(Main.iPod, UnlocalizedData.iPodClassic);
		GameRegistry.registerItem(Main.iPodNano, UnlocalizedData.iPodNano);
		
		GameRegistry.registerItem(Main.batteryBasic, UnlocalizedData.BatteryBasic);
		GameRegistry.registerItem(Main.batteryPro, UnlocalizedData.BatteryPro);
		GameRegistry.registerItem(Main.batteryMega, UnlocalizedData.BatteryMega);
		GameRegistry.registerItem(Main.batteryUltra, UnlocalizedData.BatteryUltra);
		GameRegistry.registerItem(Main.batteryGalactic, UnlocalizedData.BatteryGalactic);
	}

	@Override
	public void registerEvents() {
		
	}

	@Override
	public void registerEntities() {
		// Apple Dealer
		EntityRegistry.registerGlobalEntityID(EntityAppleDealer.class, UnlocalizedData.AppleDealer, EntityRegistry.findGlobalUniqueEntityId(), 3515848, 12102);
	}

	@Override
	public void registerTileEntities() {
		
	}

	@Override
	public void registerLocals() {
		// Blocks
		LanguageRegistry.addName(Main.iPodWorkbench, LocalizedData.iPodWorkbench);
		
		// Items
		LanguageRegistry.addName(Main.iPodCircuit, LocalizedData.iPodCircuit);
		LanguageRegistry.addName(Main.iPodMemCard, LocalizedData.iPodMemCard);
		LanguageRegistry.addName(Main.iPodScreen, LocalizedData.iPodScreen);
		LanguageRegistry.addName(Main.iPodSpeaker, LocalizedData.iPodSpeaker);
		LanguageRegistry.addName(Main.iPodUSBSlot, LocalizedData.iPodUSBSlot);
		LanguageRegistry.addName(Main.iPodFrame, LocalizedData.iPodFrame);
		LanguageRegistry.addName(Main.iPodCamera, LocalizedData.iPodCamera);
		LanguageRegistry.addName(Main.iPodButton, LocalizedData.iPodButton);
		
		LanguageRegistry.addName(Main.osPackOriginal, LocalizedData.OsOriginal);
		LanguageRegistry.addName(Main.osPackJailbreak, LocalizedData.OsJailbreak);
		
		LanguageRegistry.addName(Main.iPod, LocalizedData.iPodClassic);
		LanguageRegistry.addName(Main.iPodNano, LocalizedData.iPodNano);
		
		LanguageRegistry.addName(Main.batteryBasic, LocalizedData.BatteryBasic);
		LanguageRegistry.addName(Main.batteryPro, LocalizedData.BatteryPro);
		LanguageRegistry.addName(Main.batteryMega, LocalizedData.BatteryMega);
		LanguageRegistry.addName(Main.batteryUltra, LocalizedData.BatteryUltra);
		LanguageRegistry.addName(Main.batteryGalactic, LocalizedData.BatteryGalactic);
		
		// Creative Tabs
		LanguageRegistry.instance().addStringLocalization("itemGroup.tabIpod", "iPod Mod");
		
		// Entities
		LanguageRegistry.instance().addStringLocalization("entity." + UnlocalizedData.AppleDealer + ".name", LocalizedData.AppleDealer);
	}

	@Override
	public void registerMisc() {
		new GuiHandler();
		new IpodCraftingManager();
	}
	
	@Override
	public void registerWorldGens() {
		GameRegistry.registerWorldGenerator(new MainWorldGen());
	}
	
	@Override
	public void registerRecipes() {
		GameRegistry.addRecipe(new ItemStack(Main.batteryBasic), new Object[] {
			" i ", " b ", " i ",
			'i', new ItemStack(Item.ingotIron),
			'b', new ItemStack(Main.iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.batteryPro), new Object[] {
			" g ", "rbr", " g ",
			'g', new ItemStack(Item.ingotGold),
			'r', new ItemStack(Item.redstone),
			'b', new ItemStack(Main.iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.batteryMega), new Object[] {
			"igi", "rbr", "igi",
			'i', new ItemStack(Item.ingotIron),
			'g', new ItemStack(Item.ingotGold),
			'r', new ItemStack(Item.redstone),
			'b', new ItemStack(Main.iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.batteryUltra), new Object[] {
			"gdg", "rbr", "gdg",
			'd', new ItemStack(Item.diamond),
			'g', new ItemStack(Item.ingotGold),
			'r', new ItemStack(Item.redstone),
			'b', new ItemStack(Main.iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.batteryGalactic), new Object[] {
			"ddd", "lbl", "ddd",
			'd', new ItemStack(Item.diamond),
			'l', new ItemStack(Item.bucketLava), 
			'b', new ItemStack(Main.iPodCircuit)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodWorkbench), new Object[] {
			"D*D", "#!#", "D*D",
			'D', new ItemStack(Item.diamond),
			'*', new ItemStack(Item.redstone),
			'#', new ItemStack(Item.bucketLava),
			'!', new ItemStack(Item.redstoneRepeater)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodFrame), new Object[] {
			"WWW", "WIW", "WWW",
			'W', new ItemStack(Block.cloth),
			'I', new ItemStack(Item.ingotIron)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodCircuit), new Object[] {
			"!R!", "[{[", "!R!",
			'R', new ItemStack(Item.redstone),
			'!', new ItemStack(Item.ingotIron),
			'[', new ItemStack(Item.ingotGold),
			'{', new ItemStack(Item.diamond)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodMemCard), new Object[] {
			"!R!", "-X-", "!R!",
			'R', new ItemStack(Item.redstone),
			'-', new ItemStack(Item.ingotGold),
			'!', new ItemStack(Item.ingotIron),
			'X', new ItemStack(Block.chestTrapped)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodScreen), new Object[] {
			"!R!", "-G-", "!P!",
			'R', new ItemStack(Item.redstone),
			'-', new ItemStack(Item.ingotGold),
			'!', new ItemStack(Item.ingotIron),
			'G', new ItemStack(Block.glass),
			'P', new ItemStack(Item.enderPearl)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodUSBSlot), new Object[] {
			"!R!", "! !", "   ",
			'R', new ItemStack(Item.redstone),
			'!', new ItemStack(Item.ingotIron),
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodSpeaker), new Object[] {
			"!R!", "-J-", "!R!",
			'R', new ItemStack(Item.redstone),
			'-', new ItemStack(Item.ingotGold),
			'!', new ItemStack(Item.ingotIron),
			'J', new ItemStack(Block.jukebox)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodCamera), new Object[] {
			"!R!", "-G-", "!R!",
			'R', new ItemStack(Item.redstone),
			'-', new ItemStack(Item.ingotGold),
			'!', new ItemStack(Item.ingotIron),
			'G', new ItemStack(Block.glass)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.iPodButton), new Object[] {
			" R ", " _ ", " ! ",
			'R', new ItemStack(Item.redstone),
			'!', new ItemStack(Item.ingotIron),
			'_', new ItemStack(Block.pressurePlateStone)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.osPackOriginal), new Object[] {
			"!R!", "@X@", "!A!",
			'R', new ItemStack(Item.redstone),
			'A', new ItemStack(Item.appleRed),
			'!', new ItemStack(Item.ingotIron),
			'@', new ItemStack(Item.enderPearl),
			'X', new ItemStack(Block.chestTrapped)
		});
		
		GameRegistry.addRecipe(new ItemStack(Main.osPackJailbreak), new Object[] {
			"#R#", "DXD", "#A#",
			'R', new ItemStack(Item.redstone),
			'#', new ItemStack(Item.ingotGold),
			'A', new ItemStack(Item.appleGold),
			'D', new ItemStack(Item.enderPearl),
			'X', new ItemStack(Block.chestTrapped)
		});
		
		IpodCraftingManager.instance.addRecipe(new ItemStack(Main.iPod), new Object[] {
			"WW#WW", "WD#DW", "WD#DW", "W~+~W", "W?[?W",
			'W', new ItemStack(Main.iPodFrame),
			'#', new ItemStack(Main.iPodCircuit),
			'D', new ItemStack(Main.iPodScreen),
			'~', new ItemStack(Main.iPodMemCard),
			'?', new ItemStack(Main.iPodSpeaker),
			'+', new ItemStack(Main.iPodButton, 3),
			'[', new ItemStack(Main.iPodUSBSlot)
		});
		
		IpodCraftingManager.instance.addRecipe(new ItemStack(Main.iPodNano), new Object[] {
			" W~W ", "W#D#W", "WD~DW", "W?+?W", " W[W ",
			'W', new ItemStack(Main.iPodFrame),
			'#', new ItemStack(Main.iPodCircuit),
			'D', new ItemStack(Main.iPodScreen),
			'~', new ItemStack(Main.iPodMemCard),
			'?', new ItemStack(Main.iPodSpeaker),
			'+', new ItemStack(Main.iPodButton),
			'[', new ItemStack(Main.iPodUSBSlot)
		});
		
	}
	
}

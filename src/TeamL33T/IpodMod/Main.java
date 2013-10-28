package TeamL33T.IpodMod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import TeamL33T.IpodMod.battery.BatteryBasic;
import TeamL33T.IpodMod.battery.BatteryGalactic;
import TeamL33T.IpodMod.battery.BatteryMega;
import TeamL33T.IpodMod.battery.BatteryPro;
import TeamL33T.IpodMod.battery.BatteryUltra;
import TeamL33T.IpodMod.blocks.BlockIpodCraftingTable;
import TeamL33T.IpodMod.database.ModInfo;
import TeamL33T.IpodMod.items.IpodButton;
import TeamL33T.IpodMod.items.IpodCamera;
import TeamL33T.IpodMod.items.IpodCircuit;
import TeamL33T.IpodMod.items.IpodFrame;
import TeamL33T.IpodMod.items.IpodMemCard;
import TeamL33T.IpodMod.items.IpodScreen;
import TeamL33T.IpodMod.items.IpodSpeaker;
import TeamL33T.IpodMod.items.IpodUSBSlot;
import TeamL33T.IpodMod.items.OSPackJailbreak;
import TeamL33T.IpodMod.items.OSPackOriginal;
import TeamL33T.IpodMod.items.ipod.IpodClassic;
import TeamL33T.IpodMod.items.ipod.IpodNano;
import TeamL33T.IpodMod.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Main {
	
	@Instance(ModInfo.ID)
	public static Main instance;
	
	@SidedProxy(clientSide = ModInfo.CLIENTP_LOC, serverSide = ModInfo.SERVERP_LOC)
	public static CommonProxy proxy;
	
	public static CreativeTabs tabIpod = new CreativeTabs("tabIpod") {
		public ItemStack getIconItemStack(){
			return new ItemStack(iPodClassic);
		}
	};
	
	public static SoundListReader SLR = new SoundListReader();
	public static GuiHandler guiHandler = new GuiHandler();
	public static WavPlayer Player = new WavPlayer();
	
	public static Item iPodClassic = new IpodClassic();
	public static Item iPodNano = new IpodNano();
	
	public static Item iPodFrame = new IpodFrame();
	public static Item iPodCircuit = new IpodCircuit();
	public static Item iPodMemCard = new IpodMemCard();
	public static Item iPodScreen = new IpodScreen();
	public static Item iPodSpeaker = new IpodSpeaker();
	public static Item iPodUSBSlot = new IpodUSBSlot();
	public static Item iPodCamera = new IpodCamera();
	public static Item iPodButton = new IpodButton();
	
	public static Item batteryBasic = new BatteryBasic();
	public static Item batteryPro = new BatteryPro();
	public static Item batteryMega = new BatteryMega();
	public static Item batteryUltra = new BatteryUltra();
	public static Item batteryGalactic = new BatteryGalactic();
	
	public static Item osPackOriginal = new OSPackOriginal();
	public static Item osPackJailbreak = new OSPackJailbreak();
	
	public static Block iPodWorkbench = new BlockIpodCraftingTable();
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerEvents();
		proxy.registerBlocks();
		proxy.registerItems();
		proxy.registerEntities();
		proxy.registerRenderHandlers();
		proxy.registerTileEntities();
		proxy.registerWorldGens();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerMisc();
		proxy.registerRecipes();
		proxy.registerLocals();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}

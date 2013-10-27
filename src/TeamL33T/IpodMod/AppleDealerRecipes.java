package TeamL33T.IpodMod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class AppleDealerRecipes {
	
	public static final ItemStack[] ipodR = { new ItemStack(Item.diamond, 1), new ItemStack(Item.emerald, 5), new ItemStack(Main.iPod, 1) };
	public static final ItemStack[] bBatteryR = { new ItemStack(Item.ingotGold, 1), new ItemStack(Item.ingotIron, 3), new ItemStack(Main.batteryBasic, 1) };
	public static final ItemStack[] pBatteryR = { new ItemStack(Item.ingotGold, 2), new ItemStack(Item.ingotIron, 5), new ItemStack(Main.batteryPro, 1) };
	public static final ItemStack[] mBatteryR = { new ItemStack(Item.ingotGold, 3), new ItemStack(Item.ingotIron, 8), new ItemStack(Main.batteryMega, 1) };
	public static final ItemStack[] uBatteryR = { new ItemStack(Item.ingotGold, 5), new ItemStack(Item.ingotIron, 10), new ItemStack(Main.batteryUltra, 1) };
	public static final ItemStack[] gBatteryR = { new ItemStack(Item.ingotGold, 10), new ItemStack(Item.diamond, 1), new ItemStack(Main.batteryGalactic, 1) };
			
	public static final int IPOD = 0;
	public static final int B_BASIC = 1;
	public static final int B_PRO = 2;
	public static final int B_MEGA = 3;
	public static final int B_ULTRA = 4;
	public static final int B_GALACTIC = 5;
	
	public static final int recipesCount = 5;
	
	public static ItemStack[] getRecipes(int i) {
		switch (i) {
			case 0: return ipodR;
			case 1: return bBatteryR;
			case 2: return pBatteryR;
			case 3: return mBatteryR;
			case 4: return uBatteryR;
			case 5: return gBatteryR;
			default: return ipodR;
		}
	}
	
	public static String getName(int i) {
		switch (i) {
			case 0: return "iPod";
			case 1: return "Basic Battery";
			case 2: return "Pro Battery";
			case 3: return "Mega Battery";
			case 4: return "Ultra Battery";
			case 5: return "Galactic Battery";
			default: return "iPod";
		}
	}
	
}

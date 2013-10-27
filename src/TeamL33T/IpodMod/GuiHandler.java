package TeamL33T.IpodMod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import TeamL33T.IpodMod.container.ContainerAppleDeal;
import TeamL33T.IpodMod.container.ContainerIpodCraftingTable;
import TeamL33T.IpodMod.database.GuiIndex;
import TeamL33T.IpodMod.gui.GuiAppleDeal;
import TeamL33T.IpodMod.gui.GuiIpodCraftingTable;
import TeamL33T.IpodMod.gui.ipod.GuiIpod;
import TeamL33T.IpodMod.gui.ipod.nano.GuiIpodNanoMain1;
import TeamL33T.IpodMod.tileentity.InventoryAppleDeal;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler {
	
	public GuiHandler() {
		NetworkRegistry.instance().registerGuiHandler(Main.instance, this);
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GuiIndex.IpodSuperClassic:
			return null;
		case GuiIndex.AppleDeal:
			return new ContainerAppleDeal(player.inventory, new InventoryAppleDeal());
		case GuiIndex.IpodCharger:
			return null;
		case GuiIndex.IpodWorkbench:
			System.out.println("serverGuiHnd: on iPod Workbench, creating container");
			return new ContainerIpodCraftingTable(player.inventory);
		case GuiIndex.IpodNanoMainOne:
			return null;
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch (ID) {
		case GuiIndex.IpodSuperClassic:
			return new GuiIpod();
		case GuiIndex.AppleDeal:
			return new GuiAppleDeal(player.inventory, new InventoryAppleDeal());
		case GuiIndex.IpodCharger:
			return null;
		case GuiIndex.IpodWorkbench:
			System.out.println("clientGuiHnd: on iPod Workbench, creating gui");
			return new GuiIpodCraftingTable(player.inventory);
		case GuiIndex.IpodNanoMainOne:
			return new GuiIpodNanoMain1(player);
		default:
			return null;
		}
	}

}

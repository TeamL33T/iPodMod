package TeamL33T.IpodMod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import TeamL33T.IpodMod.Main;
import TeamL33T.IpodMod.database.GuiIndex;
import TeamL33T.IpodMod.database.IdData;
import TeamL33T.IpodMod.database.UnlocalizedData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIpodCraftingTable extends Block {

	@SideOnly(Side.CLIENT)
	public Icon iconTop;
	@SideOnly(Side.CLIENT)
	public Icon iconBottom;
	
	public BlockIpodCraftingTable() {
		super(IdData.iPodWorkbench, Material.rock);
		this.setCreativeTab(Main.tabIpod);
		this.setUnlocalizedName(UnlocalizedData.iPodWorkbench);
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		switch (par1) {
		case 0: return this.iconBottom;
		case 1: return this.iconTop;
		default: return this.blockIcon;
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconR) { 
		this.blockIcon = iconR.registerIcon("ipodmod:techct_side");
		this.iconTop = iconR.registerIcon("ipodmod:techct_top");
		this.iconBottom = iconR.registerIcon("ipodmod:techct_bottom");
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		if (!par5EntityPlayer.isSneaking()) {
			par5EntityPlayer.openGui(Main.instance, GuiIndex.IpodWorkbench, par1World, par2, par3, par4);
			return true;
		} else {
			return false;
		}
	}

}

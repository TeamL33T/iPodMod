package org.TeamL33T.IpodMod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockIpodCharger extends BlockContainer
{
    /**
     * Is the random generator used by ipod charger to drop the inventory contents in random directions.
     */
    private final Random ipodChargerRand = new Random();

    /** True if this is an active ipod charger, false if idle */
    private final boolean isActive;

    /**
     * This flag is used to prevent the ipod charger inventory to be dropped upon block removal, is used internally when the
     * ipod charger block changes from idle to active and vice-versa.
     */
    private static boolean keepIpodChargerInventory;
    @SideOnly(Side.CLIENT)
    private Icon ipodChargerIconTop;
    @SideOnly(Side.CLIENT)
    private Icon ipodChargerIconFront;

    public BlockIpodCharger(int par1, boolean par2)
    {
        super(par1, Material.rock);
        this.isActive = par2;
        this.setUnlocalizedName("IpodCharger");
        this.setHardness(2.0F);
        this.setCreativeTab(Main.tabIpod);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return Main.IpodCharger.blockID;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    /**
     * set a blocks direction
     */
    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int l = par1World.getBlockId(par2, par3, par4 - 1);
            int i1 = par1World.getBlockId(par2, par3, par4 + 1);
            int j1 = par1World.getBlockId(par2 - 1, par3, par4);
            int k1 = par1World.getBlockId(par2 + 1, par3, par4);
            byte b0 = 3;

            if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
            {
                b0 = 3;
            }

            if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
            {
                b0 = 2;
            }

            if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
            {
                b0 = 5;
            }

            if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
            {
                b0 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, b0, 2);
        }
    }

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        return par1 == 1 ? this.ipodChargerIconTop : (par1 == 0 ? this.ipodChargerIconTop : (par1 != par2 ? this.blockIcon : this.ipodChargerIconFront));
    }

    @SideOnly(Side.CLIENT)

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("ipodmod:ipodCharger_side");
        this.ipodChargerIconFront = par1IconRegister.registerIcon(this.isActive ? "ipodmod:ipodCharger_front_on" : "ipodmod:ipodCharger_front_off");
        this.ipodChargerIconTop = par1IconRegister.registerIcon("ipodmod:ipodCharger_top");
    }

    public void displayGUIIpodCharger(TileEntityIpodCharger par1TileEntityIpodCharger) {}
    
    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, BlockIpodCharger par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityIpodCharger tileentityipodcharger = (TileEntityIpodCharger)par1World.getBlockTileEntity(par2, par3, par4);

            if (tileentityipodcharger != null)
            {
                par5EntityPlayer.displayGUIIpodCharger(tileentityipodcharger);
            }

            return true;
        }
    }

    /**
     * Update which block ID the ipod charger is using depending on whether or not it is activated
     */
    public static void updateIpodChargerBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
        int l = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity tileentity = par1World.getBlockTileEntity(par2, par3, par4);
        keepIpodChargerInventory = true;

        if (par0)
        {
            par1World.setBlock(par2, par3, par4, Main.IpodCharger.blockID);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, Main.IpodCharger.blockID);
        }

        keepIpodChargerInventory = false;
        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            par1World.setBlockTileEntity(par2, par3, par4, tileentity);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityIpodCharger(10);
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

        if (par6ItemStack.hasDisplayName())
        {
            ((TileEntityIpodCharger)par1World.getBlockTileEntity(par2, par3, par4)).getInvName();
        }
    }

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (!keepIpodChargerInventory)
        {
            TileEntityIpodCharger tileentityipodcharger = (TileEntityIpodCharger)par1World.getBlockTileEntity(par2, par3, par4);

            if (tileentityipodcharger != null)
            {
                for (int j1 = 0; j1 < tileentityipodcharger.getSizeInventory(); ++j1)
                {
                    ItemStack itemstack = tileentityipodcharger.getStackInSlot(j1);

                    if (itemstack != null)
                    {
                        float f = this.ipodChargerRand.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.ipodChargerRand.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.ipodChargerRand.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0)
                        {
                            int k1 = this.ipodChargerRand.nextInt(21) + 10;

                            if (k1 > itemstack.stackSize)
                            {
                                k1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= k1;
                            EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.itemID, k1, itemstack.getItemDamage()));

                            if (itemstack.hasTagCompound())
                            {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.ipodChargerRand.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.ipodChargerRand.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.ipodChargerRand.nextGaussian() * f3);
                            par1World.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                par1World.func_96440_m(par2, par3, par4, par5);
            }
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
    {
        return Container.calcRedstoneFromInventory((IInventory)par1World.getBlockTileEntity(par2, par3, par4));
    }

    @SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return Main.IpodCharger.blockID;
    }
}


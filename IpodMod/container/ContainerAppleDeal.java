package TeamL33T.IpodMod.container;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import TeamL33T.IpodMod.tileentity.InventoryAppleDeal;

public class ContainerAppleDeal extends Container {

	private InventoryAppleDeal tileentity;
	
	public ContainerAppleDeal(InventoryPlayer inventory, InventoryAppleDeal tileentity) {
		this.tileentity = tileentity;
		
		int index = 8;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				index++;
				this.addSlotToContainer(new Slot(inventory, index, 8 + x * 18, 84 + y * 18));
			}
		}
		
		for (int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(inventory, x, 8 + x * 18, 142));
		}
		
		this.addSlotToContainer(new Slot(tileentity, 0, 36, 53));
		this.addSlotToContainer(new Slot(tileentity, 1, 62, 53));
		this.addSlotToContainer(new Slot(tileentity, 2, 117, 53));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tileentity.isUseableByPlayer(entityplayer);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		return null;
	}

}

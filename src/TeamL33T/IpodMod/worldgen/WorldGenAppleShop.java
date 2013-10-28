package TeamL33T.IpodMod.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import TeamL33T.IpodMod.entity.EntityAppleDealer;

public class WorldGenAppleShop extends WorldGenerator {

	// WorldGenAppleShop Build 2 (Fix 1)
	
	/* Do remember:
	 * X LEFT-RIGHT (Left is lower, right is higher)
	 * Y LOW-HIGH (nuff said)
	 * Z UP-DOWN (North is lower, south is higher)
	 */
	
	private World mWorld;
	
	public boolean generate(World world, Random random, int x, int y, int z) {
		boolean needsPost = false;
		this.mWorld = world;
		
		if (world.getBlockId(x, y, z) == 2) {
			
			// START OF CHECKING
			
			// Analyze if y-point layers' surfaces (except 1st layer) are air, grass, flowers, or
			for (int y1 = 1; y1 <= 5; y1++) {
				for (int z1 = 0; z1 < 11; z1++) {
					for (int x1 = 0; x1 < 10; x1++) {
						int curBlockId = world.getBlockId(x + x1, y + y1, z + z1);
						
						if (curBlockId != 0 || curBlockId != 31 || curBlockId != 37 ||
							curBlockId != 38 || curBlockId != 39 || curBlockId != 40) {
							return false;
						}
					}
				}
			}
			
			// Analyze if it needs a wood post
			for (int z1 = 0; z1 < 11; z1++) {
				for (int x1 = 0; x1 < 10; x1++) {
					if (world.getBlockId(x + x1, y, z + z1) == 0) {
						needsPost = true;
						break;
					}
				}
				
				if (needsPost) break;
			}
			
			// END OF CHECKING || START OF BUILDING
			
			// Clear everything on the 2nd layer (such as grasses, flowers, mushies)
			for (int z1 = 0; z1 < 11; z1++) {
				for (int x1 = 0; x1 < 10; x1++) {
					world.setBlockToAir(x + x1, y + 1, z + z1);	
				}
			}
			
			// Make the wood post under, if necessary (wood floor then fence support)
			if (needsPost) {
				this.makeHollowQuad(10, 11, x, y, z, Block.wood.blockID);
				
				int curY = y - 1;
				boolean vertex1b = true; // Top-Left
				boolean vertex2b = true; // Top-Right
				boolean vertex3b = true; // Bottom-Left
				boolean vertex4b = true; // Bottom-Right
				
				while (vertex1b || vertex2b || vertex3b || vertex4b) {
					if (world.getBlockId(x, curY, z) == 0) {
						world.setBlock(x, curY, z, Block.fence.blockID);
					} else {
						vertex1b = false;
					}
					
					if (world.getBlockId(x + 9, curY, z) == 0) {
						world.setBlock(x + 9, curY, z, Block.fence.blockID);
					} else {
						vertex2b = false;
					}
					
					if (world.getBlockId(x, curY, z + 10) == 0) {
						world.setBlock(x, curY, z + 10, Block.fence.blockID);
					} else {
						vertex3b = false;
					}
					
					if (world.getBlockId(x + 9, curY, z + 10) == 0) {
						world.setBlock(x + 9, curY, z + 10, Block.fence.blockID);
					} else {
						vertex4b = false;
					}
					
					curY--;
				}
			}
			
			// Make the wool and quartz layer
			this.makeFilledQuad(8, 9, x + 1, y, z + 1, Block.cloth.blockID);
			this.makeHollowQuad(10, 11, x, y + 1, z, Block.blockNetherQuartz.blockID);
			
			// Make the quads of glass and roof
			for (int y1 = 2; y1 <= 5; y1++) {
				
				if (y1 == 5) {
					this.makeHollowQuad(10, 11, x, y + y1, z, Block.blockNetherQuartz.blockID);
					this.makeHollowQuad(8, 9, x + 1, y + y1, z + 1, Block.glowStone.blockID);
					this.makeFilledQuad(6, 7, x + 2, y + y1, z + 2, Block.blockNetherQuartz.blockID);
				} else {
					this.makeHollowQuad(10, 11, x, y + y1, z, Block.glass.blockID);
				}
				
			}
			
			// Remove some junk glass and quartz
			for (int y1 = 1; y1 <= 4; y1++) {
				world.setBlockToAir(x + 4, y + y1, z + 10);
				world.setBlockToAir(x + 5, y + y1, z + 10);
			}
			
			// The counter
			for (int x1 = 1; x1 <= 4; x1++) {
				world.setBlock(x + x1, y + 1, z + 2, Block.wood.blockID);
				
				if (x1 == 4) 
					world.setBlock(x + x1, y + 1, z + 1, Block.trapdoor.blockID);
			}
			
			// The Displays
			for (int z1 = 2; z1 <= 8; z1 += 2) {
				world.setBlock(x + 9, y + 1, z + z1, Block.cloth.blockID);
				world.setBlock(x + 9, y + 2, z + z1, Block.cloth.blockID);
			}
			
			// The Dealer
			EntityAppleDealer employee = new EntityAppleDealer(world);
			employee.setPosition(x + 2, y + 2, z + 1);
			world.spawnEntityInWorld(employee);
			
			return true;
		} else {
			return false;
		}
	}
	
	private void makeHollowQuad(int width, int length, int x, int y, int z, int blockID) {
		for (int x1 = 0; x1 < width; x1++) {
			mWorld.setBlock(x + x1, y, z, blockID);
			mWorld.setBlock(x + x1, y, z + (length - 1), blockID);
		}
		
		for (int z1 = 0; z1 < length; z1++) {
			mWorld.setBlock(x, y, z + z1, blockID);
			mWorld.setBlock(x + (width - 1), y, z + z1, blockID);
		}
	}
	
	private void makeFilledQuad(int width, int length, int x, int y, int z, int blockID) {
		for (int z1 = length; z1 < length; z1++) {
			for (int x1 = width; x1 < width; x1++) {
				mWorld.setBlock(x + x1, y, z + z1, blockID);
			}
		}
	}
	
}

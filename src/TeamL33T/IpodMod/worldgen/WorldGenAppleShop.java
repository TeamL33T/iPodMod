package TeamL33T.IpodMod.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import TeamL33T.IpodMod.entity.EntityAppleDealer;

public class WorldGenAppleShop extends WorldGenerator {

	/* Do remember:
	 * X LEFT-RIGHT
	 * Y LOW-HIGH
	 * Z UP-DOWN
	 */
	
	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if (world.getBlockId(x, y, z) == Block.dirt.blockID) {
			
			// START OF CHECKING
			
			// Analyze if the surface is all grass
			for (int z1 = 0; z1 < 11; z1++) {
				for (int x1 = 0; x1 < 10; x1++) {
					if (world.getBlockId(x + x1, y, z + z1) != Block.dirt.blockID) {
						return false;
					}
				}
			}
			
			// Analyze if y-point layers' surfaces are air
			for (int y1 = 0; y1 < 5; y1++) {
				for (int z1 = 0; z1 < 11; z1++) {
					for (int x1 = 0; x1 < 10; x1++) {
						if (world.getBlockId(x + x1, y + y1, z + z1) != 0) {
							return false;
						}
					}
				}
			}
			
			// Analyze if lower 2 layers' surfaces are air
			for (int y1 = 1; y1 <= 2; y1++) {
				for (int z1 = 0; z1 < 11; z1++) {
					for (int x1 = 0; x1 < 10; x1++) {
						if (world.getBlockId(x + x1, y - y1, z + z1) == 0) {
							return false;
						}
					}
				}
			}
			
			// END OF CHECKING || START OF BUILDING
			
			// Make 0th layer's surface wool
			for (int z1 = 0; z1 < 11; z1++) {
				for (int x1 = 0; x1 < 10; x1++) {
					world.setBlock(x + x1, y, z + z1, Block.cloth.blockID);
				}
			}
			
			// Make the quads of quartz
			for (int y1 = 1; y1 <= 5; y1++) {
				
				if (y1 == 1) {
					for (int x1 = 0; x1 < 10; x1++) {
						world.setBlock(x + x1, y + y1, z, Block.blockNetherQuartz.blockID);
						
						if ((x + x1) != 4 || (x + x1) != 5) {
							world.setBlock(x + x1, y + y1, z + 10, Block.blockNetherQuartz.blockID);
						}
					}
					
					for (int z1 = 0; z1 < 11; z1++) {
						world.setBlock(x, y + y1, z + z1, Block.blockNetherQuartz.blockID);
						world.setBlock(x + 10, y + y1, z + z1, Block.blockNetherQuartz.blockID);
					}
				} else if (y1 == 5) {
					Random randTmp = new Random();
					
					for (int z1 = 0; z1 < 11; z1++) {
						for (int x1 = 0; x1 < 10; x1++) {
							world.setBlock(x + x1, y + y1, z + z1, randTmp.nextInt(3) <= 1 ? Block.blockNetherQuartz.blockID : Block.glowStone.blockID);
						}
					}
				} else {
					for (int x1 = 0; x1 < 10; x1++) {
						world.setBlock(x + x1, y + y1, z, Block.glass.blockID);
						
						if (((x + x1) != 4 || (x + x1) != 5) && y1 != 4) {
							world.setBlock(x + x1, y + y1, z + 10, Block.glass.blockID);
						}
					}
					
					for (int z1 = 0; z1 < 11; z1++) {
						world.setBlock(x, y + y1, z + z1, Block.glass.blockID);
						world.setBlock(x + 10, y + y1, z + z1, Block.glass.blockID);
					}
				}
				
			}
			
			// The counter
			world.setBlock(x + 1, y + 1, z + 2, Block.wood.blockID);
			world.setBlock(x + 2, y + 1, z + 2, Block.wood.blockID);
			world.setBlock(x + 3, y + 1, z + 2, Block.wood.blockID);
			world.setBlock(x + 4, y + 1, z + 2, Block.wood.blockID);
			world.setBlock(x + 4, y + 1, z + 1, Block.trapdoor.blockID);
			
			// The Displays
			world.setBlock(x + 9, y + 1, z + 2, Block.cloth.blockID);
			world.setBlock(x + 9, y + 2, z + 2, Block.cloth.blockID);
			world.setBlock(x + 9, y + 1, z + 4, Block.cloth.blockID);
			world.setBlock(x + 9, y + 2, z + 4, Block.cloth.blockID);
			world.setBlock(x + 9, y + 1, z + 6, Block.cloth.blockID);
			world.setBlock(x + 9, y + 2, z + 6, Block.cloth.blockID);
			world.setBlock(x + 9, y + 1, z + 8, Block.cloth.blockID);
			world.setBlock(x + 9, y + 2, z + 8, Block.cloth.blockID);
			
			// The Dealer
			EntityAppleDealer employee = new EntityAppleDealer(world);
			employee.setPosition(x + 2, y + 1, z + 1);
			world.spawnEntityInWorld(employee);
			
			return true;
		}
		
		return false;
	}
	
}

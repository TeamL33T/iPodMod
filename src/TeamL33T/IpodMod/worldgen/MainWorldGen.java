package TeamL33T.IpodMod.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class MainWorldGen implements IWorldGenerator {

	private final int appleShopMaxSpawn = 10;
	private final int appleShopMaxChances = 10;
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId) {
		case 0: 
			this.generateSurface(world, random, chunkX, chunkZ); 
			break;
		case 1:
			this.generateNether(world, random, chunkX, chunkZ);
			break;
		case -1:
			this.generateEnd(world, random, chunkX, chunkZ);
			break;
		}
	}
	
	private void generateNether(World world, Random random, int chunkX, int chunkZ) { }
	
	private void generateEnd(World world, Random random, int chunkX, int chunkZ) { }
	
	private void generateSurface(World world, Random random, int chunkX, int chunkZ) {
		int chances = 0;
		int spawned = 0;
		
		for (int i = 0; i < appleShopMaxSpawn; i++) {
			int xPos = chunkX + random.nextInt(16);
			int yPos = random.nextInt(21) + 65;
			int zPos = chunkZ + random.nextInt(16);
			
			if (world.getBiomeGenForCoords(xPos, zPos).biomeName != "Plains") {
				i--;
				continue;
			}
			
			boolean genProc = (new WorldGenAppleShop()).generate(world, random, xPos, yPos, zPos);
			
			if (genProc == false && chances < appleShopMaxChances) {
				i--;
				chances++;
				
				System.out.println("Failed to spawn a shop at "+xPos+","+yPos+","+zPos);
			} else if (chances >= appleShopMaxChances) {
				chances = 0;
			} else if (genProc) {
				System.out.println("Spawned an Apple Shop at "+xPos+","+yPos+","+zPos);
				spawned++;
			}
		}
	}

}

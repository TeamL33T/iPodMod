package TeamL33T.IpodMod;

import java.util.ArrayList;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class IpodCraftingManager {
	
	public static IpodCraftingManager instance;
	private ArrayList<ItemStack[]> recipeList = new ArrayList<ItemStack[]>();
	
	public IpodCraftingManager() {
		if (instance == null) instance = this;
	}
	
	public void addRecipe(ItemStack result, Object ... recipe) {
		ArrayList<String> unnamed = new ArrayList<String>();
		ArrayList<char[]> untitled = new ArrayList<char[]>();
		ArrayList<Character> jaja = new ArrayList<Character>();
		ArrayList<ItemStack> unknown = new ArrayList<ItemStack>();
		ItemStack[] stranger;
		
		boolean strWasFinished = false;
		boolean readyForChar = false;
		boolean isNewChar = true;
		
		String charBuild = "";
		
		for (int x = 0; x < recipe.length; x++) {
			if (recipe[x] instanceof String && recipe[x].toString().length() == 5) {
				unnamed.add((String)recipe[x]);
				
				if (unnamed.size() == 5) {
					strWasFinished = true;
					readyForChar = true;
					
					for (int y = 0; y < 5; y++) {
						untitled.add(unnamed.get(y).toCharArray());
						
						for (char sym : untitled.get(y)) {
							if (sym != ' ') {
								if (!charBuild.contains(String.valueOf(sym))) {
									charBuild += String.valueOf(sym);
									break;
								}
							}
						}
					}
					
					untitled.clear();
				}
			} else if ((strWasFinished && readyForChar) && recipe[x] instanceof ItemStack) {
				char recent = (Character)recipe[x-1];
				
				jaja.add((Character)recent);
				unknown.add((ItemStack)recipe[x]);
			}
		}
		
		ItemStack[] fullRecipe = new ItemStack[26];
		
		int index = -1;
		for (int x = 0; x < 5; x++) {
			String str = unnamed.get(x);
			char[] chA = str.toCharArray();
			
			for (Character ch : chA) {
				index++;
				ItemStack is = null;
				
				for (int y = 0; y < jaja.size(); y++) {
					if (jaja.get(y) == ch) {
						is = unknown.get(y);
						break;
					}
				}
				
				fullRecipe[index] = is;
			}
		}
		
		fullRecipe[25] = result;
		recipeList.add(fullRecipe);
	}
	
	public ItemStack findMatchingRecipe(IInventory iInv) {
		int index = -1;
		
		for (ItemStack[] fullRecipe : recipeList) {
			if (fullRecipe[index] == iInv.getStackInSlot(index)) {
				if (index == 24) {
					return fullRecipe[25];
				}
			} else {
				continue;
			}
		}
		
		return null;
	}
	
}

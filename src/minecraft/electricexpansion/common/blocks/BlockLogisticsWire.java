package electricexpansion.common.blocks;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.core.electricity.ElectricityConnections;
import universalelectricity.prefab.BlockConductor;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electricexpansion.common.ElectricExpansion;
import electricexpansion.common.cables.TileEntityLogisticsWire;
import electricexpansion.common.misc.EETab;

public class BlockLogisticsWire extends BlockConductor
{
	public BlockLogisticsWire(int id, int meta)
	{
		super(id, Material.cloth);
		this.setBlockName("LogisticsWire");
		this.setStepSound(soundClothFootstep);
		this.setResistance(0.2F);
		this.setHardness(0.1F);
		this.setBlockBounds(0.30F, 0.30F, 0.30F, 0.70F, 0.70F, 0.70F);
		this.setRequiresSelfNotify();
		this.setCreativeTab(EETab.INSTANCE);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.updateWireSwitch(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int par5)
	{
		super.onNeighborBlockChange(world, x, y, z, par5);
		this.updateWireSwitch(world, x, y, z);
	}

	private void updateWireSwitch(World world, int x, int y, int z)
	{
		TileEntityLogisticsWire tileEntity = (TileEntityLogisticsWire) world.getBlockTileEntity(x, y, z);

		if (!world.isRemote && tileEntity != null)
		{
			if (world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				ElectricityConnections.registerConnector(tileEntity, EnumSet.range(ForgeDirection.DOWN, ForgeDirection.EAST));
			}
			else
			{
				ElectricityConnections.registerConnector(tileEntity, EnumSet.of(ForgeDirection.UNKNOWN));
			}

			for (int i = 0; i < 6; i++)
			{
				ForgeDirection direction = ForgeDirection.getOrientation(i);

				Block block = Block.blocksList[world.getBlockId(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ)];

				if (block != null)
				{
					if (block.blockID != this.blockID)
					{
						try
						{
							block.onNeighborBlockChange(world, x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, this.blockID);
						}
						catch (Exception e)
						{
							FMLLog.severe("Failed to update switch wire");
							e.printStackTrace();
						}
					}
				}
			}

		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileEntityLogisticsWire();
	}

	@Override
	public String getTextureFile()
	{
		return ElectricExpansion.ALEX_ITEMS_TEXTURE_FILE;
	}

	@Override
	public boolean canProvidePower()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 0; var4 < 5; ++var4)
			par3List.add(new ItemStack(par1, 1, var4));
	}

	/**
	 * Called when the block is right clicked by the player
	 */
	@Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{
			TileEntityLogisticsWire tileEntity = (TileEntityLogisticsWire) par1World.getBlockTileEntity(par2, par3, par4);
			
		 /*     for(int x = 0; x < 5; x = x+1) {
		    	  
						if(par1World.isRemote)
		  				System.out.println(tileEntity.visuallyConnected[x] + " client");
		  				
		  				if(!par1World.isRemote)
		  					System.out.println(tileEntity.visuallyConnected[x] + " server");
		  			*/
		      // }	

			{
				par5EntityPlayer.openGui(ElectricExpansion.instance, 3, par1World, par2, par3, par4);
				return true;
			}
			
	}
}
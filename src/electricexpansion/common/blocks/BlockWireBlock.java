package electricexpansion.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electricexpansion.common.ElectricExpansion;
import electricexpansion.common.cables.TileEntityWireBlock;
import electricexpansion.common.helpers.BlockWireBase;
import electricexpansion.common.helpers.TileEntityConductorBase;
import electricexpansion.common.misc.EETab;

public class BlockWireBlock extends BlockWireBase
{
    public BlockWireBlock(int id)
    {
        super(id, Material.rock);
        this.setUnlocalizedName("HiddenWire");
        this.setStepSound(soundStoneFootstep);
        this.setResistance(0.2F);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setCreativeTab(EETab.INSTANCE);
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass()
    {
        return 0;
    }
    
    @Override
    public int damageDropped(int i)
    {
        return i;
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return 0;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1)
    {
        return new TileEntityWireBlock();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int x, int y, int z, int side)
    {
        return ((TileEntityConductorBase) par1IBlockAccess.getBlockTileEntity(x, y, z)).textureItemStack == null ? this.blockIcon : ((TileEntityConductorBase) par1IBlockAccess.getBlockTileEntity(x,
                y, z)).textureItemStack.getIconIndex();
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9))
            return true;

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityConductorBase)
        {
            TileEntityConductorBase te = (TileEntityConductorBase) tileEntity;
            if (player.inventory.getCurrentItem() != null && player.inventory.getCurrentItem().getItem() instanceof ItemBlock)
            {
                if (!te.mode && player.inventory.getCurrentItem().itemID != this.blockID && Block.isNormalCube(player.inventory.getCurrentItem().itemID))
                {
                    ((TileEntityConductorBase) world.getBlockTileEntity(x, y, z)).textureItemStack = player.inventory.getCurrentItem();
                    world.markBlockForRenderUpdate(x, y, z);
                    return true;
                }
                else
                {
                    ((TileEntityConductorBase) world.getBlockTileEntity(x, y, z)).textureItemStack = null;
                    world.markBlockForRenderUpdate(x, y, z);
                    return true;
                }
            }
            else
            {
                if (player.isSneaking())
                {
                    te.mode = !te.mode;
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon(ElectricExpansion.PREFIX + "CamoWire");
    }
}

package electricexpansion.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemStack;
import universalelectricity.core.item.ItemElectric;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electricexpansion.common.ElectricExpansion;
import electricexpansion.common.misc.EETab;

public class ItemUltimateBattery extends ItemElectric
{
    public ItemUltimateBattery(int par1)
    {
        super(par1);
        this.setUnlocalizedName("UltimateBattery");
        this.setCreativeTab(EETab.INSTANCE);
    }
    
    @Override
    public float getMaxElectricityStored(ItemStack i)
    {
        return 5_000;
    }
    
    @Override
    public float getVoltage(ItemStack i)
    {
        return 0.075f;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon(this.getUnlocalizedName().replaceAll("item.", ElectricExpansion.PREFIX));
    }
}

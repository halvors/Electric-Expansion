package electricexpansion.common.items;

import universalelectricity.prefab.ItemElectric;
import universalelectricity.prefab.UETab;
import electricexpansion.common.ElectricExpansion;

public class ItemEliteBattery extends ItemElectric
{
	public ItemEliteBattery(int par1)
	{
		super(par1);
		this.iconIndex = 1;
		this.setItemName("EliteBattery");
		this.setCreativeTab(UETab.INSTANCE);
	}

	@Override
	public double getMaxJoules(Object... data)
	{
		return 750000;
	}

	@Override
	public boolean canProduceElectricity()
	{
		return true;
	}

	@Override
	public String getTextureFile()
	{
		return ElectricExpansion.MATT_ITEM_TEXTURE_FILE;
	}

	@Override
	public double getVoltage()
	{
		return 50;
	}
}

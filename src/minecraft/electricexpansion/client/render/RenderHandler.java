package electricexpansion.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electricexpansion.client.ClientProxy;
import electricexpansion.client.model.ModelTransformer;
import electricexpansion.client.model.ModelWireMill;
import electricexpansion.common.ElectricExpansion;

@SideOnly(Side.CLIENT)
public class RenderHandler implements ISimpleBlockRenderingHandler
{
	public ModelWireMill wireMill = new ModelWireMill();
	public ModelTransformer transformer = new ModelTransformer();

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		GL11.glPushMatrix();

		if (block.blockID == ElectricExpansion.blockWireMill.blockID)
		{
			GL11.glBindTexture(3553, FMLClientHandler.instance().getClient().renderEngine.getTexture(ElectricExpansion.ALEX_TEXTURES + "wiremill.png"));
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.5F, .8F, 0.5F);
			// GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1F, -1F, -1F);
			wireMill.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GL11.glPopMatrix();
		}
		if (block.blockID == ElectricExpansion.blockTransformer.blockID)
		{
			GL11.glBindTexture(3553, FMLClientHandler.instance().getClient().renderEngine.getTexture(ElectricExpansion.MattFILE_PATH + "transformer.png"));
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.5F, .8F, 0.5F);
			// GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			GL11.glScalef(1F, -1F, -1F);
			transformer.render(null, 0, 0, 0, 0, 0, 0.0625F);
			GL11.glPopMatrix();
		}

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ClientProxy.RENDER_ID;
	}

}
package xyz.periodic.extracreative;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by lukem on 10/5/2016.
 */
public class GuiHandler implements IGuiHandler {
    public static final int CREATE_ITEM_GUI = 0;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == CREATE_ITEM_GUI)
                return new CreateScreen();
        return null;
    }
}

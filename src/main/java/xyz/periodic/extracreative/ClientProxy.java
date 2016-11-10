package xyz.periodic.extracreative;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.periodic.extracreative.KeyHandler;
import xyz.periodic.extracreative.ExtraCreative;

/**
 * Created by lukem on 9/28/2016.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Mod.Instance
    public static ExtraCreative instance;
    @Override
    public void load() {
        super.load();
        FMLCommonHandler.instance().bus().register(KeyHandler.instance);
        NetworkRegistry.INSTANCE.registerGuiHandler(ExtraCreative.instance, new GuiHandler());
    }
}

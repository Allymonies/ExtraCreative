package xyz.periodic.extracreative;

/**
 * Created by lukem on 9/28/2016.
 */
import net.minecraft.init.Blocks;
import net.minecraft.creativetab.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = ExtraCreative.MODID, version = ExtraCreative.VERSION)
public class ExtraCreative
{
    public static final String MODID = "ExtraCreative";
    public static final String VERSION = "1.23";

    @Mod.Instance(MODID)
    public static ExtraCreative instance;

    @SidedProxy(clientSide = "xyz.periodic.extracreative.ClientProxy", serverSide = "xyz.periodic.extracreative.CommonProxy")
    public static CommonProxy proxy;

    public static final Logger logger = LogManager.getLogger(MODID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.info("Beginning pre-initialization!");
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("ExtraCreative v" + VERSION + " Initializing!");
        proxy.load();
    }
    public static Logger getLogger() {
        return logger;
    }
}
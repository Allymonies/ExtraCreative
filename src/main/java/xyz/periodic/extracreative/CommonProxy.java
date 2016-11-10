package xyz.periodic.extracreative;

import java.text.DecimalFormat;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

    private static final DecimalFormat FORMAT = new DecimalFormat("########0.000");

    protected long serverTickCount = 0;
    protected long clientTickCount = 0;
    protected final TickTimer tickTimer = new TickTimer();

    public CommonProxy() {
    }

    public World getClientWorld() {
        return null;
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public double getReachDistanceForPlayer(EntityPlayer entityPlayer) {
        return 5;
    }

    public void loadIcons() {
        ;
    }

    public void load() {
        FMLCommonHandler.instance().bus().register(tickTimer);
    }

    public long getTickCount() {
        return serverTickCount;
    }

    public boolean isNeiInstalled() {
        return false;
    }


    protected void onServerTick() {
        ++serverTickCount;
    }

    protected void onClientTick() {
    }

    public final class TickTimer {

        @SubscribeEvent
        public void onTick(ServerTickEvent evt) {
            if(evt.phase == Phase.END) {
                onServerTick();
            }
        }

        @SubscribeEvent
        public void onTick(ClientTickEvent evt) {
            if(evt.phase == Phase.END) {
                onClientTick();
            }
        }
    }
}
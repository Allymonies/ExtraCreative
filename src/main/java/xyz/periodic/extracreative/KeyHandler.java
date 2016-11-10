package xyz.periodic.extracreative;

/**
 * Created by lukem on 9/28/2016.
 */
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

import org.lwjgl.input.Keyboard;

import static xyz.periodic.extracreative.ExtraCreative.getLogger;

public class KeyHandler
{
    public static final KeyHandler instance = new KeyHandler();
    private final KeyBinding createKey;


    public KeyHandler() {
        createKey = new KeyBinding("CreateItem", Keyboard.KEY_I, "ExtraCreative");
        ClientRegistry.registerKeyBinding(createKey);
        getLogger().info("Registered createKey");
    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        handleCreate();
    }

    private void handleCreate() {
        if(createKey.isPressed()) {
            EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
            CreateItem.createGUI(player);
        }
    }
}
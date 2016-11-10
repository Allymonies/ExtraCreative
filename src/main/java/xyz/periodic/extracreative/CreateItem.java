package xyz.periodic.extracreative;

/**
 * Created by lukem on 10/5/2016.
 */
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTException;
import net.minecraft.util.EnumHand;
import xyz.periodic.extracreative.ExtraCreative;

import static net.minecraft.command.CommandBase.getItemByText;
import static xyz.periodic.extracreative.ExtraCreative.getLogger;
import xyz.periodic.extracreative.GuiHandler;

import java.io.IOException;

public class CreateItem {

    public static void createGUI(EntityPlayer player) {
        //getLogger().info("createKey pressed!");
        player.openGui(ExtraCreative.instance, GuiHandler.CREATE_ITEM_GUI, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
    }
    public static void create(String itemID, int count, int damage, String nbtData, int slot) throws NBTException, NumberInvalidException {
        EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().thePlayer;
        if (damage < 0) {
            damage = 0;
        }
        if (player.capabilities.isCreativeMode == true) {
            Item item;
            try
            {
                item = getItemByText(player, itemID);
            }
            catch (NumberInvalidException numberinvalidexception)
            {
                if (Block.getBlockFromName(itemID) != Blocks.AIR)
                {
                    throw numberinvalidexception;
                }

                item = null;
            }
            ItemStack itemstack = new ItemStack(item, count, damage);
            if (nbtData != "{}") {
                String section = "\u00A7";
                getLogger().info("Pre-Parsed NBT is " + nbtData);
                nbtData = nbtData.replace("\\&","`OVERRIDE`");
                nbtData = nbtData.replace("&", section);
                nbtData = nbtData.replace("`OVERRIDE`", "&");
                getLogger().info("Parsed NBT is " + nbtData);
                try {
                    itemstack.setTagCompound(JsonToNBT.getTagFromJson(nbtData));
                } catch (NBTException nbtexception) {
                    getLogger().error("NBT Parsing failed!");
                }
            }
            if (itemstack.getItem() == null)
            {
                itemstack = null;
            }
            if (itemstack != null) {
                getLogger().info("Creating " + String.valueOf(count) + " " + itemID + ":" + String.valueOf(damage) + " in slot " + String.valueOf(slot));
                player.inventory.setInventorySlotContents(slot, itemstack);
                Minecraft.getMinecraft().playerController.sendSlotPacket(itemstack, slot);
            }
        } else {
            getLogger().info("User attempted to summon item without Creative; aborting.");
        }
    }
}

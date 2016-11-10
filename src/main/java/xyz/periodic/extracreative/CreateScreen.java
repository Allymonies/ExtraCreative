package xyz.periodic.extracreative;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.command.NumberInvalidException;
import net.minecraft.nbt.NBTException;
import org.lwjgl.opengl.GL11;
import java.io.IOException;

import static java.lang.Integer.*;
import static xyz.periodic.extracreative.ExtraCreative.getLogger;
import xyz.periodic.extracreative.CreateItem;

/**
 * Created by lukem on 10/5/2016.
 */
public class CreateScreen extends GuiScreen {
    GuiButton a;
    GuiTextField itemField;
    GuiTextField countField;
    GuiTextField damageField;
    GuiTextField nbtField;
    GuiTextField slotField;
    String item = "";
    Integer count = 0;
    Integer damage = 0;
    String nbtData = "{}";
    Integer slot = 0;
    public static int stringToInt(String param) {
        try {
            return Integer.valueOf(param);
        } catch(NumberFormatException e) {
            return -1;
        }
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        itemField.drawTextBox();
        countField.drawTextBox();
        damageField.drawTextBox();
        nbtField.drawTextBox();
        slotField.drawTextBox();
        drawString(fontRendererObj, "Item ID:", this.width / 2 - 200, this.height / 2 - 100, 0xFFFFFF);
        drawString(fontRendererObj, "Count:", this.width / 2 - 200, this.height /2 - 60, 0xFFFFFF);
        drawString(fontRendererObj, "Damage:", this.width / 2 - 200, this.height / 2 - 30, 0xFFFFFF);
        drawString(fontRendererObj, "NBT Data:", this.width / 2 - 200, this.height / 2, 0xFFFFFF);
        drawString(fontRendererObj, "Slot:", this.width / 2 - 200, this.height / 2 + 30, 0xFFFFFF);
        //drawString(fontRendererObj, item, )
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }
    @Override
    public void initGui() {
        buttonList.clear();
        this.buttonList.add(this.a = new GuiButton(0, this.width / 2 - 100, this.height / 2 + 60, "Create Item"));
        itemField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, this.height / 2 - 100, 200, 20);
        itemField.setFocused(false);
        itemField.setMaxStringLength(4096);
        countField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, this.height / 2 - 60, 200, 20);
        countField.setFocused(false);
        countField.setMaxStringLength(2);
        damageField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, this.height / 2 - 30, 200, 20);
        damageField.setFocused(false);
        damageField.setMaxStringLength(8);
        nbtField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, this.height / 2, 200, 20);
        nbtField.setFocused(false);
        nbtField.setMaxStringLength(65535);
        slotField = new GuiTextField(0, fontRendererObj, this.width / 2 - 100, this.height / 2 + 30, 20, 20);
        slotField.setFocused(false);
        slotField.setMaxStringLength(4);
    }
    @Override
    public void keyTyped(char c, int i) throws IOException {
        super.keyTyped(c,i);
        if (itemField.isFocused()) {
            itemField.textboxKeyTyped(c, i);
        } else if (countField.isFocused()) {
            countField.textboxKeyTyped(c,i);
        } else if (damageField.isFocused()) {
            damageField.textboxKeyTyped(c,i);
        } else if (nbtField.isFocused()) {
            nbtField.textboxKeyTyped(c,i);
        } else if (slotField.isFocused()) {
            slotField.textboxKeyTyped(c,i);
        }
    }
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        itemField.mouseClicked(mouseX, mouseY, mouseButton);
        countField.mouseClicked(mouseX,mouseY, mouseButton);
        damageField.mouseClicked(mouseX, mouseY, mouseButton);
        nbtField.mouseClicked(mouseX, mouseY, mouseButton);
        slotField.mouseClicked(mouseX, mouseY, mouseButton);
    }
    @Override
    public void updateScreen() {
        item = itemField.getText();
        count = stringToInt(countField.getText());
        damage = stringToInt(damageField.getText());
        nbtData = nbtField.getText();
        slot = stringToInt(slotField.getText());
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button == this.a) {
            getLogger().info("Create Item Button Pressed!");
            try {
                CreateItem.create(item, count, damage, nbtData, slot);
            } catch (NBTException e) {
                getLogger().error("Received an NBT Error!");
            } catch (NumberInvalidException e) {
                getLogger().error("Received a NumberInvalid Error!");
            }
            this.mc.displayGuiScreen(null);
            if(this.mc.currentScreen == null) {
                this.mc.setIngameFocus();
            }
        }
    }
}
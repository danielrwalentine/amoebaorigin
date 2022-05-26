package me.dekuscrub.amoebaorigin;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.wallentines.midnightlib.config.ConfigSection;
import org.wallentines.midnightlib.config.FileConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigScreen implements ModMenuApi {

    private static final FileConfig config = FileConfig.findOrCreate("amoeba_origin", new File("config"), new ConfigSection().with("minecraft:dolphin", "origins:merling").with("minecraft:phantom", "origins:phantom").with("minecraft:wither_skeleton", "amoeba_origin:wither_skeleton").with("minecraft:piglin_brute", "amoeba_origin:piglin").with("minecraft:endermite", "origins:enderian").with("minecraft:bee", "origins:elytrian").with("minecraft:cow", "amoeba_origin:cow").with("minecraft:spider", "origins:arachnid").with("minecraft:cod", "origins:merling").with("minecraft:hoglin", "amoeba_origin:pig").with("minecraft:blaze", "origins:blazeborn").with("minecraft:piglin", "amoeba_origin:piglin").with("minecraft:chicken", "origins:avian").with("minecraft:parrot", "origins:elytrian").with("minecraft:ender_dragon", "origins:elytrian").with("minecraft:salmon", "origins:merling").with("minecraft:skeleton_horse", "amoeba_origin:skeleton").with("minecraft:elder_guardian", "origins:merling").with("minecraft:strider", "amoeba_origin:strider").with("minecraft:giant", "amoeba_origin:zombie").with("minecraft:zombie_villager", "amoeba_origin:zombie").with("minecraft:guardian", "origins:merling").with("minecraft:husk", "amoeba_origin:zombie").with("minecraft:cat", "origins:feline").with("minecraft:zombie_horse", "amoeba_origin:zombie").with("minecraft:tropical_fish", "origins:merling").with("minecraft:witch", "amoeba_origin:witch").with("minecraft:wither", "amoeba_origin:wither_skeleton").with("minecraft:zoglin", "amoeba_origin:zombie").with("minecraft:creeper", "amoeba_origin:creeper").with("minecraft:stray", "amoeba_origin:skeleton").with("minecraft:pufferfish", "origins:merling").with("minecraft:shulker", "origins;shulk").with("minecraft:cave_spider", "origins:arachnid").with("minecraft:ocelot", "origins:feline").with("minecraft:vex", "origins:elytrian").with("minecraft:pig", "amoeba_origin:pig").with("minecraft:zombified_piglin", "amoeba_origin:zombie").with("minecraft:drowned", "amoeba_origin:zombie").with("minecraft:ghast", "amoeba_origin:ghast").with("minecraft:axolotl", "origins:merling").with("minecraft:mooshroom", "amoeba_origin:mooshroom").with("minecraft:glow_squid", "origins:merling").with("minecraft:enderman", "origins:enderian").with("minecraft:squid", "origins:merling"));
    private static final ConfigBuilder builder = ConfigBuilder.create().setTitle(Text.of("AmoebaOrigin Config"));
    private static final ConfigCategory mob_list = builder.getOrCreateCategory(new TranslatableText("Moblist"));
    private static final ConfigEntryBuilder entryBuilder = builder.entryBuilder();
    private static final List<String> valueList = new ArrayList<>();

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> makeConfigScreen();
    }

    static Screen makeConfigScreen() {
        Amoebaorigin.updateConfig();
        builder.setSavingRunnable(() -> {
            for (int i = 0; i < config.getRoot().getEntries().keySet().size(); i++) {
                if (!Amoebaorigin.badEntityList.contains(config.getRoot().getEntries().keySet().stream().toList().get(i))) {
                    config.getRoot().set(config.getRoot().getEntries().keySet().stream().toList().get(i), valueList.get(i));
                }
            }
            config.save();
            valueList.clear();
        });
        for (String key : config.getRoot().getEntries().keySet()) {
            mob_list.addEntry(entryBuilder.startStrField(new TranslatableText(key), config.getRoot().getString(key))
                    .setTooltip(new TranslatableText("modid:origin"))
                    .setSaveConsumer(valueList::add)
                    .build());
        }
        return builder.build();
    }
}

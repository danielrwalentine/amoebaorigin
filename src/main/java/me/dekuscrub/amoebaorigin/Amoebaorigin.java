package me.dekuscrub.amoebaorigin;

import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeReference;
import io.github.apace100.origins.component.OriginComponent;
import io.github.apace100.origins.origin.Origin;
import io.github.apace100.origins.origin.OriginLayer;
import io.github.apace100.origins.origin.OriginLayers;
import io.github.apace100.origins.origin.OriginRegistry;
import io.github.apace100.origins.registry.ModComponents;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.wallentines.midnightcore.api.text.MStyle;
import org.wallentines.midnightcore.api.text.MTextComponent;
import org.wallentines.midnightcore.api.text.MTranslateComponent;
import org.wallentines.midnightcore.fabric.player.FabricPlayer;
import org.wallentines.midnightlib.config.FileConfig;
import org.wallentines.midnightlib.math.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Amoebaorigin implements ModInitializer {

    private static final FileConfig config = FileConfig.findOrCreate("amoeba_origin", new File("config"));
    private static final ConfigBuilder builder = ConfigBuilder.create().setTitle(Text.of("AmoebaOrigin Config"));
    private static final ConfigCategory mob_list = builder.getOrCreateCategory(new TranslatableText("Moblist"));
    private static final ConfigEntryBuilder entryBuilder = builder.entryBuilder();
    private static final List<Identifier> entityList = Registry.ENTITY_TYPE.getIds().stream().toList();
    private static final List<String> valueList = new ArrayList<>();
    public static final PowerType<Power> MANIFEST = new PowerTypeReference<>(new Identifier("amoeba_origin", "manifest"));

    @Override
    public void onInitialize() {
    }

    static Screen makeConfigScreen() {
        builder.setSavingRunnable(() -> {
            for (int i = 0; i < entityList.size(); i++) {
                config.getRoot().set(entityList.get(i).toString(), valueList.get(i));
            }
            config.save();
            valueList.clear();
        });
        for (Identifier identifier : entityList) {
            String currentValue;
            if (!config.getRoot().has(identifier.toString())) {
                currentValue = "origins:human";
            } else {
                currentValue = config.getRoot().getString(identifier.toString());
            }
            mob_list.addEntry(entryBuilder.startStrField(new TranslatableText(identifier.toString()), currentValue)
                    .setTooltip(new TranslatableText("modid:origin"))
                    .setSaveConsumer(valueList::add)
                    .build());
        }
        return builder.build();
    }

    public static void resetOrigin(ServerPlayerEntity player) {
        if (MANIFEST.isActive(player)) {
            ModComponents.ORIGIN.get(player).setOrigin(OriginLayers.getLayer(Identifier.tryParse("origins:origin")), OriginRegistry.get(Identifier.tryParse("origins:human")));
            OriginComponent.sync(player);
        }
    }

    public static void timer(ServerPlayerEntity player, Origin origin) {
        final Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int ticks = 0;
            @Override
            public void run() {
                if(ticks == 1500) {
                    timer.cancel();
                    resetOrigin(player);
                }
                ticks++;
                FabricPlayer fp = FabricPlayer.wrap(player);
                MTranslateComponent name = new MTranslateComponent(origin.getName().getKey());
                fp.sendActionBar(new MTextComponent("Manifest set to ").withChild(name).withChild(new MTextComponent(", you have ").withChild(new MTextComponent(String.valueOf(25-(ticks/60)))).withChild(new MTextComponent(" minutes"))).withStyle(new MStyle().withColor(Color.fromRGBI(6))));
            }
        };
        timer.schedule(task,
                0L, // How many milliseconds to wait before ticking the first time
                1000L // How many milliseconds between each timer tick
        );
    }

    public static void bitePlayer(ServerPlayerEntity player, Entity target) {
        OriginLayer layer = OriginLayers.getLayer(Identifier.tryParse("origins:origin"));
        OriginComponent component = ModComponents.ORIGIN.get(player);

        if (player.isPlayer()) {
            if (MANIFEST.isActive(player)) {
                if (player.getInventory().getMainHandStack() == ItemStack.EMPTY) {
                    if (Math.random() <= 0.2) {
                        Origin origin;
                        if (target.isPlayer()) {
                            origin = ModComponents.ORIGIN.get(target).getOrigin(layer);
                        } else {
                            origin = OriginRegistry.get(Identifier.tryParse(config.getRoot().getString(Registry.ENTITY_TYPE.getId(target.getType()).toString())));
                        }
                        component.setOrigin(layer, origin);
                        timer(player, origin);
                        OriginComponent.sync(player);
                    }
                }
            }
        }
    }
}
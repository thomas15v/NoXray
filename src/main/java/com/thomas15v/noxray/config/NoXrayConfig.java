package com.thomas15v.noxray.config;

import com.thomas15v.noxray.NoXrayPlugin;
import com.thomas15v.noxray.api.BlockModifier;
import com.thomas15v.noxray.modifier.ModifierRegistry;
import com.thomas15v.noxray.modifier.modifiers.EmptyModifier;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class NoXrayConfig {
    private static final String USE_ORE_DICT_KEY = "UseOreDict";
    private static final String USE_ORE_DICT_COMMENT = "In case forge is detected, use the forgedict to add extra ores";
    @Setting(value = USE_ORE_DICT_KEY, comment = USE_ORE_DICT_COMMENT)
    private boolean useOreDict = true;

    private static final String MODIFIER = "modifier";
    private static final String MODIFIER_COMMENT = "Different modifiers are obvious, allhidden, random and veinrandom";
    @Setting(value = MODIFIER, comment = MODIFIER_COMMENT)
    private String modifier = "obvious";

    public boolean isUseOreDict() {
        return useOreDict;
    }

    public BlockModifier getModifier() {
        if (ModifierRegistry.exist(modifier)) {
            NoXrayPlugin.getInstance().getLogger().info("Using modifier: " + modifier);
            return ModifierRegistry.getModifier(modifier.toLowerCase());
        } else {
            NoXrayPlugin.getInstance().getLogger().warn("Modifier " + modifier + " is not valid, using empty modifier");
            return new EmptyModifier();
        }
    }
}
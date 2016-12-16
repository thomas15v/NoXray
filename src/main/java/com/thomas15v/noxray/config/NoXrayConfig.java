package com.thomas15v.noxray.config;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class NoXrayConfig {
    private static final String USE_ORE_DICT_KEY = "UseOreDict";
    private static final String USE_ORE_DICT_COMMENT = "In case forge is detected, use the forgedict to add extra ores";
    @Setting(value = USE_ORE_DICT_KEY, comment = USE_ORE_DICT_COMMENT)
    private boolean useOreDict = true;

    private static final String MODIFIER = "modifier";
    private static final String MODIFIER_COMMENT = "";
    @Setting(value = MODIFIER, comment = MODIFIER_COMMENT)
    private String caveEnabled = "obvious";



    public boolean isUseOreDict() {
        return useOreDict;
    }
}

package com.thomas15v.noxray.modifier;

import com.thomas15v.noxray.api.BlockModifier;
import com.thomas15v.noxray.modifier.modifiers.ObviousModifier;
import com.thomas15v.noxray.modifier.modifiers.RandomModifier;
import com.thomas15v.noxray.modifier.modifiers.VeinRandomModifier;

import java.util.HashMap;
import java.util.Map;

public class ModifierRegistry {

    private static Map<String, BlockModifier> modifierMap = new HashMap<>();

    static {
        try {
            registerModifier("obvious", new ObviousModifier());
            registerModifier("random", new RandomModifier());
            registerModifier("veinrandom", new VeinRandomModifier());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void registerModifier(String name, BlockModifier modifier) throws Exception {
        if (!modifierMap.containsKey(name)){
            modifierMap.put(name, modifier);
        } else {
            throw new Exception("Modifier Already Exists");
        }
    }

    public static BlockModifier getModifier(String name){
        return modifierMap.get(name);
    }

    public static boolean exist(String name){
        return modifierMap.containsKey(name);
    }

}

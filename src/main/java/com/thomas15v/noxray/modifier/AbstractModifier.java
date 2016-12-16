package com.thomas15v.noxray.modifier;


import com.thomas15v.noxray.api.BlockModifier;
import com.thomas15v.noxray.modifications.OreUtil;
import org.spongepowered.api.block.BlockState;

import java.util.function.Predicate;

public abstract class AbstractModifier implements BlockModifier {

    private static final Predicate<BlockState> FILTER = blockState -> OreUtil.isOre(blockState.getType());

    @Override
    public Predicate<BlockState> getFilter() {
        return FILTER;
    }
}

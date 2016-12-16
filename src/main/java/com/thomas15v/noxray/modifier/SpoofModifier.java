package com.thomas15v.noxray.modifier;


import com.thomas15v.noxray.api.BlockModifier;
import com.thomas15v.noxray.modifications.OreUtil;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;
import java.util.function.Predicate;

public class SpoofModifier implements BlockModifier {

    @Override
    public BlockState handleBlock(BlockState original, Location<World> location, List<BlockState> surroundingBlocks) {
        return OreUtil.isExposed(surroundingBlocks) ? original : OreUtil.getRandomOre().getDefaultState();
    }

    @Override
    public Predicate<BlockState> getFilter() {
        return blockState -> blockState.getType().equals(BlockTypes.STONE);
    }
}

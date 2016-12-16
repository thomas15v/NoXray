package com.thomas15v.noxray.modifier.modifiers;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.List;

public class VeinRandomModifier extends RandomModifier {

    @Override
    public BlockState handleBlock(BlockState original, Location<World> location, List<BlockState> surroundingBlocks) {
        return super.handleBlock(original, location, surroundingBlocks);
    }
}

package com.thomas15v.noxray.modifier.modifiers;

import com.thomas15v.noxray.modifications.OreUtil;
import com.thomas15v.noxray.modifier.AbstractModifier;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Arrays;
import java.util.List;

/**
 * Only modifies the blocks on disk read and so async. Causing almost no lag, but might increase length on the chunkloading (not that that is a problem really)
 */
public class ObviousModifier extends AbstractModifier {

    private static final List<BlockType> COMMON_BLOCKS = Arrays.asList(BlockTypes.AIR, BlockTypes.STONE, BlockTypes.NETHERRACK, BlockTypes.END_STONE, BlockTypes.BEDROCK);

    @Override
    public BlockState handleBlock(BlockState original, Location<World> location, List<BlockState> surroundingBlocks) {

        if (OreUtil.isExposed(surroundingBlocks)) {
            return original;
        } else {
            return BlockTypes.STONE.getDefaultState();
        }


    }

    public static boolean checkBlock(Location blockState, Direction direction) {
        return !blockState.getBlockRelative(direction).getBlock().getType().equals(BlockTypes.AIR);
    }
}

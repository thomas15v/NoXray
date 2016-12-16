package com.thomas15v.noxray.event;

import com.thomas15v.noxray.modifications.OreUtil;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.Piston;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.ChangeBlockEvent;
import org.spongepowered.api.event.block.InteractBlockEvent;
import org.spongepowered.api.event.world.ExplosionEvent;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerEventListener {

    private static List<BlockType> UPDATE_WHITELIST = Arrays.asList(BlockTypes.AIR, BlockTypes.PISTON,
            BlockTypes.PISTON_EXTENSION, BlockTypes.PISTON_HEAD, BlockTypes.STICKY_PISTON);

    @Listener
    public void onBlockUpdate(InteractBlockEvent event){
        //pre-block-update
        if (event.getCause().containsType(Player.class) && event.getTargetBlock().getLocation().isPresent()) {
            updateBlocks(event.getTargetBlock().getLocation().get());
        }
    }

    @Listener
    public void onBlockUpdate(ChangeBlockEvent.Break event){
        event.getTransactions().forEach(transaction -> {
            if (!UPDATE_WHITELIST.contains(transaction.getFinal().getState().getType())){
                updateBlocks(transaction.getFinal().getLocation().get());
            }
        });
    }

    @Listener
    public void onExplosion(ExplosionEvent.Detonate event){
        for (Location<World> location : event.getAffectedLocations()){
            for (Direction direction : OreUtil.getOrdalDirections()){
                Location sideBlock = location.getBlockRelative(direction);
                if (!event.getAffectedLocations().contains(sideBlock)) {
                    updateBlock(sideBlock);
                }
            }
        }
    }

    private static void updateBlocks(Location<World> location){
        for (Direction direction : OreUtil.getOrdalDirections()){
            updateBlock(location.getBlockRelative(direction));
        }
    }

    private static void updateBlock(Location<World> block){
        if (block.getBlockType() == BlockTypes.AIR)
            return;
        block.getExtent().sendBlockChange(block.getPosition().toInt(), block.getBlock());
    }


}

package com.thomas15v.noxray.modifications;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.OreDictionary;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.property.block.FullBlockSelectionBoxProperty;
import org.spongepowered.api.util.Direction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OreUtil {

    private static List<BlockType> Ores = new ArrayList<>();
    private final static Direction[] ordalDirections = new Direction[]{Direction.EAST,
            Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.DOWN, Direction.UP};

    static {
        addOre(BlockTypes.REDSTONE_ORE);
        addOre(BlockTypes.MONSTER_EGG);
        addOre(BlockTypes.EMERALD_ORE);
        addOre(BlockTypes.DIAMOND_ORE);
        addOre(BlockTypes.COAL_ORE);
        addOre(BlockTypes.IRON_ORE);
        addOre(BlockTypes.LAPIS_ORE);
        addOre(BlockTypes.GOLD_ORE);
        //other ores are covered by BlockOre class
    }

    public static boolean isOre(BlockType blockState){
        return blockState instanceof BlockOre | Ores.contains(blockState);
    }

    public static void addOre(BlockType blockType){
        if (!Ores.contains(blockType)) {
            Ores.add(blockType);
        }
    }


    public static void registerForgeOres(){
        for (String s : OreDictionary.getOreNames()) {
            if (s.contains("ore")){
                OreDictionary.getOres(s).stream().filter(ore -> ore.getItem() instanceof ItemBlock).forEach(ore -> {
                    Block block = ((ItemBlock) ore.getItem()).getBlock();
                    if (!(block instanceof BlockOre)) {
                        OreUtil.addOre((BlockType) block);
                    }
                });
            }
        }
    }

    public static BlockType getRandomOre(){
        return Ores.get(ThreadLocalRandom.current().nextInt(0, Ores.size()));
    }

    public static boolean isExposed(List<BlockState> surroundingBlocks){
        return surroundingBlocks.stream().anyMatch(blockState -> !blockState.getType().getProperty(FullBlockSelectionBoxProperty.class).get().getValue());
    }

    public static Direction[] getOrdalDirections() {
        return ordalDirections;
    }
}

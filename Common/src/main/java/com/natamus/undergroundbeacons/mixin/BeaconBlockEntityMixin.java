package com.natamus.undergroundbeacons.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = BeaconBlockEntity.class, priority = 1001)
public class BeaconBlockEntityMixin {
    @ModifyVariable(method = "tick", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/world/level/Level;getHeight(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I"), ordinal = 3)
    private static int tick_l(int l, Level level, BlockPos pos, BlockState state, BeaconBlockEntity blockEntity) {
        return level.getMaxY();
    }

    @ModifyVariable(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getLightBlock()I"), ordinal = 1)
    private static BlockState tick_blockState(BlockState blockState, Level level, BlockPos pos, BlockState state, BeaconBlockEntity blockEntity) {
        return Blocks.BEDROCK.defaultBlockState();
    }
}
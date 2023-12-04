package eu.midnightdust.motschen.rocks.block.blockentity;

import eu.midnightdust.motschen.rocks.block.OverworldGeyser;
import eu.midnightdust.motschen.rocks.config.RocksConfig;
import eu.midnightdust.motschen.rocks.registry.RocksBlockEntities;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OverworldGeyserBlockEntity extends BlockEntity {
	public int countdown = 0;

	public OverworldGeyserBlockEntity(BlockPos pos, BlockState state) {
		super(RocksBlockEntities.OVERWORLD_GEYSER_BE.get(), pos, state);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, OverworldGeyserBlockEntity blockEntity) {
		assert level != null;
		if (level.getBlockState(pos).is(RocksRegistry.GEYSER.get())) {
			Player player = level.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3, true);
			Player player2 = level.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8, true);

			if (RocksConfig.COMMON.geyserLevitation.get() && player2 != null && player2.getY() >= pos.getY() &&
					(pos.getX() <= player2.getX() && pos.getX() + 1 >= player2.getX()) &&
					(pos.getZ() <= player2.getZ() && pos.getZ() + 1 >= player2.getZ())) {
				player2.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 2, 10, true, false, false));
			}

			if (player != null) {
				level.setBlockAndUpdate(pos, state.setValue(OverworldGeyser.ACTIVE, true));
				if (level.getBlockState(pos.below()).getBlock() instanceof GrassBlock)
					level.setBlockAndUpdate(pos.below(), level.getBlockState(pos.below()).setValue(GrassBlock.SNOWY, true));
				blockEntity.countdown = 1000;
			} else {
				if (blockEntity.countdown > 0) {
					blockEntity.countdown--;
				} else {
					level.setBlockAndUpdate(pos, state.setValue(OverworldGeyser.ACTIVE, false));
				}
			}

			if (state.getValue(OverworldGeyser.ACTIVE)) {
				level.addParticle(ParticleTypes.SPIT, pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, 0, 1.0, 0);
				level.addParticle(ParticleTypes.SPIT, pos.getX() + 0.5, pos.getY() + 0.3, pos.getZ() + 0.5, 0, 1.0, 0);
				level.addParticle(ParticleTypes.SPLASH, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, -0.01, 1.5, -0.01);
			}
		}
	}
}

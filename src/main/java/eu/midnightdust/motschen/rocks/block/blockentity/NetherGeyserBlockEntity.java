package eu.midnightdust.motschen.rocks.block.blockentity;

import eu.midnightdust.motschen.rocks.block.NetherGeyser;
import eu.midnightdust.motschen.rocks.registry.RocksBlockEntities;
import eu.midnightdust.motschen.rocks.registry.RocksRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NetherGeyserBlockEntity extends BlockEntity {
    private int countdown = 0;

    public NetherGeyserBlockEntity(BlockPos pos, BlockState state) {
        super(RocksBlockEntities.NETHER_GEYSER_BE.get(), pos, state);
    }

    public static void tick(Level world, BlockPos pos, BlockState state, NetherGeyserBlockEntity blockEntity) {
        assert world != null;
        if (world.getBlockState(pos).getBlock() == RocksRegistry.NETHER_GEYSER.get()) {
            Player player = world.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 3, true);
            Player player2 = world.getNearestPlayer(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 1, true);


            if (player != null) {
                world.setBlockAndUpdate(pos, state.setValue(NetherGeyser.ACTIVE, true));

                player.hurt(DamageSource.ON_FIRE, 1);
                if (player2 != null) {
                    player2.hurt(DamageSource.ON_FIRE, 4);
                }
                blockEntity.countdown = 1000;
            } else {
                if (blockEntity.countdown > 0) {
                    blockEntity.countdown = blockEntity.countdown - 1;
                }
                if (blockEntity.countdown == 0) {
                    world.setBlockAndUpdate(pos, state.setValue(NetherGeyser.ACTIVE, false));
                }
            }

            if (state.getValue(NetherGeyser.ACTIVE)) {
                world.addParticle(ParticleTypes.LAVA, pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, 1, 1.5, 1);
                world.addParticle(ParticleTypes.LAVA, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 1, 1.5, 1);
                world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 0.1, pos.getZ() + 0.5, 0, 0.3, 0);
            }
        }
    }
}

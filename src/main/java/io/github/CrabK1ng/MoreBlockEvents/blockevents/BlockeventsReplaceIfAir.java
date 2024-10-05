package io.github.CrabK1ng.MoreBlockEvents.blockevents;

import com.github.puzzle.game.block.PuzzleBlockAction;
import finalforeach.cosmicreach.blockevents.BlockEventTrigger;
import finalforeach.cosmicreach.blockevents.actions.ActionId;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import static io.github.CrabK1ng.MoreBlockEvents.Constants.MOD_ID;

@ActionId(
        id =  MOD_ID+":ReplaceIfAir"
)
public class BlockeventsReplaceIfAir extends PuzzleBlockAction {

    String blockStateId;
    int xOff;
    int yOff;
    int zOff;

    @Override
    protected void act(BlockState srcBlockState, Zone zone, @Nullable BlockPosition sourcePos, Map<String, Object> map) {
        this.blockStateId = getParameters().get("blockStateId").asString();
        this.xOff = getParameters().get("xOff").asInt();
        this.yOff = getParameters().get("yOff").asInt();
        this.zOff = getParameters().get("zOff").asInt();

        BlockState blockState;
        if ("self".equals(this.blockStateId)) {
            blockState = srcBlockState;
        } else {
            blockState = BlockState.getInstance(this.blockStateId);
        }

        BlockPosition bp = sourcePos.getOffsetBlockPos(zone, this.xOff, this.yOff, this.zOff);
        if (bp != null) {
            if (bp.getBlockState() == BlockState.getInstance("base:air[default]")){
                BlockSetter.get().replaceBlock(zone, blockState, bp);

                BlockState targetBlockState = sourcePos.getBlockState();
                if (targetBlockState == null) {
                    return;
                }

                BlockEventTrigger[] customTrigger = targetBlockState.getTrigger("onReplace");
                if (customTrigger == null) {
                    return;
                }

                Map<String, Object> args = new HashMap();
                args.put("blockPos", sourcePos);
                BlockEventTrigger[] var8 = customTrigger;
                int var9 = customTrigger.length;

                for(int var10 = 0; var10 < var9; ++var10) {
                    BlockEventTrigger t = var8[var10];
                    t.act(targetBlockState, zone, args);
                }
            }
        }



    }
}

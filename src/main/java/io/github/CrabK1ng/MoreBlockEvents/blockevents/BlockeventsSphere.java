package io.github.CrabK1ng.MoreBlockEvents.blockevents;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Queue;
import com.github.puzzle.game.block.PuzzleBlockAction;
import finalforeach.cosmicreach.blockevents.BlockEventTrigger;
import finalforeach.cosmicreach.blockevents.actions.ActionId;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;
import io.github.CrabK1ng.MoreBlockEvents.Constants;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static io.github.CrabK1ng.MoreBlockEvents.Constants.MOD_ID;

@ActionId(
        id =  MOD_ID+":Sphere"
)
public class BlockeventsSphere extends PuzzleBlockAction {

    public String blockStateId = "base:air[default]";
    public int xOff;
    public int yOff;
    public int zOff;
    public float radius;

    @Override
    protected void act(BlockState srcBlockState, Zone zone, @Nullable BlockPosition sourcePos, Map<String, Object> map) {
        this.blockStateId = getParameters().get("blockStateId").asString();
        this.radius = getParameters().get("radius").asFloat();
        this.xOff = getParameters().get("xOff").asInt();
        this.yOff = getParameters().get("yOff").asInt();
        this.zOff = getParameters().get("zOff").asInt();

        float radiusSq = this.radius * this.radius;
        Queue<BlockPosition> explodeQueue = new Queue();

        for(float i = -this.radius; i <= this.radius; ++i) {
            for(float j = -this.radius; j <= this.radius; ++j) {
                for(float k = -this.radius; k <= this.radius; ++k) {
                    float workingRadiusSq = Vector3.len2(i, j, k);
                    if (workingRadiusSq <= radiusSq) {
                        BlockPosition pos = sourcePos.getOffsetBlockPos(zone, (int)((float)this.xOff + i), (int)((float)this.yOff + j), (int)((float)this.zOff + k));
                        if (pos != null) {
                            if (pos.getBlockState() == BlockState.getInstance("base:air[default]")){
                                explodeQueue.addLast(pos);
                            }
                        }
                    }
                }
            }
        }

        BlockState blockState;
        if ("self".equals(this.blockStateId)) {
            blockState = srcBlockState;
        } else {
            blockState = BlockState.getInstance(this.blockStateId);
        }

        Map<String, Object> newArgs = new HashMap();
        Iterator var19 = explodeQueue.iterator();

        while(true) {
            BlockEventTrigger[] explode;
            BlockPosition explodedPos;
            BlockState explodedBlock;
            do {
                do {
                    do {
                        if (!var19.hasNext()) {
                            BlockSetter.get().replaceBlocks(zone, blockState, explodeQueue);
                            return;
                        }

                        explodedPos = (BlockPosition)var19.next();
                    } while(explodedPos.equals(sourcePos));

                    explodedBlock = explodedPos.getBlockState();
                } while(explodedBlock == null);

                explode = explodedBlock.getTrigger("onExplode");
            } while(explode == null);

            BlockEventTrigger[] var13 = explode;
            int var14 = explode.length;

            for(int var15 = 0; var15 < var14; ++var15) {
                BlockEventTrigger e = var13[var15];
                newArgs.put("blockPos", explodedPos);
                e.act(explodedBlock, zone, newArgs);
            }
        }
    }
}

package io.github.CrabK1ng.MoreBlockEvents.blockevents;

import com.github.puzzle.game.block.PuzzleBlockAction;
import finalforeach.cosmicreach.ClientSingletons;
import finalforeach.cosmicreach.blockevents.BlockEventTrigger;
import finalforeach.cosmicreach.blockevents.actions.ActionId;
import finalforeach.cosmicreach.blocks.BlockPosition;
import finalforeach.cosmicreach.blocks.BlockState;
import finalforeach.cosmicreach.chat.Chat;
import finalforeach.cosmicreach.gamestates.InGame;
import finalforeach.cosmicreach.world.BlockSetter;
import finalforeach.cosmicreach.world.Zone;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

import static io.github.CrabK1ng.MoreBlockEvents.Constants.MOD_ID;

@ActionId(
        id =  MOD_ID+":Chat"
)
public class BlockeventsChat extends PuzzleBlockAction {

    String chatMessage;

    @Override
    protected void act(BlockState srcBlockState, Zone zone, @Nullable BlockPosition sourcePos, Map<String, Object> map) {
        this.chatMessage = getParameters().get("chatMessage").asString();

        Chat chat = Chat.MAIN_CHAT;
        chat.sendMessageOrCommand(InGame.world, InGame.getLocalPlayer(), ClientSingletons.account, this.chatMessage);

    }
}

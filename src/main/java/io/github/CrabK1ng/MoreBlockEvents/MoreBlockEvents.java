package io.github.CrabK1ng.MoreBlockEvents;

import com.github.puzzle.core.PuzzleRegistries;
import com.github.puzzle.game.block.DataModBlock;
import com.github.puzzle.game.events.OnRegisterBlockEvent;
import com.github.puzzle.loader.entrypoint.interfaces.ModInitializer;
import finalforeach.cosmicreach.util.Identifier;
import io.github.CrabK1ng.MoreBlockEvents.blockevents.BlockeventsChat;
import io.github.CrabK1ng.MoreBlockEvents.blockevents.BlockeventsReplaceIf;
import io.github.CrabK1ng.MoreBlockEvents.blockevents.BlockeventsReplaceIfAir;
import io.github.CrabK1ng.MoreBlockEvents.blockevents.BlockeventsSphere;
import io.github.CrabK1ng.MoreBlockEvents.commands.Commands;
import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

import static com.github.puzzle.core.resources.PuzzleGameAssetLoader.locateAsset;
import static finalforeach.cosmicreach.blockevents.BlockEvents.loadBlockEventsFromAsset;
import static finalforeach.cosmicreach.blockevents.BlockEvents.registerBlockEventAction;

public class MoreBlockEvents implements ModInitializer {

    @Override
    public void onInit() {
        PuzzleRegistries.EVENT_BUS.register(this);

        Constants.LOGGER.info("Hello From INIT");

        Commands.register();

        Constants.LOGGER.info("registerBlockEvent");
        registerBlockEventAction(BlockeventsSphere.class);
        registerBlockEventAction(BlockeventsReplaceIfAir.class);
        registerBlockEventAction(BlockeventsReplaceIf.class);
        registerBlockEventAction(BlockeventsChat.class);
    }

    @Subscribe
    public void onEvent(OnRegisterBlockEvent event) {
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "test_block.json")));
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "test_block2.json")));
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "test_block3.json")));
        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "test_blockchat.json")));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/block_events_c4or.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/block_events_c4or2.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/block_events_c4or3.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/block_events_chat.json"))));

        event.registerBlock(() -> new DataModBlock(Identifier.of(Constants.MOD_ID, "block_cupboard.json")));


        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/east_cupboard_closed.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/east_cupboard_closed_door.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/east_cupboard_open.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/north_cupboard_closed.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/north_cupboard_closed_door.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/north_cupboard_open.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/south_cupboard_closed.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/south_cupboard_closed_door.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/south_cupboard_open.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/west_cupboard_closed.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/west_cupboard_closed_door.json"))));
        loadBlockEventsFromAsset(Objects.requireNonNull(locateAsset(Identifier.of(Constants.MOD_ID, "block_events/cupboard/west_cupboard_open.json"))));
    }
}

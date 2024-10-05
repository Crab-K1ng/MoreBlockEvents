package io.github.CrabK1ng.MoreBlockEvents;

import com.github.puzzle.core.PuzzleRegistries;
import com.github.puzzle.loader.entrypoint.interfaces.PreModInitializer;

public class MoreBlockEventsPreinit implements PreModInitializer {

    @Override
    public void onPreInit() {
        Constants.LOGGER.info("Hello From PRE-INIT");
    }
}

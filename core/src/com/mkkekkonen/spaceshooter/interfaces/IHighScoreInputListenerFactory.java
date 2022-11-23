package com.mkkekkonen.spaceshooter.interfaces;

import com.mkkekkonen.spaceshooter.highscores.HighScoreInputListener;

import dagger.assisted.AssistedFactory;

@AssistedFactory
public interface IHighScoreInputListenerFactory {
    HighScoreInputListener createHighScoreInputListener(Integer score);
}

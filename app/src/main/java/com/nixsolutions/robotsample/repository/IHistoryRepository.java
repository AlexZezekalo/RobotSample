package com.nixsolutions.robotsample.repository;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.interaction.Interaction;

import java.util.List;

public interface IHistoryRepository {

    void addInteraction(@NonNull Interaction interaction);

    Interaction getInteractionById(@NonNull String interactionId);

    @NonNull
    List<Interaction> getAllInteractions();

}

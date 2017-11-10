package com.nixsolutions.robotsample.repository;


import android.support.annotation.NonNull;

import com.nixsolutions.robotsample.interaction.Interaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockHistoryRepository implements IHistoryRepository {

    private Map<String, Interaction> interactionMap = new HashMap<>();

    @Override
    public void addInteraction(@NonNull Interaction interaction) {
        interactionMap.put(interaction.getInteractionId(), interaction);
    }

    @Override
    public Interaction getInteractionById(@NonNull String interactionId) {
        return interactionMap.get(interactionId);
    }

    @NonNull
    @Override
    public List<Interaction> getAllInteractions() {
        return new ArrayList<>(interactionMap.values());
    }
}

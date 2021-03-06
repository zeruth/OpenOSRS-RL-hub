package com.questhelper.quests.myarmsbigadventure;

import com.questhelper.questhelpers.QuestHelper;
import com.questhelper.requirements.ItemRequirement;
import com.questhelper.steps.ObjectStep;
import java.util.ArrayList;
import java.util.Collections;
import javax.inject.Inject;
import net.runelite.api.ItemID;
import net.runelite.api.NullObjectID;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;

public class AddDung extends ObjectStep
{
	@Inject
	EventBus eventBus;

	private final boolean hasSubscribed = false;

	ItemRequirement dung = new ItemRequirement("Ugthanki dung", ItemID.UGTHANKI_DUNG, 3);

	public AddDung(QuestHelper questHelper)
	{
		super(questHelper, NullObjectID.NULL_18867, new WorldPoint(2831, 3696, 0), "Add 3 ugthanki dung on My Arm's soil patch.");
		this.addIcon(ItemID.UGTHANKI_DUNG);
		dung.setTip("You can get some by feeding the camels in Pollvineach hot sauce, then using a bucket on their dung");
		dung.setHighlightInInventory(true);
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		updateSteps();
	}

	protected void updateSteps()
	{
		if (!hasSubscribed)
		{
			eventBus.subscribe(GameTick.class, this, this::onGameTick);
		}

		int numCompToAdd = 3 - client.getVarbitValue(2791);
		dung.setQuantity(numCompToAdd);
		this.setRequirements(new ArrayList<>(Collections.singletonList(dung)));
		this.setText("Add " + numCompToAdd + " ugthanki dung on My Arm's soil patch.");
	}
}


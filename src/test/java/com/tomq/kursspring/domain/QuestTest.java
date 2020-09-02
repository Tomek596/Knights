package com.tomq.kursspring.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuestTest {

    @Test
    public void settingStartedFlagToFalseShouldSetStartDate() {
        Quest quest = new Quest(1, "Zadanie testowe");

        quest.setStarted(true);

        assertNotNull(quest.startDate);
    }

    @Test
    public void questShouldBeCompleted() {
        Quest quest = new Quest(1, "Zadanie testowe");
        quest.setStarted(true);
        quest.lengthInSeconds = -60;
        assertTrue(quest.isCompleted());
        assertTrue(quest.isCompleted());
    }

    @Test
    public void questShouldBeNotCompleted() {
        Quest quest = new Quest(1, "Zadanie testowe");
        quest.setStarted(true);
        quest.lengthInSeconds = 2000;
        assertFalse(quest.isCompleted());
        assertFalse(quest.isCompleted());
    }

}

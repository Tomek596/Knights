package com.tomq.kursspring.services;

import com.tomq.kursspring.domain.Quest;
import com.tomq.kursspring.domain.repository.QuestRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestServiceTest {

    @Mock
    QuestRepository questRepositoryMock;

    @Test
    public void returnsNotStartedQuests() {

        List<Quest> quests = new ArrayList<>();
        Quest q1 = new Quest(1, "test quest1");
        Quest q2 = new Quest(2, "test quest2");
        Quest q3 = new Quest(3, "test quest3");

        q1.setStarted(true);

        quests.add(q1);
        quests.add(q2);
        quests.add(q3);

        when(questRepositoryMock.getAll()).thenReturn(quests);

        QuestService qs = new QuestService();

        qs.setQuestRepository(questRepositoryMock);

        List<Quest> allNotStartedQuests = qs.getAllNotStartedQuests();

        assertEquals(2, allNotStartedQuests.size());
        assertThat(allNotStartedQuests, containsInAnyOrder(q2, q3));
    }
}

package com.tomq.kursspring.services;

import com.tomq.kursspring.domain.Quest;
import com.tomq.kursspring.domain.repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class QuestFormatter implements Formatter<Quest> {

    private QuestRepository repository;

    @Autowired
    public QuestFormatter(QuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Quest parse(String idAsString, Locale locale) throws ParseException {
        Integer id = Integer.parseInt(idAsString);
        return repository.getQuest(id);
    }

    @Override
    public String print(Quest quest, Locale locale) {
        return quest.toString();
    }
}

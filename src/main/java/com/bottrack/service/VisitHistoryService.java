package com.bottrack.service;

import com.bottrack.model.VisitHistoryModel;
import com.bottrack.repository.VisitHistoryRepository;
import com.bottrack.serviceinterfaces.IVisitHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitHistoryService implements IVisitHistoryService {
    @Autowired
    VisitHistoryRepository visitHistoryRepository;

    public List<VisitHistoryModel> getMonthVisitedMapHistory(long userId) {
         var result = visitHistoryRepository.lastMonthHistory(userId);
         return  result;
    }
}

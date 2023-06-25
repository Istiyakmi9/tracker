package com.bottrack.serviceinterfaces;

import com.bottrack.model.FilterModel;
import com.bottrack.model.VisitHistoryModel;

import java.util.List;

public interface IVisitHistoryService {
    List<VisitHistoryModel> getMonthVisitedMapHistory(FilterModel filterModel);
}

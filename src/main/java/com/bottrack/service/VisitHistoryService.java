package com.bottrack.service;

import com.bottrack.model.DbParameters;
import com.bottrack.model.FilterModel;
import com.bottrack.model.VisitHistoryModel;
import com.bottrack.repository.LowLevelExecution;
import com.bottrack.repository.VisitHistoryRepository;
import com.bottrack.serviceinterfaces.IVisitHistoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitHistoryService implements IVisitHistoryService {
    @Autowired
    VisitHistoryRepository visitHistoryRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LowLevelExecution lowLevelExecution;

    public List<VisitHistoryModel> getMonthVisitedMapHistory(FilterModel filter) {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_searchString", filter.getSearchString(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_sortBy", filter.getSortBy(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageIndex", filter.getPageIndex(), Types.INTEGER));
        dbParameters.add(new DbParameters("_pageSize", filter.getPageSize(), Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_visiting_history_getby_Filter", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<VisitHistoryModel>>() {});
    }
}

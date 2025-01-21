package com.example.booktrackerservice.queryHandlers.dispatch;

import com.example.booktrackerservice.entities.Dto.BookTrackerDto;
import com.example.booktrackerservice.queryHandlers.handler.QueryHandler;
import com.example.booktrackerservice.queryHandlers.query.BaseQuery;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class QueryBookTrackerDispatcher implements QueryDispatcher{
    private final Map<Class<? extends BaseQuery>, QueryHandler<? extends BaseQuery>> handlers = new HashMap<>();
    public QueryBookTrackerDispatcher(List<QueryHandler<? extends BaseQuery>> queryHandlers){
        for(QueryHandler<? extends BaseQuery> handler: queryHandlers){
            Class<? extends BaseQuery> query = getQueryType(handler);
            handlers.put(query, handler);
        }
    }
    private Class<? extends BaseQuery> getQueryType(QueryHandler<? extends BaseQuery> handler){
        ParameterizedType gotType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
        return (Class<? extends BaseQuery>) gotType.getActualTypeArguments()[0];
    }

    @Override
    public <U extends BookTrackerDto> List<U> dispatch(BaseQuery baseQuery) {
        QueryHandler<BaseQuery> handler = (QueryHandler<BaseQuery>) handlers.get(baseQuery.getClass());
        if(handler==null) throw new RuntimeException("Handler is not defined");
        return handler.execute(baseQuery);
    }
}

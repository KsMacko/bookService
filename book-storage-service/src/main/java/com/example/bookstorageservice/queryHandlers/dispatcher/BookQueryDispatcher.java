package com.example.bookstorageservice.queryHandlers.dispatcher;

import com.example.bookstorageservice.entity.DTO.BaseDto;
import com.example.bookstorageservice.queryHandlers.handlers.QueryHandler;
import com.example.bookstorageservice.queryHandlers.query.BaseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BookQueryDispatcher implements QueryDispatch{
    private final Map<Class<? extends BaseQuery>, QueryHandler<? extends BaseQuery>> handlers = new HashMap<>();

    @Autowired
    public BookQueryDispatcher(List<QueryHandler<? extends BaseQuery>> queryHandlerList){
        for(QueryHandler<? extends BaseQuery> queryHandler: queryHandlerList){
            Class<? extends BaseQuery> queryType = getQueryType(queryHandler);
            handlers.put(queryType,queryHandler);
        }
    }
    private Class<? extends BaseQuery> getQueryType(QueryHandler<? extends BaseQuery>  handler){
        ParameterizedType gotQueryType = (ParameterizedType) handler.getClass().getGenericInterfaces()[0];
        return (Class<? extends BaseQuery>) gotQueryType.getActualTypeArguments()[0];
    }

    @Override
    public <T extends BaseQuery> void regQuery(Class<T> query, QueryHandler<T> handler) {
        handlers.computeIfAbsent(query, qh->handler);
    }

    @Override
    public <U extends BaseDto> List<U> dispatch(BaseQuery baseQuery) {
        QueryHandler<BaseQuery> queryHandler = (QueryHandler<BaseQuery>) handlers.get(baseQuery.getClass());
        if(queryHandler==null) throw new RuntimeException("Handler is not defined");
        return queryHandler.execute(baseQuery);
    }
}

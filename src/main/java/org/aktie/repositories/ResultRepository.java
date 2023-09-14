package org.aktie.repositories;

import java.util.List;

import org.aktie.model.Result;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResultRepository implements PanacheRepository<Result> {

    public Result create(Result result) {
        persistAndFlush(result);

        return result;
    }

    public List<Result> listAll() {
        return listAll(Sort.ascending("id"));
    }

}

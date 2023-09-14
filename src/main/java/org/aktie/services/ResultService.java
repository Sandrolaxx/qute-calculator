package org.aktie.services;

import java.math.BigDecimal;
import java.util.List;

import org.aktie.model.EnumUserOption;
import org.aktie.model.Result;
import org.aktie.repositories.ResultRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ResultService {

    @Inject
    ResultRepository repository;

    @Transactional
    public void saveResult(EnumUserOption option, String firstValue, String secondValue, BigDecimal resultValue) {
        Result result = new Result();
        String operationText = option.getValue().concat(" - Primeiro valor: ").concat(firstValue)
                .concat(" - Segundo valor").concat(secondValue);

        result.setValue(resultValue);
        result.setOperation(operationText);

        repository.create(result);
    }

    public List<Result> getAll() {
        return repository.listAll();
    }

}

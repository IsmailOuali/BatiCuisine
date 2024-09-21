package org.example.repository.Interface;

import org.example.dao.MateriauDAO;
import org.example.models.Materiau;

public interface MateriauRepositoryInterface {

    public void MateriauRepository(MateriauDAO materiauDAO);
    void save(Materiau materiau);
}

package org.example.dao;

import org.example.models.Composant;
import org.example.models.Materiau;

import java.sql.SQLException;

public interface MateriauDAO {

    void addMateriau( Materiau materiau) throws SQLException;
}

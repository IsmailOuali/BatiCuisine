package org.example.dao;

import org.example.models.MainDOeuvre;

import java.sql.SQLException;

public interface MainDOeuvreDAO {

    void addMainDOeuvre(MainDOeuvre mainDOeuvre) throws SQLException;
}

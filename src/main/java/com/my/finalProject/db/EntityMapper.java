package com.my.finalProject.db;

import java.sql.ResultSet;

public interface EntityMapper<T> {
    T mapRow(ResultSet resultSet);
}

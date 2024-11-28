package dev.priporov.ftwc.mapper;

import dev.priporov.ftwc.entity.FormulaEntity;
import dev.priporov.ftwc.entity.VariableEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class FormulaRowMapper implements ResultSetExtractor<FormulaEntity> {

    @Override
    public FormulaEntity extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<String, FormulaEntity> map = new HashMap<>();

        while (rs.next()) {
            String formula = rs.getString("formula");

            FormulaEntity entity = map.computeIfAbsent(formula, k -> new FormulaEntity());
            VariableEntity variable = new VariableEntity();
            variable.setName(rs.getString("variable"));
            variable.setValue(rs.getBigDecimal("value"));
            variable.setAlias(rs.getString("alias"));

            entity.setId(rs.getLong("id"));
            entity.setFormula(formula);
            entity.getVariables().add(variable);
        }

        Iterator<FormulaEntity> iterator = map.values().iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }

}

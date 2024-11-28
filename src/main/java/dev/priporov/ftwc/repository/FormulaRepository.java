package dev.priporov.ftwc.repository;

import dev.priporov.ftwc.dto.CalculationRequest;
import dev.priporov.ftwc.entity.FormulaEntity;
import dev.priporov.ftwc.mapper.FormulaRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FormulaRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final FormulaRowMapper formulaRowMapper;

    public FormulaEntity findFormula(CalculationRequest request) {
        String sql = """
                select f.id as id, f.value as formula, v.name as variable, v.alias_code_name as alias, v.value as value from formula f
                 join mixture m  on f.mixture_id = m.id
                 join variable v on f.id = v.formula_id
                where m.id = :mixture_id
                """;
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("mixture_id", request.getMixtureId());

        return jdbcTemplate.query(sql, parameters, formulaRowMapper);
    }

}

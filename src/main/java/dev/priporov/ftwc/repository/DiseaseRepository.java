package dev.priporov.ftwc.repository;

import dev.priporov.ftwc.entity.DiseaseEntity;
import dev.priporov.ftwc.mapper.DiseaseRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class DiseaseRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DiseaseRowMapper diseaseRowMapper;

    public Collection<DiseaseEntity> findAll() {
        String sql = """
            select d.id as id , d.name as name , m.name as mixture_name, m.id as mixture_id 
            from disease d full join mixture m on m.disease_id = d.id
            """;

        return jdbcTemplate.query(sql, diseaseRowMapper);
    }

}

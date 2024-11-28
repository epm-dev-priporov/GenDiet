package dev.priporov.ftwc.mapper;

import dev.priporov.ftwc.entity.DiseaseEntity;
import dev.priporov.ftwc.entity.MixtureEntity;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class DiseaseRowMapper implements ResultSetExtractor<Collection<DiseaseEntity>> {

    @Override
    public Collection<DiseaseEntity> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<Long, DiseaseEntity> map = new HashMap<>();

        while (rs.next()) {
            Long id = rs.getLong("id");

            DiseaseEntity entity = map.computeIfAbsent(id, k -> new DiseaseEntity());
            entity.setId(id);
            entity.setName(rs.getString("name"));
            entity.getMixtures().add(
                    new MixtureEntity(
                            rs.getLong("mixture_id"),
                            rs.getString("mixture_name")
                    )
            );
        }

        return map.values();
    }

}

package dev.gyeoul.iek.api.repository;

import dev.gyeoul.iek.api.domain.request.ContributePortalRequest;
import org.jooq.*;
import org.jooq.generated.tables.JPortal;
import org.jooq.generated.tables.records.PortalRecord;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;

import static org.jooq.impl.DSL.*;

@Repository
public class PortalRepository {
    private static final JPortal PORTAL = JPortal.PORTAL;
    private final DSLContext dsl;

    private <T> void setIfNotNull(BiConsumer<TableField<PortalRecord, T>, T> setter, TableField<PortalRecord, T> field, T value) {
        if (value != null) {
            setter.accept(field, value);
        }
    }

    public PortalRepository(Configuration configuration) {
        dsl = using(configuration);

    }

    public PortalRecord getPortalByGuid(UUID guid) {
        return dsl.selectFrom(PORTAL).where(PORTAL.GUID.eq(guid)).fetchOne();
    }

    public List<PortalRecord> getPortalsByName(String name) {
        var likeQuery = Arrays.stream(name.split("\\s+"))
                .map(String::trim)
                .map(str -> "%" + str + "%")
                .toArray(String[]::new);

        var q = dsl.selectFrom(PORTAL);
        SelectConditionStep<PortalRecord> where = q.where(PORTAL.NAME.like(likeQuery[0]));
        for (int i = 1; i < likeQuery.length; i++) {
            where = where.and(PORTAL.NAME.like(likeQuery[i]));
        }
        return where
                .limit(8)
                .fetch();
    }

    public List<PortalRecord> getSimilarPortalsByName(String name) {
        Field<String> nameField = field("name", String.class);
        Field<String> imageField = field("image", String.class);
        return dsl
                .select(
                        PORTAL,
                        field(
                                "bigm_similarity(LOWER(name), LOWER('" + name + "'))",
                                Double.class)
                                .as("similarity"))
                .from(PORTAL)
                .where(
                        DSL.lower(PORTAL.NAME)
                                .likeRegex(DSL.lower(inline(name))))
                .orderBy(
                        field("similarity").desc(),
                        nameField.asc(),
                        imageField.asc())
                .limit(4)
                .fetch()
                .map(r -> r.get(0, PortalRecord.class));
    }

    public void updatePortal(PortalRecord portal) {
        dsl.update(PORTAL).set(portal).where(PORTAL.GUID.eq(portal.getGuid())).execute();
    }

    public void insertPortal(PortalRecord portal) {
        dsl.insertInto(PORTAL).set(portal).execute();
    }

    public InsertOnDuplicateSetStep<PortalRecord> insertPortalBuilder(ContributePortalRequest contrib, UUID guid) {
        PortalRecord newRecord = createNewPortalRecord(contrib, guid);
        return dsl.insertInto(PORTAL)
                .set(newRecord)
                .onDuplicateKeyUpdate();
    }

    private PortalRecord createNewPortalRecord(ContributePortalRequest contrib, UUID guid) {
        PortalRecord portal = JPortal.PORTAL.newRecord();
        portal.set(PORTAL.GUID, guid);
        setIfNotNull(portal::set, PORTAL.NAME, contrib.getTitle());
        setIfNotNull(portal::set, PORTAL.IMAGE, contrib.getImage());
        portal.set(PORTAL.LATE6, contrib.getLatE6());
        portal.set(PORTAL.LNGE6, contrib.getLngE6());
        portal.set(PORTAL.LAST_UPDATE, LocalDateTime.now());
        return portal;
    }

    public int[] batchUpdate(List<Query> list) {
        return dsl.batch(list).execute();
    }
}

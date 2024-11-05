/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.daos;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jooq.Configuration;
import org.jooq.generated.tables.JPortal;
import org.jooq.generated.tables.pojos.Portal;
import org.jooq.generated.tables.records.PortalRecord;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class PortalDao extends DAOImpl<PortalRecord, Portal, UUID> {

    /**
     * Create a new PortalDao without any configuration
     */
    public PortalDao() {
        super(JPortal.PORTAL, Portal.class);
    }

    /**
     * Create a new PortalDao with an attached configuration
     */
    public PortalDao(Configuration configuration) {
        super(JPortal.PORTAL, Portal.class, configuration);
    }

    @Override
    public UUID getId(Portal object) {
        return object.getGuid();
    }

    /**
     * Fetch records that have <code>guid BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Portal> fetchRangeOfJGuid(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(JPortal.PORTAL.GUID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>guid IN (values)</code>
     */
    public List<Portal> fetchByJGuid(UUID... values) {
        return fetch(JPortal.PORTAL.GUID, values);
    }

    /**
     * Fetch a unique record that has <code>guid = value</code>
     */
    public Portal fetchOneByJGuid(UUID value) {
        return fetchOne(JPortal.PORTAL.GUID, value);
    }

    /**
     * Fetch a unique record that has <code>guid = value</code>
     */
    public Optional<Portal> fetchOptionalByJGuid(UUID value) {
        return fetchOptional(JPortal.PORTAL.GUID, value);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public List<Portal> fetchRangeOfJPoint(Object lowerInclusive, Object upperInclusive) {
        return fetchRange(JPortal.PORTAL.POINT, lowerInclusive, upperInclusive);
    }

    /**
     * @deprecated Unknown data type. If this is a qualified, user-defined type,
     * it may have been excluded from code generation. If this is a built-in
     * type, you can define an explicit {@link org.jooq.Binding} to specify how
     * this type should be handled. Deprecation can be turned off using
     * {@literal <deprecationOnUnknownTypes/>} in your code generator
     * configuration.
     */
    @Deprecated
    public List<Portal> fetchByJPoint(Object... values) {
        return fetch(JPortal.PORTAL.POINT, values);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Portal> fetchRangeOfJName(String lowerInclusive, String upperInclusive) {
        return fetchRange(JPortal.PORTAL.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<Portal> fetchByJName(String... values) {
        return fetch(JPortal.PORTAL.NAME, values);
    }

    /**
     * Fetch records that have <code>image BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Portal> fetchRangeOfJImage(String lowerInclusive, String upperInclusive) {
        return fetchRange(JPortal.PORTAL.IMAGE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>image IN (values)</code>
     */
    public List<Portal> fetchByJImage(String... values) {
        return fetch(JPortal.PORTAL.IMAGE, values);
    }

    /**
     * Fetch records that have <code>last_update BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Portal> fetchRangeOfJLastUpdate(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(JPortal.PORTAL.LAST_UPDATE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>last_update IN (values)</code>
     */
    public List<Portal> fetchByJLastUpdate(LocalDateTime... values) {
        return fetch(JPortal.PORTAL.LAST_UPDATE, values);
    }

    /**
     * Fetch records that have <code>late6 BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Portal> fetchRangeOfJLate6(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(JPortal.PORTAL.LATE6, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>late6 IN (values)</code>
     */
    public List<Portal> fetchByJLate6(Long... values) {
        return fetch(JPortal.PORTAL.LATE6, values);
    }

    /**
     * Fetch records that have <code>lnge6 BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<Portal> fetchRangeOfJLnge6(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(JPortal.PORTAL.LNGE6, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>lnge6 IN (values)</code>
     */
    public List<Portal> fetchByJLnge6(Long... values) {
        return fetch(JPortal.PORTAL.LNGE6, values);
    }
}

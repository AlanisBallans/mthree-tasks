package dao.interfaces;

import exceptions.PersistenceException;

public interface AuditDao {
    void writeAuditEntry(String entry) throws PersistenceException;
}

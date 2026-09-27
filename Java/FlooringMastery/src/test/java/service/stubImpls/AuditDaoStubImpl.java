package service.stubImpls;

import dao.interfaces.AuditDao;
import exceptions.PersistenceException;

public class AuditDaoStubImpl implements AuditDao {
    @Override
    public void writeAuditEntry(String entry) throws PersistenceException {

    }
}

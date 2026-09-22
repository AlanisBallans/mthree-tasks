package dao.interfaces;

import dto.Order;
import exceptions.PersistenceException;

import java.time.LocalDate;
import java.util.Map;

public interface ExportDao {
    void exportData() throws PersistenceException;
}

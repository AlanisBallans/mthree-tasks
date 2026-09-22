package dao.implementations;

import dao.interfaces.ExportDao;
import dto.Order;
import exceptions.PersistenceException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExportDaoFileImpl implements ExportDao {


    private final String EXPORT_FILE;
    private final String ORDER_FOLDER;
    private final String DELIMITER = "::";
    private final String FILE_DATE_PATTERN = "yyyyMMdd";
    private final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern(FILE_DATE_PATTERN);
    private final String ORDER_DATE_PATTERN = "dd-MM-yyyy";
    private final String ORDER_FILE_EXTENSION = ".txt";

    private List<Order> allOrders = new ArrayList<>();

    public ExportDaoFileImpl() {
        EXPORT_FILE = "DataExport.txt";
        ORDER_FOLDER = "Orders";
    }

    public ExportDaoFileImpl(String fileName, String folderName) {
        EXPORT_FILE = fileName;
        ORDER_FOLDER = folderName;
    }


    private void writeToFile() throws PersistenceException {
        PrintWriter writer;
        try {
            writer = new PrintWriter(new FileWriter(EXPORT_FILE));

        } catch (IOException e) {
            throw new PersistenceException("Unable to load file to write to",e);
        }

        File[] files = new File(ORDER_FOLDER).listFiles(); // Get all files in folder
        if (files == null) throw new PersistenceException("Files in order folder not found");

        for (File file : files) {
            String fileName = file.getName();
            String stringDate = fileName.substring(fileName.length() - (FILE_DATE_PATTERN.length() + ORDER_FILE_EXTENSION.length()),
                    fileName.length() - (ORDER_FILE_EXTENSION.length()));
            LocalDate date = LocalDate.parse(stringDate, FILE_DATE_FORMAT);
            String dateAsOrderField = date.format(DateTimeFormatter.ofPattern(ORDER_DATE_PATTERN));

            Scanner scanner;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                throw new PersistenceException("File unable to be loaded", e);
            }

            while (scanner.hasNext()) {
                String orderLine = scanner.nextLine() + DELIMITER + dateAsOrderField;
                writer.println(orderLine);
                writer.flush();
            }

            scanner.close();

        }
    }

    @Override
    public void exportData() throws PersistenceException {
        writeToFile();
    }
}

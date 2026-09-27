import org.example.controller.AddressBookController;
import org.example.dao.AddressBookDao;
import org.example.dao.AddressBookDaoFileImpl;
import org.example.ui.AddressBookView;
import org.example.ui.UserIO;
import org.example.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        AddressBookDao dao = new AddressBookDaoFileImpl();
        UserIO io = new UserIOConsoleImpl();
        AddressBookView view = new AddressBookView(io);
        AddressBookController controller = new AddressBookController(dao, view);

        controller.run();
    }

}

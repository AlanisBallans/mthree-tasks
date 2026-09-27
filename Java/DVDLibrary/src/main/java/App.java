import org.example.controller.DvdLibraryController;
import org.example.dao.DvdLibraryDao;
import org.example.dao.DvdLibraryDaoFileImpl;
import org.example.ui.DvdLibraryView;
import org.example.ui.UserIO;
import org.example.ui.UserIOConsoleImpl;

public class App {

    public static void main(String[] args) {
        DvdLibraryDao dao = new DvdLibraryDaoFileImpl();
        UserIO io = new UserIOConsoleImpl();
        DvdLibraryView view = new DvdLibraryView(io);
        DvdLibraryController controller = new DvdLibraryController(dao, view);

        controller.run();
    }

}

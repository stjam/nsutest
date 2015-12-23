package lb.view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("lb.MyAppWidgetset")
public class MyUI extends UI {
    private final VerticalLayout mainVerticalLayout = new VerticalLayout();
    private final TabSheet tabSheet = new TabSheet();

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        mainVerticalLayout.addComponent(createMenuBar());
        ClientEditor clientEditor = new ClientEditor();
        AccountEditor accountEditor = new AccountEditor();
        TransactionEditor transactionEditor = new TransactionEditor();
        tabSheet.addTab(clientEditor,"Клиенты");
        tabSheet.addTab(accountEditor, "Счета");
        tabSheet.addTab(transactionEditor, "Транзакциии");
        mainVerticalLayout.addComponent(tabSheet);
        setContent(mainVerticalLayout);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    private MenuBar createMenuBar(){
        MenuBar menuBar = new MenuBar();
        menuBar.setSizeFull();
        MenuBar.MenuItem recipeMenu = menuBar.addItem("Файл", null);
        recipeMenu.addItem(MenuBarEnumUI.valueOf(1).getName(), new MenuHandler());
        recipeMenu.addItem(MenuBarEnumUI.valueOf(2).getName(), new MenuHandler());
        recipeMenu.addItem(MenuBarEnumUI.valueOf(3).getName(), new MenuHandler());
        return menuBar;
    }

    private class MenuHandler implements MenuBar.Command{
        @Override
        public void menuSelected(MenuBar.MenuItem menuItem) {
            int commandCode = MenuBarEnumUI.indexOf(menuItem.getText().toString());
            switch(commandCode){
                case 1:
                    Notification.show("Новый клиент");
                    break;
                case 2:
                    Notification.show("Новый счет");
                    break;
                case 3:
                    Notification.show("Новая операция");
                    break;
            }
        }
    }
}

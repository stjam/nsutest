package lb.view;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import lb.bank.service.configs.SpringContextHelper;
import lb.model.entity.Account;
import lb.model.entity.Client;
import lb.service.PersistenceService;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 23.12.2015.
 */
public class ClientEditor extends HorizontalSplitPanel implements ComponentContainer {
    private final BeanItemContainer<Client> clients =
            new BeanItemContainer<>(Client.class);
    private final BeanItemContainer<Account> accounts =
            new BeanItemContainer<Account>(Account.class);
    private final SpringContextHelper helper = new SpringContextHelper();;
    private final PersistenceService clientService = (PersistenceService)helper.getBean("clientService");
    private final PersistenceService accountService = (PersistenceService)helper.getBean("accountService");
    private final Table clientTable = new Table("Список клиентов");
    private final Table accountTable = new Table("Список счетов");
    private final TextField firstName = new TextField("Имя") ;
    private final TextField lastName = new TextField("Фамилия");
    private final DateField birthDate = new DateField("Дата рождения");
    private final TextField accountNumber = new TextField("Новый счет");
    private final TextField accountBalance = new TextField("Баланс");
    private final HorizontalLayout formHorizontalLayout = new HorizontalLayout();
    private final Button newButton = new Button("Новый клиент");
    private final Button saveButton = new Button("Добавить");
    private final Button updateButton = new Button("Сохранить");
    private final Button deleteButton = new Button("Удалить");
    private boolean createMode = true;
       public ClientEditor(){
              refreshClientList();
              accountTable.setContainerDataSource(accounts);
              accountTable.setVisibleColumns(new Object[]{"accountNumber", "balance"});
              accountTable.setVisible(!createMode);

              clientTable.setContainerDataSource(clients);
              clientTable.setVisibleColumns(new Object[]{"id","firstName","lastName","birthDate"});
              clientTable.setSelectable(true);
              clientTable.addValueChangeListener(new Property.ValueChangeListener() {
                  @Override
                  public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                         Client client = (Client)clientTable.getValue();

                         createMode = false;
                         accountTable.setVisible(!createMode);
                         saveButton.setVisible(createMode);
                         updateButton.setVisible(!createMode);
                         deleteButton.setVisible(!createMode);

                         accountNumber.setVisible(createMode);
                         accountBalance.setVisible(createMode);
                         populateFields(client.getFirstName(),client.getLastName(), client.getBirthDate(),client.getId());
                         refreshAccountList();
                  }
              });
           newButton.addClickListener(new Button.ClickListener() {
               @Override
               public void buttonClick(Button.ClickEvent clickEvent) {
                      createMode = true;
                      saveButton.setVisible(createMode);
                      updateButton.setVisible(!createMode);
                      deleteButton.setVisible(!createMode);
                      accountTable.setVisible(!createMode);
                      accountNumber.setVisible(createMode);
                      accountBalance.setVisible(createMode);
               }
           });
           final VerticalLayout vLayout = new VerticalLayout();
           vLayout.addComponent(newButton);
           vLayout.addComponent(clientTable);
           vLayout.setSizeFull();
           addComponent(vLayout);
           addComponent(createForm());
           setSplitPosition(40, Unit.PERCENTAGE);
           this.setSizeFull();
       }
    private FormLayout createForm(){
            final FormLayout form = new FormLayout();
            form.setMargin(true);
            form.setSizeFull();
            final HorizontalLayout horizontalLayoutz = new HorizontalLayout();
            firstName.setRequired(true);
            lastName.setRequired(true);
            birthDate.setRequired(true);
            birthDate.setDateFormat("dd-MM-yyyy");
            form.addComponent(firstName);
            form.addComponent(lastName);
            form.addComponent(birthDate);
            form.addComponent(accountNumber);
            form.addComponent(accountBalance);
            saveButton.setVisible(createMode);
            saveButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
            saveButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    final Client client = new Client();
                    client.setFirstName(firstName.getValue());
                    client.setLastName(lastName.getValue());
                    client.setBirthDate(birthDate.getValue());
                    clientService.add(client);
                    final Account account = new Account();
                    account.setBalance(Double.valueOf(accountBalance.getValue()));
                    account.setClient(client);
                    accountNumber.setValue(account.getAccountNumber());
                    accountService.add(account);
                    Notification.show("Добавлен новый клиент", Notification.Type.HUMANIZED_MESSAGE);
                    refreshClientList();
                }
            });
            updateButton.setVisible(!createMode);
            updateButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
            updateButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    final Client client = (Client) clientService.findById(editable);
                    client.setFirstName(firstName.getValue());
                    client.setLastName(lastName.getValue());
                    client.setBirthDate(birthDate.getValue());
                    clientService.update(client);
                    refreshClientList();
                    Notification.show("Информация о клиенте изменена", Notification.Type.HUMANIZED_MESSAGE);
                }
            });
            deleteButton.setVisible(!createMode);
            deleteButton.setStyleName(ValoTheme.BUTTON_DANGER);
            deleteButton.setWidth(String.valueOf(updateButton.getWidth()));
            deleteButton.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    clientService.delete(editable);
                    refreshClientList();
                    Notification.show("Клиент удален", Notification.Type.HUMANIZED_MESSAGE);
                }
            });
            accountBalance.setRequired(true);
            accountNumber.setRequired(true);
            accountNumber.setEnabled(false);
            horizontalLayoutz.addComponent(saveButton);
            horizontalLayoutz.addComponent(updateButton);
            horizontalLayoutz.addComponent(deleteButton);
            form.addComponent(horizontalLayoutz);
            accountTable.setVisible(!createMode);
            accountTable.setSizeFull();
            form.addComponent(accountTable);
        return form;
    }
    private Long editable=Long.MAX_VALUE;
    public void populateFields(String firstName, String lastName, Date birthDate, Long id){
            this.firstName.setValue(firstName);
            this.lastName.setValue(lastName);
            this.birthDate.setValue(birthDate);
            this.editable = id;

    }
    private void refreshClientList(){
        clients.removeAllItems();
        final List<Client> clientList = clientService.getList();
        if(clientList != null) {
            clients.addAll(clientList);
        }
    }
    private void refreshAccountList(){
        accounts.removeAllItems();
        Client client = (Client) clientService.findById(editable);
        final List<Account> accountList = client.getAccounts();
        if(accountList != null) {
            accounts.addAll(accountList);
        }
    }
}

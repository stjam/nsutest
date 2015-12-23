package lb.view;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import lb.bank.service.configs.SpringContextHelper;
import lb.model.entity.Account;
import lb.model.entity.Transaction;
import lb.service.PersistenceService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 23.12.2015.
 */
public class AccountEditor extends HorizontalSplitPanel implements ComponentContainer {
    private final BeanItemContainer<Transaction> transactions =
            new BeanItemContainer<>(Transaction.class);
    private final Table transactionsTable =  new Table("Список транзакций");
    private final SpringContextHelper helper = new SpringContextHelper();
    private final PersistenceService transactionService = (PersistenceService)helper.getBean("transactionService");
    private final VerticalLayout transactionsLayout = new VerticalLayout();
    private final PersistenceService accountService = (PersistenceService)helper.getBean("accountService");
    private final VerticalLayout accountLayout = new VerticalLayout();
    private final BeanItemContainer<Account> accounts =
            new BeanItemContainer<Account>(Account.class);
    private final Table accountTable = new Table("Список счетов");
    public AccountEditor(){
        refreshAccountList();
        accountTable.setSelectable(true);
        accountTable.setContainerDataSource(accounts);
        transactionsTable.setContainerDataSource(transactions);
        transactionsTable.setVisibleColumns(new Object[] {"id", "operationValue", "info", "targetAccount",});
        accountTable.setVisibleColumns(new Object[] {"id", "balance","accountNumber"});

        accountTable.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                Account account = (Account) accountTable.getValue();
                refreshTransactionContainer(account.getId());
                refreshAccountList();
            }
        });
        accountLayout.addComponent(accountTable);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponent(transactionsTable);
        accountTable.setSizeFull();
        transactionsTable.setSizeFull();
        addComponent(accountLayout);
        addComponent(verticalLayout);
        setSplitPosition(30,UNITS_PERCENTAGE);
    }

    private void refreshAccountList(){
        accounts.removeAllItems();
        final List<Account> accountList = accountService.getList();
        if(accountList != null) {
            accounts.addAll(accountList);
        }
    }
    private void refreshTransactionContainer(final Long id){
        transactions.removeAllItems();
        List<Transaction> transactionList = transactionService.getList();
        List<Transaction> transactionList1 = new ArrayList<>();
        for(Transaction tr : transactionList) {
            if(tr.getAccount().getId() == id)
                transactionList1.add(tr);
        }
        if(transactionList != null) {
            transactions.addAll(transactionList1);
        }
    }
}

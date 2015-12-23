package lb.view;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import lb.bank.service.configs.SpringContextHelper;
import lb.model.entity.Transaction;
import lb.service.PersistenceService;

import java.util.List;

/**
 * Created by root on 23.12.2015.
 */
public class TransactionEditor extends HorizontalSplitPanel implements ComponentContainer {
    private final BeanItemContainer<Transaction> transactions =
            new BeanItemContainer<>(Transaction.class);
    private final Table transactionsTable =  new Table();
    private final SpringContextHelper helper = new SpringContextHelper();
    private final PersistenceService transactionService = (PersistenceService)helper.getBean("transactionService");
    private final VerticalLayout transactionsLayout = new VerticalLayout();

    public  TransactionEditor(){
            refreshTransactionContainer();
            transactionsTable.setContainerDataSource(transactions);
            transactionsTable.setVisibleColumns(new Object[]{"id", "operationValue", "info", "targetAccount",});
            transactionsTable.setSelectable(true);
            transactionsTable.setSizeFull();

            transactionsLayout.addComponent(transactionsTable);
            addComponent(transactionsLayout);
            setSplitPosition(100, Unit.PERCENTAGE);
            this.setSizeFull();
    }

    private void refreshTransactionContainer(){
        transactions.removeAllItems();
        final List<Transaction> transactionList = transactionService.getList();
        if(transactionList != null) {
            transactions.addAll(transactionList);
        }
    }
}

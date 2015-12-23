package lb.model.enums;

import lb.model.util.ResourceHelper;

/**
 * Created by root on 19.12.2015.
 * Supported transaction types:
 * <li>{@link #INCOME}</li>
 * <li>{@link #OUTCOME}</li>
 */
public enum TransactionType {
    /*
     * Increment balance transaction
     */
    INCOME(0L, "account.income.operation" ),
    /*
     * Decrement balance transaction
     */
    OUTCOME(1L, "account.outcome.operation" );

    private final Long operationTypeId;
    private String operationName;

    TransactionType(Long operationTypeId, String operationName) {
        this.operationTypeId = operationTypeId;
        this.operationName = operationName;
    }

    public String getValue(String key) {
        return ResourceHelper.getResourceKey(key);
    }
}

package lb.view;

/**
 * Created by root on 23.12.2015.
 */
public enum MenuBarEnumUI {

    CLIENT("Новый клиент", 1),
    ACCOUNT("Новый счет", 2),
    TRANSFER("Провести транзакцию", 3);

    private String name;
    private Integer id;

    MenuBarEnumUI(String name, Integer id) {
        this.id = id;
        this.name = name;
    }

    public static MenuBarEnumUI valueOf(Integer code){
        for(MenuBarEnumUI value:MenuBarEnumUI.values()){
            if(value.getId()==code)
                return value;
        }
        return  null;
    }
    public static int indexOf(String param){
        for(MenuBarEnumUI value : MenuBarEnumUI.values()) {
            if(value.getName().equals(param)){
                return value.getId();
            }
        }
        return 0;
    }

    public String getName(){
           return name;

    }

    public Integer getId(){
        return id;
    }
}

package lb.model.entity;

import java.io.Serializable;

/**
 * Created by root on 21.12.2015.
 */
public interface Persistent extends Serializable {
       Long getId();
       void setId(final Long id);
}

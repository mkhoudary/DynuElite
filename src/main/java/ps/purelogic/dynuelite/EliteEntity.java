/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Mohammed
 */
public class EliteEntity extends EliteConditionBuilder {

    private final String model;

    public EliteEntity(String model) {
        this.model = model;
    }
    
    public EliteEntity and(Consumer<EliteConditionBuilder> conditions) {
        return this;
    }

    public List<EntityPresentation> all() {
        return Collections.EMPTY_LIST;
    }

}

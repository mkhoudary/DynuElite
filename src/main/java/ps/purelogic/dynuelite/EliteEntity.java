/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Mohammed
 */
public class EliteEntity extends EliteConditionBuilder {

    private final String model;

    public EliteEntity(String model) {
        this.model = model;
    }
    
    public List<EntityPresentation> all() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void translate(StringBuilder builder) {
         for (int i = 0; i < conditions.size(); i++) {
            if (i > 0) {
                builder.append(" AND ");
            }
            
            conditions.get(i).translate(builder);
        }
    }

}

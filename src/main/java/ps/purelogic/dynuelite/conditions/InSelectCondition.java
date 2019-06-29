/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite.conditions;

import ps.purelogic.dynuelite.EliteEntity;
import ps.purelogic.dynuelite.EliteOperand;

/**
 *
 * @author Mohammed
 */
public class InSelectCondition implements Condition {

    private final EliteOperand operand;
    private final EliteEntity innerEntity;

    public InSelectCondition(EliteOperand operand, EliteEntity innerEntity) {
        this.operand = operand;
        this.innerEntity = innerEntity;
    }

    @Override
    public String translate() {
        StringBuilder builder = new StringBuilder();
        
        operand.translate(builder);
        builder.append(" in (");
        
        innerEntity.translate(builder);
        
        builder.append(") ");
        
        return builder.toString();
    }

}

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
public class EqualitySelectCondition implements Condition {

    private final EliteOperand operand;
    private final EliteEntity innerEntity;
    private final boolean equality;

    public EqualitySelectCondition(EliteOperand operand, EliteEntity innerEntity, boolean equality) {
        this.operand = operand;
        this.innerEntity = innerEntity;
        this.equality = equality;
    }

    @Override
    public String translate() {
        StringBuilder builder = new StringBuilder();
        
        operand.translate(builder);
        
        if (equality) {
            builder.append(" = (");
        } else {
            builder.append(" <> (");
        }
        
        innerEntity.translate(builder);
        
        builder.append(") ");
        
        return builder.toString();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite.conditions;

import ps.purelogic.dynuelite.EliteOperand;

/**
 *
 * @author Mohammed
 */
public class NullityCondition implements Condition {

    private final EliteOperand operand;
    private final boolean isNull;

    public NullityCondition(EliteOperand operand, boolean isNull) {
        this.operand = operand;
        this.isNull = isNull;
    }

    @Override
    public String translate() {
        StringBuilder builder = new StringBuilder();
        
        operand.translate(builder);
        
        if (isNull) {
            builder.append(" IS NULL ");
        } else {
            builder.append(" IS NOT NULL ");
        }
        
        return builder.toString();
    }

}

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
public class EqualityCondition implements Condition {

    private final EliteOperand operand;
    private final EliteOperand value;
    private final boolean equals;

    public EqualityCondition(EliteOperand operand, EliteOperand value, boolean equals) {
        this.operand = operand;
        this.value = value;
        this.equals = equals;
    }

    @Override
    public String translate() {
        StringBuilder builder = new StringBuilder();
        
        operand.translate(builder);
        
        if (equals) {
            builder.append(" = ");
        } else {
            builder.append(" <> ");
        }
        
        value.translate(builder);
        
        return builder.toString();
    }

}

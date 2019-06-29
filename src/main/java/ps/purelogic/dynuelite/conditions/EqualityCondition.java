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

    public EqualityCondition(EliteOperand operand, EliteOperand value) {
        this.operand = operand;
        this.value = value;
    }

    @Override
    public void translate(StringBuilder builder) {
        operand.translate(builder);
        builder.append(" = ");
        value.translate(builder);
    }

}

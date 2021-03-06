/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite.conditions;

import java.util.Arrays;
import java.util.stream.Collectors;
import ps.purelogic.dynuelite.EliteOperand;

/**
 *
 * @author Mohammed
 */
public class InCondition implements Condition {

    private final EliteOperand operand;
    private final Object[] values;
    private final boolean in;

    public InCondition(EliteOperand operand, Object[] values, boolean in) {
        this.operand = operand;
        this.values = values;
        this.in = in;
    }

    @Override
    public String translate() {
        StringBuilder builder = new StringBuilder();
        
        operand.translate(builder);
        
        if (in) {
            builder.append(" in (");
        } else {
            builder.append(" not in (");
        }
        
        builder.append(Arrays.asList(values).stream().map(value -> value instanceof Number ? value.toString() : "'" + value.toString() + "'").collect(Collectors.joining(", ")));
        
        builder.append(") ");
        
        return builder.toString();
    }

}

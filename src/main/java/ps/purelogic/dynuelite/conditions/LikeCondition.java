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
public class LikeCondition implements Condition {
    
    public enum Matcher {
        ANYWHERE,
        STARTING,
        ENDING,
        EXACT
    }

    private final EliteOperand operand;
    private final Object value;
    private final Matcher matcher;
    
    public LikeCondition(EliteOperand operand, Object value, Matcher matcher) {
        this.operand = operand;
        this.value = value;
        this.matcher = matcher;
    }

    @Override
    public void translate(StringBuilder builder) {
        operand.translate(builder);
        builder.append(" like ");
        
        if (null != matcher) switch (matcher) {
            case STARTING:
                builder.append("'");
                builder.append(value.toString());
                builder.append("%' ");
                break;
            case ENDING:
                builder.append("'%");
                builder.append(value.toString());
                builder.append("' ");
                break;
            case ANYWHERE:
                builder.append("'%");
                builder.append(value.toString());
                builder.append("%' ");
                break;
            case EXACT:
                builder.append("'");
                builder.append(value.toString());
                builder.append("' ");
                break;
            default:
                break;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite;

/**
 *
 * @author Mohammed
 */
public class EliteConditionsGroup extends EliteConditionBuilder {

    private final String operand;

    public EliteConditionsGroup(String operand) {
        this.operand = operand;
    }

    @Override
    public void translate(StringBuilder builder) {
        builder.append("(");
        
        for (int i = 0; i < conditions.size(); i++) {
            if (i > 0) {
                builder.append(" ");
                builder.append(operand);
                builder.append(" ");
            }
            
            conditions.get(i).translate(builder);
        }
        
        builder.append(") ");
    }

}

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
    private final EliteConditionBuilder builder;

    public EliteConditionsGroup(String operand, EliteConditionBuilder builder) {
        this.operand = operand;
        this.builder = builder;
    }

    @Override
    public String translate() {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("(");

        for (int i = 0; i < conditions.size(); i++) {
            if (i > 0) {
                stringBuilder.append(" ");
                stringBuilder.append(operand);
                stringBuilder.append(" ");
            }

            conditions.get(i).translate(stringBuilder);
        }

        stringBuilder.append(") ");
        
        return stringBuilder.toString();
    }

    @Override
    public String alias() {
        return builder.alias();
    }

}

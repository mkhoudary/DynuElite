/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Mohammed
 */
public class EliteEntity extends EliteConditionBuilder {

    private final String model;
    private String alias;
    private List<EliteOperand> operands;

    public EliteEntity(String model) {
        this(model, model);
    }
    
    public EliteEntity(String model, String alias) {
        this.model = model;
        this.alias = alias;
        this.operands = new ArrayList<>();
    }
    
    public EliteConditionBuilder select(EliteOperand operand) {
        operands.add(operand);
        
        return this;
    }
    
    public EliteConditionBuilder select(String operand) {
        operands.add(EliteOperand.property(alias(), operand));
        
        return this;
    }
    
    public EliteConditionBuilder select(EliteOperand ... operandsToAdd) {
        operands.addAll(Arrays.asList(operandsToAdd));
        
        return this;
    }
    
    public EliteConditionBuilder select(String ... operandsToAdd) {
        operands.addAll(Arrays.asList(operandsToAdd).stream().map(operandToAdd -> EliteOperand.property(alias(), operandToAdd)).collect(Collectors.toList()));
        
        return this;
    }

    public List<EntityPresentation> all() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public String alias() {
        return alias;
    }

    @Override
    public String translate() {
        StringBuilder builder = new StringBuilder();
        
        builder.append("SELECT ");
        if (operands.isEmpty()) {
            builder.append(" * ");
        } else {
            builder.append(operands.stream().map(operand -> operand.translate()).collect(Collectors.joining(", ")));
        }
        builder.append(" FROM ");
        builder.append(model);
        builder.append(" ");
        builder.append(alias);
        builder.append(" WHERE ");
        
        for (int i = 0; i < conditions.size(); i++) {
            if (i > 0) {
                builder.append(" AND ");
            }

            conditions.get(i).translate(builder);
        }
        
        return builder.toString();
    }

}

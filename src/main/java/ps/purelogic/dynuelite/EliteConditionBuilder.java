/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import ps.purelogic.dynuelite.conditions.Condition;
import ps.purelogic.dynuelite.conditions.EqualityCondition;
import ps.purelogic.dynuelite.conditions.LikeCondition;

/**
 *
 * @author Mohammed
 */
public abstract class EliteConditionBuilder implements Condition {
    
    protected final List<Condition> conditions;

    public EliteConditionBuilder() {
        conditions = new ArrayList<>();
    }

    public EliteConditionBuilder and(Consumer<EliteConditionBuilder> conditionsGroup) {
        EliteConditionsGroup group = new EliteConditionsGroup("AND");
        conditions.add(group);
        
        conditionsGroup.accept(group);

        return this;
    }
    
    public EliteConditionBuilder eq(EliteOperand operand, EliteOperand value) {
        conditions.add(new EqualityCondition(operand, value));

        return this;
    }
    
    public EliteConditionBuilder eq(EliteOperand operand, Object value) {
        conditions.add(new EqualityCondition(operand, EliteOperand.value(value)));

        return this;
    }
    
    public EliteConditionBuilder eq(String operand, EliteOperand value) {
        conditions.add(new EqualityCondition(EliteOperand.property(operand), value));

        return this;
    }
    
    public EliteConditionBuilder eq(String operand, Object value) {
        conditions.add(new EqualityCondition(EliteOperand.property(operand), EliteOperand.value(value)));

        return this;
    }

    public EliteConditionBuilder like(EliteOperand operand, Object value) {
        conditions.add(new LikeCondition(operand, value, LikeCondition.Matcher.ANYWHERE));

        return this;
    }
    
    public EliteConditionBuilder like(String operand, Object value) {
        conditions.add(new LikeCondition(EliteOperand.property(operand), value, LikeCondition.Matcher.ANYWHERE));

        return this;
    }
    
    public EliteConditionBuilder like(EliteOperand operand, Object value, LikeCondition.Matcher matcher) {
        conditions.add(new LikeCondition(operand, value, matcher));

        return this;
    }
    
    public EliteConditionBuilder like(String operand, Object value, LikeCondition.Matcher matcher) {
        conditions.add(new LikeCondition(EliteOperand.property(operand), value, matcher));

        return this;
    }
    
    public void query() {
        StringBuilder wherePart = new StringBuilder();
        
        translate(wherePart);
        
        System.out.println(wherePart.toString());
    }
}

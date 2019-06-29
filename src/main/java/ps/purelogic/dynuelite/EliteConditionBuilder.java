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
import ps.purelogic.dynuelite.conditions.InCondition;
import ps.purelogic.dynuelite.conditions.InSelectCondition;
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
    
    public EliteConditionBuilder inSelect(String operand, String model, Consumer<EliteEntity> entity) {
        EliteEntity inEntity = new EliteEntity(model);
        conditions.add(new InSelectCondition(EliteOperand.property(alias(), operand), inEntity));
        
        entity.accept(inEntity);

        return this;
    }
    
    public EliteConditionBuilder inSelect(EliteOperand operand, String model, Consumer<EliteEntity> entity) {
        EliteEntity inEntity = new EliteEntity(model);
        conditions.add(new InSelectCondition(operand, inEntity));
        
        entity.accept(inEntity);

        return this;
    }

    public EliteConditionBuilder in(String operand, Object ... values) {
        conditions.add(new InCondition(EliteOperand.property(alias(), operand), values));

        return this;
    }

    public EliteConditionBuilder in(EliteOperand operand, Object ... values) {
        conditions.add(new InCondition(operand, values));

        return this;
    }

    public EliteConditionBuilder and(Consumer<EliteConditionBuilder> conditionsGroup) {
        EliteConditionsGroup group = new EliteConditionsGroup("AND", this);
        conditions.add(group);
        
        conditionsGroup.accept(group);

        return this;
    }

    public EliteConditionBuilder or(Consumer<EliteConditionBuilder> conditionsGroup) {
        EliteConditionsGroup group = new EliteConditionsGroup("OR", this);
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
        conditions.add(new EqualityCondition(EliteOperand.property(alias(), operand), value));

        return this;
    }
    
    public EliteConditionBuilder eq(String operand, Object value) {
        conditions.add(new EqualityCondition(EliteOperand.property(alias(), operand), EliteOperand.value(value)));

        return this;
    }

    public EliteConditionBuilder like(EliteOperand operand, Object value) {
        conditions.add(new LikeCondition(operand, value, LikeCondition.Matcher.ANYWHERE));

        return this;
    }
    
    public EliteConditionBuilder like(String operand, Object value) {
        conditions.add(new LikeCondition(EliteOperand.property(alias(), operand), value, LikeCondition.Matcher.ANYWHERE));

        return this;
    }
    
    public EliteConditionBuilder like(EliteOperand operand, Object value, LikeCondition.Matcher matcher) {
        conditions.add(new LikeCondition(operand, value, matcher));

        return this;
    }
    
    public EliteConditionBuilder like(String operand, Object value, LikeCondition.Matcher matcher) {
        conditions.add(new LikeCondition(EliteOperand.property(alias(), operand), value, matcher));

        return this;
    }
    
    public abstract String alias();
    
    public void query() {
        StringBuilder wherePart = new StringBuilder();
        
        translate(wherePart);
        
        System.out.println(wherePart.toString());
    }
}

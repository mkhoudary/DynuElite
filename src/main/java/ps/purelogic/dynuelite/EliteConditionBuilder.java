/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import ps.purelogic.dynuelite.conditions.Condition;
import ps.purelogic.dynuelite.conditions.EqualityCondition;
import ps.purelogic.dynuelite.conditions.EqualitySelectCondition;
import ps.purelogic.dynuelite.conditions.InCondition;
import ps.purelogic.dynuelite.conditions.InSelectCondition;
import ps.purelogic.dynuelite.conditions.LikeCondition;
import ps.purelogic.dynuelite.conditions.NullityCondition;

/**
 *
 * @author Mohammed
 */
public abstract class EliteConditionBuilder implements Condition {
    
    protected final List<Condition> conditions;

    public EliteConditionBuilder() {
        conditions = new ArrayList<>();
    }
    
    /*
    * In Select
    */
    
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
    
    /*
    * In
    */

    public EliteConditionBuilder in(String operand, Object ... values) {
        conditions.add(new InCondition(EliteOperand.property(alias(), operand), values, true));

        return this;
    }

    public EliteConditionBuilder in(EliteOperand operand, Object ... values) {
        conditions.add(new InCondition(operand, values, true));

        return this;
    }
    
    /*
    * Not In
    */

    public EliteConditionBuilder notIn(String operand, Object ... values) {
        conditions.add(new InCondition(EliteOperand.property(alias(), operand), values, false));

        return this;
    }

    public EliteConditionBuilder notIn(EliteOperand operand, Object ... values) {
        conditions.add(new InCondition(operand, values, false));

        return this;
    }
    
    /*
    * Grouping
    */

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
    
    /*
    * Equal Select
    */
    
    public EliteConditionBuilder eqSelect(EliteOperand operand, String model, Consumer<EliteEntity> entity) {
        EliteEntity inEntity = new EliteEntity(model);
        conditions.add(new EqualitySelectCondition(operand, inEntity, true));

        entity.accept(inEntity);
        
        return this;
    }
    
    public EliteConditionBuilder eqSelect(String operand, String model, Consumer<EliteEntity> entity) {
        EliteEntity inEntity = new EliteEntity(model);
        conditions.add(new EqualitySelectCondition(EliteOperand.property(alias(), operand), inEntity, true));

        entity.accept(inEntity);
        
        return this;
    }
    
    /*
    * Equal
    */
    
    public EliteConditionBuilder eq(EliteOperand operand, EliteOperand value) {
        conditions.add(new EqualityCondition(operand, value, true));

        return this;
    }
    
    public EliteConditionBuilder eq(EliteOperand operand, Object value) {
        conditions.add(new EqualityCondition(operand, EliteOperand.value(value), true));

        return this;
    }
    
    public EliteConditionBuilder eq(String operand, EliteOperand value) {
        conditions.add(new EqualityCondition(EliteOperand.property(alias(), operand), value, true));

        return this;
    }
    
    public EliteConditionBuilder eq(String operand, Object value) {
        conditions.add(new EqualityCondition(EliteOperand.property(alias(), operand), EliteOperand.value(value), true));

        return this;
    }
    
    /*
    * Not Equal
    */
    
    public EliteConditionBuilder ne(EliteOperand operand, EliteOperand value) {
        conditions.add(new EqualityCondition(operand, value, false));

        return this;
    }
    
    public EliteConditionBuilder ne(EliteOperand operand, Object value) {
        conditions.add(new EqualityCondition(operand, EliteOperand.value(value), false));

        return this;
    }
    
    public EliteConditionBuilder ne(String operand, EliteOperand value) {
        conditions.add(new EqualityCondition(EliteOperand.property(alias(), operand), value, false));

        return this;
    }
    
    public EliteConditionBuilder ne(String operand, Object value) {
        conditions.add(new EqualityCondition(EliteOperand.property(alias(), operand), EliteOperand.value(value), false));

        return this;
    }
    
    /*
    * IS NULL
    */
    
    public EliteConditionBuilder isNull(EliteOperand operand) {
        conditions.add(new NullityCondition(operand, true));

        return this;
    }
    
    public EliteConditionBuilder isNull(String operand) {
        return isNull(EliteOperand.property(alias(), operand));
    }
    
    
    /*
    * IS Not NULL
    */
    
    public EliteConditionBuilder isNotNull(EliteOperand operand) {
        conditions.add(new NullityCondition(operand, false));

        return this;
    }
    
    public EliteConditionBuilder isNotNull(String operand) {
        return isNotNull(EliteOperand.property(alias(), operand));
    }
    
    /*
    * IS NULL
    */
    
    /*
    * Like
    */

    public EliteConditionBuilder like(EliteOperand operand, Object value) {
        conditions.add(new LikeCondition(operand, value, LikeCondition.Matcher.EXACT));

        return this;
    }
    
    public EliteConditionBuilder like(String operand, Object value) {
        conditions.add(new LikeCondition(EliteOperand.property(alias(), operand), value, LikeCondition.Matcher.EXACT));

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
        
        String finalQuery = wherePart.toString();
        
        finalQuery = finalQuery.replaceAll("\\s{2,}", " ");
        
        System.out.println(finalQuery);
    }
}

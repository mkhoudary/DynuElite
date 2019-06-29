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
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 *
 * @author Mohammed
 */
public class EliteEntity extends EliteConditionBuilder {

    protected final String model;
    protected String alias;
    private List<EliteOperand> selects;
    private List<EliteJoin> joins;

    public EliteEntity(String model) {
        this(model, model);
    }

    public EliteEntity(String model, String alias) {
        this.model = model;
        this.alias = alias;
        this.selects = new ArrayList<>();
        this.joins = new ArrayList<>();
    }

    public EliteEntity custom(ScopedEntityQuery scopedQuery) {
        scopedQuery.addToEntityQuery(this);
        
        return this;
    }
    
    public EliteEntity select(EliteOperand operand) {
        selects.add(operand);

        return this;
    }

    public EliteEntity select(String operand) {
        selects.add(EliteOperand.property(alias(), operand));

        return this;
    }

    public EliteEntity select(EliteOperand... operandsToAdd) {
        selects.addAll(Arrays.asList(operandsToAdd));

        return this;
    }

    public EliteEntity select(String... operandsToAdd) {
        selects.addAll(Arrays.asList(operandsToAdd).stream().map(operandToAdd -> EliteOperand.property(alias(), operandToAdd)).collect(Collectors.toList()));

        return this;
    }

    public EliteEntity join(String model, String alias, Consumer<EliteJoin> join) {
        EliteJoin eliteJoin = new EliteJoin("", model, alias, this);
        joins.add(eliteJoin);

        join.accept(eliteJoin);

        return this;
    }

    public EliteEntity leftJoin(String model, String alias, Consumer<EliteJoin> join) {
        EliteJoin eliteJoin = new EliteJoin("left", model, alias, this);
        joins.add(eliteJoin);

        join.accept(eliteJoin);

        return this;
    }

    public EliteEntity selectWithAlias(String alias, String... operandsToAdd) {
        selects.addAll(Arrays.asList(operandsToAdd).stream().map(operandToAdd -> EliteOperand.property(alias, operandToAdd)).collect(Collectors.toList()));

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
        
        List<EliteOperand> allSelects = selects();

        builder.append("SELECT ");
        if (allSelects.isEmpty()) {
            builder.append(" * ");
        } else {
            builder.append(allSelects.stream().map(operand -> operand.translate()).collect(Collectors.joining(", ")));
        }
        builder.append(" FROM ");
        builder.append(model);
        builder.append(" ");
        builder.append(alias);
        
        List<EliteJoin> allJoins = joins();

        for (int i = 0; i < allJoins.size(); i++) {
            if (i > 0) {
                builder.append(" ");
            }

            allJoins.get(i).translate(builder);
        }

        builder.append(" WHERE ");

        for (int i = 0; i < conditions.size(); i++) {
            if (i > 0) {
                builder.append(" AND ");
            }

            conditions.get(i).translate(builder);
        }

        return builder.toString();
    }

    protected List<EliteOperand> selects() {
        ArrayList<EliteOperand> allSelects = new ArrayList<>(selects);

        joins.forEach((join) -> {
            allSelects.addAll(join.selects());
        });

        return allSelects;
    }

    protected List<EliteJoin> joins() {
        ArrayList<EliteJoin> allJoins = new ArrayList<>(joins);

        joins.forEach((join) -> {
            allJoins.addAll(join.joins());
        });

        return allJoins;
    }
}

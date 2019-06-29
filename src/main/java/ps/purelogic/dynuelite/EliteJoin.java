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
public class EliteJoin extends EliteEntity {

    private final String joinType;
    private final EliteEntity joinedWith;

    public EliteJoin(String joinType, String model, String alias, EliteEntity joinedWith) {
        super(model, alias);
        
        this.joinType = joinType;
        this.joinedWith = joinedWith;
    }

    @Override
    public String translate() {
        StringBuilder builder = new StringBuilder();

        if (joinType != null && !joinType.isEmpty()) {
            builder.append(" ");
            builder.append(joinType);
        }
        
        builder.append(" join ");
        builder.append(model);
        builder.append(" ");
        builder.append(alias);
        builder.append(" on (");

        for (int i = 0; i < conditions.size(); i++) {
            if (i > 0) {
                builder.append(" AND ");
            }

            conditions.get(i).translate(builder);
        }

        builder.append(") ");

        return builder.toString();
    }

    public EliteEntity getJoinedWith() {
        return joinedWith;
    }

}

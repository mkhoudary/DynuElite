/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.dynuelite.conditions;

/**
 *
 * @author Mohammed
 */
public interface Condition {

    String translate();
    
    public default void translate(StringBuilder builder) {
        String translated = translate();
        
        builder.append(translated);
    }

}

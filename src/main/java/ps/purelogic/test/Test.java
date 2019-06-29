/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.test;

import ps.purelogic.dynuelite.Elite;
import ps.purelogic.dynuelite.EliteOperand;

/**
 *
 * @author Mohammed
 */
public class Test {
    public static void main(String[] args) {
        Elite elite = new Elite();
        elite.entity("countries")
                .eq(EliteOperand.property("prop_id"), EliteOperand.value("215"))
                .and(group -> {
                    group.eq(EliteOperand.property("name"), 
                             EliteOperand.value("Palestine"));
                    
                    group.eq(EliteOperand.property("id"), 
                             EliteOperand.value("25"));
                })
                .query();
    }
}

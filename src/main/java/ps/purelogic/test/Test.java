/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.test;

import ps.purelogic.dynuelite.Elite;
import ps.purelogic.dynuelite.EliteOperand;
import ps.purelogic.dynuelite.conditions.LikeCondition;

/**
 *
 * @author Mohammed
 */
public class Test {
    public static void main(String[] args) {
        Elite elite = new Elite();
        elite.entity("countries")
                .like(EliteOperand.property("prop_id"), 215, LikeCondition.Matcher.ENDING)
                .and(group -> {
                    group.eq("name", "Palestine");
                    
                    group.eq("id", 25);
                })
                .query();
    }
}

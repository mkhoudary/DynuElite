/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ps.purelogic.test;

import ps.purelogic.dynuelite.Elite;

/**
 *
 * @author Mohammed
 */
public class Test {
    public static void main(String[] args) {
        Elite elite = new Elite();
        
        elite.entity("countries", "c")
                .select("first_name", "last_name", "age", "country")
                .like("prop_id", "%215%")
                .or(group -> {
                    group.eq("name", "Palestine");
                    group.eq("id", 25);
                })
                .in("last_name", "Mohammed", "Ali", "Fawzi")
                .inSelect("id", "cities", entity -> {
                    entity.select("id")
                          .eq("city_name", "Gaza");
                })
                .query();
    }
}

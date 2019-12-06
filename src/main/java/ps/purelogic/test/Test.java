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

        elite.entity("saf_invoices", "c")
                .join("saf_line_items", "i", join -> {
                    join.eq("invoice_id", EliteOperand.property(join.getJoinedWith().alias(), "id"));
                    
                    join.join("saf_invoice_line_items", "l", otherJoin -> {
                        otherJoin.select("amount");
                        otherJoin.eq("id", EliteOperand.property(otherJoin.getJoinedWith().alias(), "line_item_id"));
                    });
                })
                .select("id", "invoice_number")
                .and(builder -> {
                    builder.isNotNull("invoice_number");
                    builder.ne("invoice_number", "");
                })
                .query();
    }
}

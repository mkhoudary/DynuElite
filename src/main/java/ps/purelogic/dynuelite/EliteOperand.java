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
public abstract class EliteOperand {

    private final Object[] values;

    public EliteOperand(Object ... values) {
        this.values = values;
    }

    public Object[] getValues() {
        return values;
    }

    public void translate(StringBuilder builder) {
        String translated = translate();
        
        builder.append(translated);
    }
    
    public abstract String translate();

    public static EliteOperand property(String alias, String name) {
        return new EliteOperand(alias, name) {

            @Override
            public String translate() {
                StringBuilder builder = new StringBuilder();
                
                builder.append(getValues()[0]);
                builder.append(".");
                builder.append(getValues()[1]);
                
                return builder.toString();
            }
        };
    }

    public static EliteOperand value(Object value) {
        return new EliteOperand(value) {

            @Override
            public String translate() {
                StringBuilder builder = new StringBuilder();
                
                if (getValues()[0] instanceof Number) {
                    builder.append(getValues()[0]);
                } else {
                    builder.append("'");
                    builder.append(getValues()[0]);
                    builder.append("'");
                }
                
                return builder.toString();
            }
        };
    }

}

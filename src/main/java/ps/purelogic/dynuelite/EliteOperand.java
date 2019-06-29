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

    private final Object value;

    public EliteOperand(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public abstract void translate(StringBuilder builder);

    public static EliteOperand property(String name) {
        return new EliteOperand(name) {

            @Override
            public void translate(StringBuilder builder) {
                builder.append(getValue());
            }
        };
    }

    public static EliteOperand value(Object value) {
        return new EliteOperand(value) {

            @Override
            public void translate(StringBuilder builder) {
                if (getValue() instanceof Number) {
                    builder.append(getValue());
                } else {
                    builder.append("'");
                    builder.append(getValue());
                    builder.append("'");
                }
            }
        };
    }

}

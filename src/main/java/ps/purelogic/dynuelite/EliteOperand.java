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

    private final String name;

    public EliteOperand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void translate(StringBuilder builder);

    public static EliteOperand property(String name) {
        return new EliteOperand(name) {

            @Override
            public void translate(StringBuilder builder) {
                builder.append(getName());
            }
        };
    }

    public static EliteOperand value(String value) {
        return new EliteOperand(value) {

            @Override
            public void translate(StringBuilder builder) {
                builder.append("'");
                builder.append(getName());
                builder.append("'");
            }
        };
    }

}

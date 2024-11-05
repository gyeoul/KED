package org.jooq.generator;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class JPrefixGeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        return switch (mode) {
            case DEFAULT -> "J" + super.getJavaClassName(definition, mode);
            case null, default -> super.getJavaClassName(definition, mode);
        };
    }
}

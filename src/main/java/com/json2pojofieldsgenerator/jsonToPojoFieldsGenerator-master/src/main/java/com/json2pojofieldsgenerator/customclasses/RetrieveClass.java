package com.json2pojofieldsgenerator.customclasses;

import com.json2pojofieldsgenerator.customclasses.rules.CustomConstructorRule;
import com.json2pojofieldsgenerator.customclasses.rules.CustomObjectRule;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.JType;
import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.SourceType;
import org.jsonschema2pojo.rules.Rule;
import org.jsonschema2pojo.rules.RuleFactory;
import org.jsonschema2pojo.util.ParcelableHelper;

public class RetrieveClass {

    public static RuleFactory ruleFactory(GenerationConfig config) {
        return new RuleFactory(config, new CustomAnnotator(config), new SchemaStore()) {

            @Override
            public Rule<JDefinedClass, JDefinedClass> getConstructorRule() {
                return new CustomConstructorRule(this, this.getReflectionHelper());
            }

            @Override
            public Rule<JPackage, JType> getObjectRule() {
                return new CustomObjectRule(this, new ParcelableHelper(), this.getReflectionHelper());
            }
        };


    }

    public static GenerationConfig defaultConfig() {
        return new DefaultGenerationConfig() {
            @Override
            public boolean isGenerateBuilders() {
                return false;
            }

            @Override
            public SourceType getSourceType() {
                return SourceType.JSON;
            }

            @Override
            public boolean isIncludeAdditionalProperties() {
                return false;
            }

            @Override
            public boolean isIncludeHashcodeAndEquals() {
                return false;
            }

            @Override
            public boolean isIncludeSetters() {
                return false;
            }

            @Override
            public boolean isIncludeConstructors() {
                return true;
            }

        };

    }
}

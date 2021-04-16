package com.json2pojofieldsgenerator.customclasses.rules;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Schema;
import org.jsonschema2pojo.rules.Rule;
import org.jsonschema2pojo.rules.RuleFactory;
import org.jsonschema2pojo.util.NameHelper;
import org.jsonschema2pojo.util.ReflectionHelper;

public class CustomConstructorRule implements Rule<JDefinedClass, JDefinedClass> {
    private final RuleFactory ruleFactory;
    private final ReflectionHelper reflectionHelper;

    public CustomConstructorRule(RuleFactory ruleFactory, ReflectionHelper reflectionHelper) {
        this.ruleFactory = ruleFactory;
        this.reflectionHelper = reflectionHelper;
    }

    public JDefinedClass apply(String nodeName, JsonNode node, JsonNode parent, JDefinedClass instanceClass, Schema currentSchema) {
        this.handleMultiChoiceConstructorConfiguration(node, instanceClass, currentSchema);
        return instanceClass;
    }

    private void handleMultiChoiceConstructorConfiguration(JsonNode node, JDefinedClass instanceClass, Schema currentSchema) {
        boolean requiresConstructors = false;
        LinkedHashSet<String> requiredClassProperties = null;
        LinkedHashSet<String> classProperties = null;
        LinkedHashSet<String> requiredCombinedSuperProperties = null;
        LinkedHashSet<String> combinedSuperProperties = null;
        GenerationConfig generationConfig = this.ruleFactory.getGenerationConfig();
        boolean includeAllPropertiesConstructor = generationConfig.isIncludeAllPropertiesConstructor();
        boolean includeRequiredPropertiesConstructor = generationConfig.isIncludeRequiredPropertiesConstructor();
        if (includeAllPropertiesConstructor) {
            classProperties = this.getConstructorProperties(node, false);
            combinedSuperProperties = this.getSuperTypeConstructorPropertiesRecursive(node, currentSchema, false);
            requiresConstructors = requiresConstructors || !classProperties.isEmpty() || !combinedSuperProperties.isEmpty();
        }

        if (requiresConstructors) {
            if (classProperties.size() + combinedSuperProperties.size() > 0) {
                this.addFieldsConstructor(instanceClass, classProperties);
            }

            if (includeRequiredPropertiesConstructor && requiredClassProperties.size() + requiredCombinedSuperProperties.size() > 0) {
                this.addFieldsConstructor(instanceClass, requiredClassProperties);
            }
        }

    }

    private void addFieldsConstructor(JDefinedClass instanceClass, Set<String> classProperties) {
        this.generateFieldsConstructor(instanceClass, classProperties);
    }

    private LinkedHashSet<String> getConstructorProperties(JsonNode node, boolean onlyRequired) {
        if (!node.has("properties")) {
            return new LinkedHashSet();
        } else {
            LinkedHashSet<String> rtn = new LinkedHashSet();
            Set<String> draft4RequiredProperties = new HashSet();
            Iterator properties;
            if (onlyRequired && node.has("required")) {
                JsonNode requiredArray = node.get("required");
                if (requiredArray.isArray()) {
                    properties = requiredArray.iterator();

                    while (properties.hasNext()) {
                        JsonNode requiredEntry = (JsonNode) properties.next();
                        if (requiredEntry.isTextual()) {
                            draft4RequiredProperties.add(requiredEntry.asText());
                        }
                    }
                }
            }

            NameHelper nameHelper = this.ruleFactory.getNameHelper();
            properties = node.get("properties").fields();

            while (properties.hasNext()) {
                Entry<String, JsonNode> property = (Entry) properties.next();
                JsonNode propertyObj = property.getValue();
                if (onlyRequired) {
                    if (propertyObj.has("required") && propertyObj.get("required").asBoolean()) {
                        rtn.add(nameHelper.getPropertyName((String) property.getKey(), (JsonNode) property.getValue()));
                    }

                    if (draft4RequiredProperties.contains(property.getKey())) {
                        rtn.add(nameHelper.getPropertyName((String) property.getKey(), (JsonNode) property.getValue()));
                    }
                } else {
                    rtn.add(nameHelper.getPropertyName((String) property.getKey(), (JsonNode) property.getValue()));
                }
            }

            return rtn;
        }
    }

    private LinkedHashSet<String> getSuperTypeConstructorPropertiesRecursive(JsonNode node, Schema schema, boolean onlyRequired) {
        Schema superTypeSchema = this.reflectionHelper.getSuperSchema(node, schema, true);
        if (superTypeSchema == null) {
            return new LinkedHashSet();
        } else {
            JsonNode superSchemaNode = superTypeSchema.getContent();
            LinkedHashSet<String> rtn = this.getConstructorProperties(superSchemaNode, onlyRequired);
            rtn.addAll(this.getSuperTypeConstructorPropertiesRecursive(superSchemaNode, superTypeSchema, onlyRequired));
            return rtn;
        }
    }

    private JMethod generateFieldsConstructor(JDefinedClass jclass, Set<String> classProperties) {
        JMethod fieldsConstructor = jclass.constructor(JMod.PUBLIC);

        JBlock constructorBody = fieldsConstructor.body();

        Map<String, JFieldVar> fields = jclass.fields();
        Map<String, JAnnotationUse> classFieldParams = new HashMap<>();

        for (String property : classProperties) {
            JFieldVar field = fields.get(property);

            if (field == null) {
                throw new IllegalStateException("Property " + property + " hasn't been added to JDefinedClass before calling addConstructors");
            }

            JVar param = fieldsConstructor.param(field.type(), field.name());

            classFieldParams.put(property, param.annotate(JsonProperty.class).param("value", property));

            constructorBody.assign(JExpr._this()
                    .ref(field), param);

        }

        return fieldsConstructor;
    }

}




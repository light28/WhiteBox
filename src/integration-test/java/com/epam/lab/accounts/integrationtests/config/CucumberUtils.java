package com.epam.lab.accounts.integrationtests.config;

import io.cucumber.datatable.DataTable;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.NumberUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import static java.util.Arrays.asList;

public class CucumberUtils {


    public static <T> T toObject(final DataTable dataTable, Class<T> clazz) {

        final ClassReflectUtil<T> targetClassReflection = new ClassReflectUtil<>(clazz);

        final T obj = targetClassReflection.createNewInstance();

        final Map<String, String> data = dataTable.asMap(String.class, String.class);

        for (final Field field : targetClassReflection.getFieldNames()) {

            if (data.containsKey(field.getName())) {
                final String dataTableFieldValue = data.get(field.getName());
                final Object fieldValue;
                if (Enum.class.isAssignableFrom(field.getType()))
                {
                    fieldValue = Enum.valueOf((Class)field.getType(), dataTableFieldValue);
                }
                else if (BigDecimal.class.isAssignableFrom(field.getType()))
                {
                    fieldValue = NumberUtils.parseNumber(dataTableFieldValue, BigDecimal.class);
                }
                else
                {
                    fieldValue = dataTableFieldValue;
                }

                targetClassReflection.setFieldValueForObject(obj, field.getName(), fieldValue);
            }
        }

        return obj;
    }

    private static class ClassReflectUtil<T> {

        private Class<T> clazz;


        public ClassReflectUtil(Class<T> clazz) {
            this.clazz = clazz;
        }

        private T createNewInstance( ) {
            T t = null;
            try {
                t = ReflectionUtils.accessibleConstructor(clazz).newInstance();
            } catch (NoSuchMethodException |
                    IllegalAccessException |
                    InstantiationException |
                    InvocationTargetException e) {
                e.printStackTrace();
            }
            return t;
        }

        public List<Field> getFieldNames() {

            return asList(clazz.getDeclaredFields());
        }

        public void setFieldValueForObject(T obj, String fieldName, Object fieldValue) {

            final Field targetClassField = ReflectionUtils.findField(clazz, fieldName);
            ReflectionUtils.makeAccessible(targetClassField);
            ReflectionUtils.setField(targetClassField, obj, fieldValue);
        }
    }


}

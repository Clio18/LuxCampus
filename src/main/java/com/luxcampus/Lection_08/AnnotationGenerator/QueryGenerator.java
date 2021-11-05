package com.luxcampus.Lection_08.AnnotationGenerator;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.StringJoiner;

public class QueryGenerator {

    public String getAll(Class<?> clazz) {
        // SELECT * FROM table_name;
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        //get the Table name
        String tableName = getTableName(clazz);
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);
            }
        }
        stringBuilder.append(stringJoiner);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(";");
        return stringBuilder.toString();

    }

    // INSERT INTO table_Name//VALUES (....);
    public String insert(Object value) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        if (value == null) {
            throw new NullPointerException("Value must not be null!");
        }
        StringBuilder stringBuilder = new StringBuilder("INSERT INTO ");
        Class clazz = value.getClass();
        String tableName = getTableName(clazz);

        StringJoiner stringJoiner = new StringJoiner(", ");
        StringJoiner stringJoiner2 = new StringJoiner(", ");
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);
                field.setAccessible(true);
                stringJoiner2.add("'" + field.get(value).toString() + "'");
            }
        }

        stringBuilder.append(tableName);
        stringBuilder.append(" (");
        stringBuilder.append(stringJoiner);
        stringBuilder.append(") VALUES ");
        stringBuilder.append("(");
        stringBuilder.append(stringJoiner2);
        stringBuilder.append(");");
        return stringBuilder.toString();

    }

    // UPDATE Customers
    // SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
    // WHERE CustomerID = 1;
    public String update(Object value) throws IllegalAccessException {
        if (value == null) {
            throw new NullPointerException("Value must not be null!");
        }
        StringBuilder stringBuilder = new StringBuilder("UPDATE ");
        Class clazz = value.getClass();
        String tableName = getTableName(clazz);

        StringJoiner stringJoiner = new StringJoiner(", ");

        //it needs to join fields names with their values in format
        // "field_name = field_value, ..."
        StringJoiner stringJoiner2 = new StringJoiner(", ");

        //it needs to join field id with its value in format
        // "field_name = field_value"
        StringBuilder stringBuilder3 = new StringBuilder();

        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            //each time new string "field_name = field_value" will be created
            StringBuilder stringBuilder2 = new StringBuilder();
            if (columnAnnotation != null) {
                if (field.getAnnotation(Id.class)==null) {
                    String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                    field.setAccessible(true);
                    stringBuilder2.append(columnName + " = "+ "'");
                    stringBuilder2.append(field.get(value).toString() + "'");
                    stringJoiner2.add(stringBuilder2.toString());
                } else {
                    String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                    field.setAccessible(true);
                    stringBuilder3.append(columnName + " = " + "'");
                    stringBuilder3.append(field.get(value).toString()+ "'");
                }
            }
        }

        // UPDATE persons SET person_name = " +
        // person.getName() + ", salary =" + person.getSalary() + "WHERE id = " + person.getId() + ";";
        stringBuilder.append(tableName); // UPDATE persons
        stringBuilder.append(" SET "); // UPDATE persons SET
        stringBuilder.append(stringJoiner.add(stringJoiner2.toString())); // UPDATE persons SET ...
        stringBuilder.append(" WHERE "); // UPDATE persons SET ... WHERE
        stringBuilder.append(stringBuilder3); // UPDATE persons SET ... WHERE ...
        stringBuilder.append(";"); // UPDATE persons SET ... WHERE ...;

        return stringBuilder.toString();
    }

    // SELECT id, person_name, salary FROM persons WHERE id =" + person.getId() + ";"
    public String getById(Class clazz, Object id) {
        if (id == null) {
            throw new NullPointerException("Id must not be null!");
        }
        StringBuilder stringBuilder = new StringBuilder("SELECT ");
        String tableName = getTableName(clazz);
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Field field : clazz.getDeclaredFields()) {
            Column columnAnnotation = field.getAnnotation(Column.class);
            if (columnAnnotation != null) {
                String columnName = columnAnnotation.name().isEmpty() ? field.getName() : columnAnnotation.name();
                stringJoiner.add(columnName);
            }
        }
        stringBuilder.append(stringJoiner);
        stringBuilder.append(" FROM ");
        stringBuilder.append(tableName);
        stringBuilder.append(" WHERE id = " + "'" + id.toString() + "'");
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    // DELETE FROM table_name WHERE condition;
    public String delete(Class clazz, Object id) {
        StringBuilder stringBuilder = new StringBuilder("DELETE FROM ");
        if (id == null) {
            throw new NullPointerException("Id must not be null!");
        }

        String tableName = getTableName(clazz);
        stringBuilder.append(tableName);
        stringBuilder.append(" WHERE id = " + "'" + id.toString() + "'");
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

    private String getTableName(Class clazz) {
        Table annotation = (Table) clazz.getAnnotation(Table.class);
        if (annotation == null) {
            throw new IllegalArgumentException("@Table is missing");
        }
        return annotation.name().isEmpty() ? clazz.getName() : annotation.name();
    }
}



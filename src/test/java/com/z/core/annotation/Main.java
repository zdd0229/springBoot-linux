package com.z.core.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static String query(Person person) {
        StringBuilder sb = new StringBuilder();

        Class clazz = person.getClass();
        Class clazz1 = Person.class;

        System.out.println(clazz == clazz1);

        Class stringClazz = String.class;
        boolean exist = clazz.isAnnotationPresent(Table.class);
        if (!exist) {
            return null;
        }

        Table table = (Table) clazz.getAnnotation(Table.class);
        String tableName = table.value();

        //拼接sql
        sb.append("select * from ").append(tableName).append(" where 1=1");
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            boolean fExist = field.isAnnotationPresent(Column.class);
            if (!fExist) {
                return null;
            }

            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            String fieldName = field.getName();

            Object fieldValue = null;
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            try {
                Method method = clazz.getMethod(getMethodName);
                fieldValue = method.invoke(person);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.append(" and ").append(columnName).append("=").append(fieldValue);

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.name = "赵丁丁";
        p.userName = "zdd";

        String sql = query(p);
        System.out.println(sql);
    }

}

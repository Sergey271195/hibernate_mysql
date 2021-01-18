package utils;

import javax.persistence.OneToMany;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StandardMethodGenerator {

    public static String generateToStringMethod(Object obj) {
        List<Field> allFields = new ArrayList<>(List.of(obj.getClass().getDeclaredFields()));
        allFields.addAll(
                obj.getClass().getSuperclass() != null
                        ? List.of(obj.getClass().getSuperclass().getDeclaredFields())
                        : Collections.emptyList()
        );
        String declaredFields = allFields.stream()
                .filter(field -> field.getAnnotation(OneToMany.class) == null)
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return field.getName() + " = " + field.get(obj);
                    } catch (IllegalAccessException e) { return ""; }
                })
                .collect(Collectors.joining(", "));
        return obj.getClass().getSimpleName() + '[' + declaredFields + ']';
    }

    public static boolean generateEqualsMethod(Object o1, Object o2, String ...fields) {
        if (o1 == o2) return true;
        if (o2 == null || !(o1.getClass() == o2.getClass())) return false;
        for (String field : fields) {
            try {
                Field field1 = o1.getClass().getDeclaredField(field);
                Field field2 = o2.getClass().getDeclaredField(field);
                field1.setAccessible(true);
                field2.setAccessible(true);
                try {
                    if (!Objects.equals(field1.get(o1), field2.get(o2))) return false;
                } catch (IllegalAccessException e) {
                    System.out.println("IllegalAccessException for field : " +  field +
                            ". In object : " + o1.getClass().getSimpleName());
                }
            } catch (NoSuchFieldException e) {
                System.out.println("NoSuchFieldException for field : " +  field +
                        ". In object : " + o1.getClass().getSimpleName());
            }
        }
        return true;
    }

}

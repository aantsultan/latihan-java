package com.latihan.java.multiple.database.helper;

import com.latihan.java.multiple.database.exception.HelperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

@Component
public class DtoHelper {

    private static final Logger log = LoggerFactory.getLogger(DtoHelper.class);

    public <S, T> T toDto(S source, Class<T> clazz) {
        try {
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            T target = declaredConstructor.newInstance();
            Class<?> sClazz = source.getClass();
            Field[] targetFields = clazz.getDeclaredFields();
            for (Field targeField : targetFields) {
                targeField.setAccessible(true);
                try {
                    Field sFields = sClazz.getDeclaredField(targeField.getName());
                    sFields.setAccessible(true);

                    Object sO = sFields.get(source);
                    Class<?> sType = sFields.getType();
                    if (sType.isPrimitive()) {
                        if (sType.isAssignableFrom(long.class)) {
                            targeField.set(target, sO == null ? 0 : sO);
                        }
                    } else {
                        if (sType.isAssignableFrom(String.class)) {
                            targeField.set(target, sO == null ? null : sO.toString());
                        }
                    }
                } catch (NoSuchFieldException e) {
                    log.trace(e.getMessage(), e);
                }
            }
            return target;
        } catch (Exception e) {
            throw new HelperException(e.getMessage(), e);
        }
    }

}

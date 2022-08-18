package cn.fantasticmao.mundo.data.jdbc;

import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.data.util.ReflectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RoutingSeedExtractor
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-18
 */
final class RoutingSeedExtractor {
    private static final ConcurrentHashMap<Class<?>, Optional<Field>> DOMAIN_CLASS_SEED_FIELD_CACHE
        = new ConcurrentHashMap<>(32);
    private static final ConcurrentHashMap<Class<?>, Method> DOMAIN_CLASS_SEED_GETTER_CACHE
        = new ConcurrentHashMap<>(32);

    @Nullable
    public static Object fromMethodArguments(Object[] arguments, Annotation[][] annotations) {
        if (arguments.length != annotations.length) {
            return null;
        }

        int x = -1;
        point:
        for (int i = 0; i < annotations.length; i++) {
            for (int j = 0; j < annotations[i].length; j++) {
                if (annotations[i][j] instanceof RoutingSeed) {
                    x = i;
                    break point;
                }
            }
        }
        return x < 0 ? null : arguments[x];
    }

    @Nullable
    public static RoutingSeed fromMethodDeclaration(Method method) {
        MergedAnnotation<RoutingSeed> seedAnnotation = MergedAnnotations.from(method)
            .get(RoutingSeed.class);
        return seedAnnotation.isPresent() ? seedAnnotation.synthesize() : null;
    }

    @Nullable
    public static RoutingSeed fromClassDeclaration(Class<?> clazz) {
        MergedAnnotation<RoutingSeed> seedAnnotation = MergedAnnotations.from(clazz)
            .get(RoutingSeed.class);
        return seedAnnotation.isPresent() ? seedAnnotation.synthesize() : null;
    }

    @Nullable
    public static Object fromDomainFields(Object[] arguments, Class<?> domainClass)
        throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Field seedField = getDomainAnnotatedField(domainClass);
        if (seedField == null) {
            return null;
        }

        int x = -1;
        for (int i = 0; i < arguments.length; i++) {
            Object argument = arguments[i];
            if (domainClass.isInstance(argument)) {
                x = i;
                break;
            }
        }
        if (x < 0) {
            return null;
        }
        return getDomainGetterMethod(seedField, domainClass).invoke(arguments[x]);
    }

    @Nullable
    private static Field getDomainAnnotatedField(Class<?> domainClass) {
        Optional<Field> optional = DOMAIN_CLASS_SEED_FIELD_CACHE.get(domainClass);
        if (optional.isPresent()) {
            return optional.get();
        }

        Field seedField = ReflectionUtils.findField(domainClass, new ReflectionUtils.DescribedFieldFilter() {
            @Nonnull
            @Override
            public String getDescription() {
                return "@RoutingSeed must be unique";
            }

            @Override
            public boolean matches(@Nonnull Field field) {
                return field.isAnnotationPresent(RoutingSeed.class);
            }
        }, true);
        DOMAIN_CLASS_SEED_FIELD_CACHE.put(domainClass, Optional.ofNullable(seedField));
        return seedField;
    }

    @Nonnull
    private static Method getDomainGetterMethod(@Nonnull Field seedField, Class<?> domainClass)
        throws IntrospectionException {
        Method getter = DOMAIN_CLASS_SEED_GETTER_CACHE.get(domainClass);
        if (getter != null) {
            return getter;
        }
        getter = new PropertyDescriptor(seedField.getName(), domainClass).getReadMethod();
        DOMAIN_CLASS_SEED_GETTER_CACHE.put(domainClass, getter);
        return getter;
    }
}

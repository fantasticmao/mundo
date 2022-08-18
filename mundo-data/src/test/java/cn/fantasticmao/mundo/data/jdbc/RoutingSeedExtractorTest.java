package cn.fantasticmao.mundo.data.jdbc;

import cn.fantasticmao.mundo.core.support.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * RoutingSeedExtractorTest
 *
 * @author fantasticmao
 * @version 1.0.6
 * @since 2022-08-18
 */
public class RoutingSeedExtractorTest {

    @Test
    public void fromMethodArguments() throws NoSuchMethodException {
        Method method = UserRepository.class.getMethod("findById", Number.class);
        Object seedObj = RoutingSeedExtractor.fromMethodArguments(Constant.Arrays.OBJECTS,
            method.getParameterAnnotations());
        Assertions.assertNull(seedObj);

        seedObj = RoutingSeedExtractor.fromMethodArguments(new Object[]{1},
            method.getParameterAnnotations());
        Assertions.assertNotNull(seedObj);
        Assertions.assertEquals(1, seedObj);

        method = UserRepository.class.getMethod("findBob");
        seedObj = RoutingSeedExtractor.fromMethodArguments(Constant.Arrays.OBJECTS,
            method.getParameterAnnotations());
        Assertions.assertNull(seedObj);
    }

    @Test
    public void fromMethodDeclaration() throws NoSuchMethodException {
        Method method = UserRepository.class.getMethod("findById", Number.class);
        RoutingSeed seedAnnotation = RoutingSeedExtractor.fromMethodDeclaration(method);
        Assertions.assertNull(seedAnnotation);

        method = UserRepository.class.getMethod("findBob");
        seedAnnotation = RoutingSeedExtractor.fromMethodDeclaration(method);
        Assertions.assertNotNull(seedAnnotation);
        Assertions.assertEquals("2", seedAnnotation.value());
    }

    @Test
    public void fromClassDeclaration() {
        Class<?> clazz = UserRepository.class;
        RoutingSeed seedAnnotation = RoutingSeedExtractor.fromClassDeclaration(clazz);
        Assertions.assertNotNull(seedAnnotation);
        Assertions.assertEquals("1", seedAnnotation.value());
    }

    @Test
    public void fromDomainFields() throws InvocationTargetException, IllegalAccessException {
        Class<?> domainType = UserRepository.User.class;
        Object seedObj = RoutingSeedExtractor.fromDomainFields(Constant.Arrays.OBJECTS, domainType);
        Assertions.assertNull(seedObj);
        // todo
    }
}

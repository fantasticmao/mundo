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
    public void fromDomainFields() throws InvocationTargetException, IllegalAccessException {
        Class<?> domainType = Object.class;
        Object seedObj = RoutingSeedExtractor.fromDomainFields(Constant.Arrays.OBJECTS, domainType);
        Assertions.assertNull(seedObj);

        domainType = UserRepository.User.class;
        seedObj = RoutingSeedExtractor.fromDomainFields(Constant.Arrays.OBJECTS, domainType);
        Assertions.assertNull(seedObj);

        int seed = 66;
        UserRepository.User user = new UserRepository.User();
        user.setId(seed);
        seedObj = RoutingSeedExtractor.fromDomainFields(new Object[]{user}, domainType);
        Assertions.assertNotNull(seedObj);
        Assertions.assertEquals(seed, seedObj);
    }

    @Test
    public void fromMethodArguments() throws NoSuchMethodException {
        Method method = UserRepository.class.getMethod("findById", Number.class);
        Object seedObj = RoutingSeedExtractor.fromMethodArguments(Constant.Arrays.OBJECTS,
            method.getParameterAnnotations());
        Assertions.assertNull(seedObj);

        int seed = 66;
        seedObj = RoutingSeedExtractor.fromMethodArguments(new Object[]{seed},
            method.getParameterAnnotations());
        Assertions.assertNotNull(seedObj);
        Assertions.assertEquals(seed, seedObj);

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

}

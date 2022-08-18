package cn.fantasticmao.mundo.data.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/**
 * RoutingSeedExtractorTest
 *
 * @author maodaohe
 * @since 2022-08-18
 */
public class RoutingSeedExtractorTest {

    @Test
    public void fromMethodArguments() {
        // todo
    }

    @Test
    public void fromMethodDeclaration() throws NoSuchMethodException {
        Method method = UserRepository.class.getMethod("findUserById", Integer.class);
        RoutingSeed annotation = RoutingSeedExtractor.fromMethodDeclaration(method);
        Assertions.assertNull(annotation);

        method = UserRepository.class.getMethod("findBob");
        annotation = RoutingSeedExtractor.fromMethodDeclaration(method);
        Assertions.assertNotNull(annotation);
        Assertions.assertEquals("2", annotation.value());
    }

    @Test
    public void fromClassDeclaration() {
        RoutingSeed annotation = RoutingSeedExtractor.fromClassDeclaration(UserRepository.class);
        Assertions.assertNotNull(annotation);
        Assertions.assertEquals("1", annotation.value());
    }

    @Test
    public void fromDomainFields() {
        // todo
    }
}

package cn.fantasticmao.mundo.web.interceptor;

import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AnnotationInterceptor
 *
 * @author maodh
 * @version 1.0
 * @since 2017/8/3
 */
abstract class AnnotationInterceptor<A extends Annotation> extends AbstractInterceptor {

    /**
     * 获取执行方法的注解，以及其当前类和祖先类的注解
     */
    Queue<A> getMethodAnnotationQueue(Object handler, Class<A> annotationClass) {
        if (handler != null && annotationClass != null && handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Queue<A> queue = new LinkedList<>();
            Method method = handlerMethod.getMethod();

            // 获取当前方法的注解
            A annotation = method.getAnnotation(annotationClass);
            addAnnotation(queue, annotation);

            // 获取当前类的注解
            Class<?> currentClass = method.getDeclaringClass();
            annotation = currentClass.getAnnotation(annotationClass);
            addAnnotation(queue, annotation);

            // 获取祖先类的注解
            Class<?>[] classes = getSuperClasses(currentClass);
            for (Class<?> clazz : classes) {
                annotation = clazz.getAnnotation(annotationClass);
                addAnnotation(queue, annotation);
            }
            return queue;
        }
        return null;
    }

    private void addAnnotation(Queue<A> queue, A a) {
        if (a != null) {
            queue.offer(a);
        }
    }

    /**
     * 获取祖先类数组
     */
    private Class<?>[] getSuperClasses(Class<?> clazz) {
        if (clazz != null) {
            List<Class<?>> list = new ArrayList<>();
            Class<?> currentClass = clazz.getSuperclass();
            while (currentClass != null) {
                list.add(currentClass);
                currentClass = currentClass.getSuperclass();
            }
            return list.toArray(new Class<?>[list.size()]);
        }
        return new Class<?>[0];
    }

    abstract boolean processAnnotationQueue(Queue<A> queue);

}

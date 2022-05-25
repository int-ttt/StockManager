package net.intt.stock.client.Mod.Event;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;

public class EventListener {
    public static EventListener getListener() {
        if (instance == null) {
            instance = new EventListener();
        }
        return instance;
    }
    private static EventListener instance;

    public void registerListener(Class<? extends Listener> event) throws Exception {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath()).setScanners(
                        new MethodAnnotationsScanner()));

        for (Method str : event.getClass().getMethods()) {
            for (Method str1 : reflections.getMethodsAnnotatedWith(EventHandler.class)) {
                if (str.equals(str1)) {
                    str1.invoke(null);
                }
            }
        }
    }

//    public static void runAllAnnotatedWith(Class<? extends Annotation> annotation) throws Exception {
//        Reflections reflections = new Reflections(new ConfigurationBuilder()
//                .setUrls(ClasspathHelper.forJavaClassPath()).setScanners(
//                        new MethodAnnotationsScanner()));
//        Set<Method> methods = reflections.getMethodsAnnotatedWith(annotation);
//
//        for (Method m : methods) {
//            m.invoke(null); // for simplicity, invoking static methods without parameters
//        }
//    }
}

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

import net.intt.stock.client.Mod.EventHandler;
import net.intt.stock.client.Mod.Listener;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class tse implements Listener {
    public static void main(String[] args) throws Exception {
        runAllAnnotatedWith(new tse());
    }

    public static void runAllAnnotatedWith(Listener event) throws Exception {
        Method[] methods = event.getClass().getMethods();

        Set<Method> methods1 = new HashSet<>(Arrays.asList(methods));
        System.out.println(methods1);

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forJavaClassPath()).setScanners(
                        new MethodAnnotationsScanner()));
        Set<Method> methods2 = reflections.getMethodsAnnotatedWith(mod.class);
        System.out.println(methods2);

        Set<Method> meth = new HashSet<>();

        for (Method str : methods1) {
            for (Method str1 : methods2) {
                if (str.equals(str1)) {
                    meth.add(str1);
                }
            }
        }

        for (Method method : meth) {
            method.invoke(null);
        }

    }

    @mod
    public static void calledcs() {
        System.out.println("called");
    }

    @mod
    public static void calledcs2() {
        System.out.println("called2");
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface mod {}
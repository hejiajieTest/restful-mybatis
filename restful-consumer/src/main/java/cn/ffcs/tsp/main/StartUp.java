package cn.ffcs.tsp.main;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class StartUp
{
  public static void main(String[] args)
    throws MalformedURLException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
  {
    System.out.println("======================================");
    System.out.println("starting......");
    System.out.println("======================================");
    String excp = System.getProperty("paas.classpath");
    String classPath = System.getenv("classpath");
    String[] pathes = excp.split(";");
    List urls = new ArrayList();

    for (String cp : pathes) {
      File cpdir = new File(cp);
      if ((!cpdir.exists()) || (!cpdir.isDirectory())) {
        System.err
          .println("Please set system property \"paas.classpath\" properly, pointed to local directories, seperated by ';'!");
      }
      else {
        getJars(urls, cpdir);
      }
    }
    URL[] urlArray = new URL[urls.size()];
    for (int i = 0; i < urlArray.length; i++) {
      urlArray[i] = ((URL)urls.get(i));
    }

    URLClassLoader cl = new URLClassLoader(urlArray);
    Thread.currentThread().setContextClassLoader(cl);

    Class clz = Class.forName("cn.ffcs.tsp.main.StartConsumerJetty", false, cl);
    Method method = clz.getDeclaredMethod("getInstance", new Class[0]);
    method.setAccessible(true);
    Object instance = method.invoke(clz, new Object[0]);
    Method m = clz.getDeclaredMethod("startJetty", new Class[0]);
    m.invoke(instance, new Object[0]);
  }

  private static void getJars(List<URL> urls, File cpdir) throws MalformedURLException
  {
    System.out.println("Loading JAR remote from " + cpdir);

    for (File f : cpdir.listFiles()) {
      if (f.isDirectory())
      {
        getJars(urls, f);
      }
      if ((f.getName().endsWith(".jar")) && (f.getName().indexOf("tuscany-sca-all-") == -1)) {
        URL url = f.toURI().toURL();
        urls.add(url);
        System.out.println("\t-->Append " + f.getName());
      }
    }
  }
}
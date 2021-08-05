package 测试;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader{

    String fileUrl = "D:\\下载\\极客时间\\week01-jvm\\Hello.xlass";

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> hello = classLoader.findClass("Hello");
        Method hello1 = hello.getMethod("hello");
        hello1.invoke(hello.newInstance());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = findAndParseByte(fileUrl);
        return defineClass(name, bytes, 0, bytes.length);
    }

    public byte[] findAndParseByte(String url){
        File file = new File(url);
        byte[] source = new byte[(int)file.length()];
        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < source.length; i++) {
            source[i] = (byte) (255 - source[i]);
        }
        return source;
    }
}

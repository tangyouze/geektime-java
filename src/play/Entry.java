package play;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// C:\Users\tyz\.jdks\azul-1.8.0_322\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.2\lib\idea_rt.jar=12667:C:\Program Files\JetBrains\IntelliJ IDEA 2021.3.2\bin" -Dfile.encoding=UTF-8 -classpath C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\cat.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\charsets.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\access-bridge-64.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\cldrdata.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\crs-agent.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\dnsns.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\jaccess.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\legacy8ujsse.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\localedata.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\nashorn.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\openjsse.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\sunec.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\sunjce_provider.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\sunmscapi.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\sunpkcs11.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\ext\zipfs.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\jce.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\jfr.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\jsse.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\management-agent.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\resources.jar;C:\Users\tyz\.jdks\azul-1.8.0_322\jre\lib\rt.jar;C:\Users\tyz\java\geektime\out\production\geektime play.Entry
//Hello@28d93b30
//class Hello
//methods
//[Ljava.lang.reflect.Method;@4554617c
//0 hello
//1
//Hello, classLoader!
//null
public class Entry extends ClassLoader {

    public static void main(String[] args) {
//        System.out.println("world");
//        List<Byte> res = new ArrayList<>();
//        try {
////            File myObj = new File("src/play/Hello.xlass");
//            Path path = Paths.get("src/play/Hello.xlass");
//
//            byte[] bytes = Files.readAllBytes(path);
//
//            for (byte b : bytes) {
//                b = (byte) (255 - b);
//                System.out.println(b);
//                res.add(b);
//            }
////            System.out.println(bytes);
////            Scanner myReader = new Scanner(myObj);
////            while (myReader.hasNextByte()) {
////                byte b = myReader.nextByte();
////                System.out.println(b);
////            }
////            System.out.println("close");
////            myReader.close();
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        System.out.println(res);
//        System.out.println(res.toArray());

        try {
            Class cls = new Entry().findClass("Hello");
            Object obj = cls.newInstance();
            System.out.println(obj);
            System.out.println(obj.getClass());
            Method[] methodList = cls.getDeclaredMethods();
            System.out.println("methods");
            System.out.println(methodList);
            for (int i = 0; i < methodList.length; i++) {
                System.out.println("" + i + " " + methodList[i].getName());
            }
            System.out.println(methodList.length);
            System.out.println(methodList[0].invoke(obj));
//            Method method = new Entry().findClass("Hello").getMethod("Hello.Hello");
//            method.invoke(obj);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String b = "helllasdkfjasdf";
//        byte[] bytes = []byte{"a"};
//        byte[] bytes;
//        byte[] CDRIVES = HexFormat.of().parseHex("e04fd020ea3a6910a2d808002b30309d");
//        byte[] bytes = hexStringToByteArray("e04fd020ea3a6910a2d808002b30309d");

//        List<Byte> res = new ArrayList<>();
//        try {
//            File myObj = new File("src/play/Hello.xlass");
        Path path = Paths.get("src/play/Hello.xlass");

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] res = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            b = (byte) (255 - b);
//            System.out.println(b);
            res[i] = b;
        }
//            System.out.println(bytes);
//            Scanner myReader = new Scanner(myObj);
//            while (myReader.hasNextByte()) {
//                byte b = myReader.nextByte();
//                System.out.println(b);
//            }
//            System.out.println("close");
//            myReader.close();
        return defineClass(name, res, 0, res.length);
//        System.out.println(res);

    }
}

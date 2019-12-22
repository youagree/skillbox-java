import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        FileAccess fileAccess = new FileAccess("48c4cfa049db", "8020");
//        fileAccess.create("test3/test4/file5.txt");
//        fileAccess.create("test2/file2.txt");
//        fileAccess.create("test2/file3.txt");
//        fileAccess.append("file.txt", "test_content");
        try {
            List<String> list = fileAccess.getFilesList("test/");
            list.stream().forEach(System.out::println);
        } finally {
            fileAccess.close();
        }
//        fileAccess.isDirectory("test");
//        System.out.println(fileAccess.read("file.txt"));
//        fileAccess.delete("file.txt");
    }
}

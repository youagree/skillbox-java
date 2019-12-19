
public class Main {

    public static void main(String[] args) throws Exception {
        FileAccess fileAccess = new FileAccess("48c4cfa049db", "8020");
        fileAccess.create("test/file.txt");
        fileAccess.append("file.txt", "test_content");
        fileAccess.getFilesList("test/file.txt");
        fileAccess.isDirectory("test");
        System.out.println(fileAccess.read("file.txt"));
        fileAccess.delete("file.txt");
    }
}

public class Main {

    public static void main(String[] args) throws Exception {
        FileAccess fileAccess = new FileAccess("21fbb152a940a37", "8020");
        fileAccess.create("test/file.txt");
        fileAccess.append("/test.txt", "test_content");
        fileAccess.getFilesList("test/file.txt");
        fileAccess.isDirectory("test");
        System.out.println(fileAccess.read("test/file.txt"));
        fileAccess.delete("test/file.txt");
    }
}

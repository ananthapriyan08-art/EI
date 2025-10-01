// Component Interface
interface FileSystemComponent {
    void showDetails(String indent);
}

// Leaf Class - File
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    public void showDetails(String indent) {
        System.out.println(indent + "- File: " + name);
    }
}

// Composite Class - Folder
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> children = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void add(FileSystemComponent component) {
        children.add(component);
    }

    public void showDetails(String indent) {
        System.out.println(indent + "+ Folder: " + name);
        for (FileSystemComponent child : children) {
            child.showDetails(indent + "  ");
        }
    }
}

// Client Code
public class CompositeFileSystem {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("Resume.pdf");
        FileSystemComponent file2 = new File("Budget.xlsx");
        FileSystemComponent file3 = new File("Presentation.pptx");

        Folder projectFolder = new Folder("Project");
        projectFolder.add(file1);
        projectFolder.add(file2);

        Folder rootFolder = new Folder("Documents");
        rootFolder.add(projectFolder);
        rootFolder.add(file3);

        rootFolder.showDetails("");
    }
}

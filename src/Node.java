import java.io.File;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class Node {
    private final File folder;
    private final ArrayList<Node> children;
    private long size;
    private int level = 0;
    private long limit;

    public Node(File folder, long limit)
    {
        this(folder);
        this.limit = limit;
    }

    public Node(File folder)
    {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public File getFolder() {
        return folder;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        node.setLimit(limit);
        children.add(node);
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String sizeOf = SizeCalculator.getHumanReadableFileSize(getSize());
        builder.append(folder.getName()).append(" - ").append(sizeOf).append(System.lineSeparator());
        for(Node child : children) {
            if(child.getSize() < limit) {
                continue;
            }
            builder.append("  ".repeat(level)).append(child);
        }
        return builder.toString();
    }
}

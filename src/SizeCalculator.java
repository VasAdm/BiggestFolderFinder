import java.text.DecimalFormat;
import java.util.HashMap;

public class SizeCalculator {
    private static HashMap<String, Integer> char2multiplier = new HashMap<>();
    private static final String[] units = new String[] { "bytes", "KB", "MB", "GB", "TB", "PB", "EB" };

    public static String getHumanReadableFileSize(long size) {
        if (size <= 1) return size + " byte";
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.##").format(size / Math.pow(1024, digitGroups))
                + " " + units[digitGroups];
    }

    public static long getSizeFromHumanReadable(String size) {
        char2multiplier = getMultipliers();
        String sizeFactor = size
                .replaceAll("[0-9\\s+]+", "");
        int multiplier = char2multiplier.get(sizeFactor);
        return multiplier * Long.parseLong(size.replaceAll("[^0-9]", ""));
    }

    private static HashMap<String, Integer> getMultipliers() {
        for (int i = 0; i < units.length; i++) {
            char2multiplier.put(units[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
    }
}

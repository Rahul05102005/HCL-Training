package collections;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Loads the simple unquoted CSV format id,name,category,price. */
public final class CsvProductLoader {
    public List<Product> load(Path path) throws IOException {
        if (path == null) throw new IllegalArgumentException("CSV path cannot be null.");
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String header = reader.readLine();
            if (header == null || !header.trim().equalsIgnoreCase("id,name,category,price")) {
                throw new IOException("Invalid or missing CSV header in " + path + "; expected id,name,category,price.");
            }
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.isBlank()) continue;
                String[] fields = line.split(",", -1);
                if (fields.length != 4) throw malformed(path, lineNumber, "expected 4 comma-separated fields");
                try {
                    int id = Integer.parseInt(fields[0].trim());
                    double price = Double.parseDouble(fields[3].trim());
                    products.add(new Product(id, fields[1], fields[2], price));
                } catch (IllegalArgumentException exception) {
                    throw malformed(path, lineNumber, exception.getMessage(), exception);
                }
            }
        }
        return List.copyOf(products);
    }

    private static IOException malformed(Path path, int line, String reason) {
        return malformed(path, line, reason, null);
    }
    private static IOException malformed(Path path, int line, String reason, Exception cause) {
        return new IOException("Malformed product CSV at " + path + ", line " + line + ": " + reason, cause);
    }
}

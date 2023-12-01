package gx.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public static String readFile(String filepath) {

        String data = null;

        try {
            Path path =  Paths.get(FileReader.class.getClassLoader()
                    .getResource(filepath).toURI());
            try (Stream<String> lines = Files.lines(path)) {
                data = lines.collect(Collectors.joining("\n"));
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}

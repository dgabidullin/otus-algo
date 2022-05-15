import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.util.Preconditions;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class OtusAlgoArgumentProvider implements ArgumentsProvider, AnnotationConsumer<OtusAlgoDataSource> {

    private String path;

    private final Map<Integer, TestData> mapOfData = new HashMap<>();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        File folder = new File(path);
        for (var file : Preconditions.notNull(folder.listFiles(), "must be not null directory")) {
            if (file.isFile()) {
                if (file.getName().endsWith(".in")) {
                    putInputData(file);
                }
                if (file.getName().endsWith(".out")) {
                    putExpectedData(file);
                }
            }
        }
        return mapOfData.values().stream().map(testData -> Arguments.of(testData.input, testData.expected));
    }

    @Override
    public void accept(OtusAlgoDataSource otusAlgoDataSource) {
        path = otusAlgoDataSource.path();
    }

    @Override
    public Consumer<OtusAlgoDataSource> andThen(Consumer<? super OtusAlgoDataSource> after) {
        return AnnotationConsumer.super.andThen(after);
    }

    private String readFileToStringWithTrim(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8).trim();
    }

    private void putInputData(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestData data = mapOfData.getOrDefault(testNumber, new TestData());
        data.setInput(Integer.parseInt(readFileToStringWithTrim(file)));
        mapOfData.put(testNumber, data);
    }

    private void putExpectedData(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestData data = mapOfData.getOrDefault(testNumber, new TestData());
        data.setExpected(Long.parseLong(FileUtils.readFileToString(file, StandardCharsets.UTF_8).trim()));
        mapOfData.put(testNumber, data);
    }

    private int getTestNumberFromFile(File file) {
        String[] fileNameSplit = file.getName().split("\\.");
        return Integer.parseInt(fileNameSplit[1]);
    }

    private static class TestData {
        private Integer input;
        private Long expected;

        public Integer getInput() {
            return input;
        }

        public void setInput(Integer input) {
            this.input = input;
        }

        public Long getExpected() {
            return expected;
        }

        public void setExpected(Long expected) {
            this.expected = expected;
        }
    }
}

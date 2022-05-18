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

    private final static String OTUS_FORMAT_FILE_DELIMITER = "\\.";

    private final static String OTUS_FORMAT_FILE_END_LINE_DELIMITER = "\\r\\n";

    private final Map<Integer, Data> mapOfData = new HashMap<>();

    private String path;

    private boolean twoInputArguments;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        File folder = new File(path);
        for (var file : Preconditions.notNull(folder.listFiles(), "must be not null directory")) {
            if (file.isFile()) {
                if (file.getName().endsWith(".in")) {
                    putInputData(file);
                } else if (file.getName().endsWith(".out")) {
                    putExpectedData(file);
                }
            }
        }
        return toStream(mapOfData);
    }

    @Override
    public void accept(OtusAlgoDataSource otusAlgoDataSource) {
        path = otusAlgoDataSource.path();
        twoInputArguments = otusAlgoDataSource.twoInputArguments();
    }

    @Override
    public Consumer<OtusAlgoDataSource> andThen(Consumer<? super OtusAlgoDataSource> after) {
        return AnnotationConsumer.super.andThen(after);
    }

    private Stream<? extends Arguments> toStream(Map<Integer, Data> mapOfData) {
        if (twoInputArguments) {
            return mapOfData.values().stream().map(TestTwoInputData.class::cast).map(testTwoInputData -> Arguments.of(testTwoInputData.input1, testTwoInputData.input2, testTwoInputData.expected));
        }
        return mapOfData.values().stream().map(TestData.class::cast).map(testData -> Arguments.of(testData.input, testData.expected));
    }

    private void putInputData(File file) throws IOException {
        if (twoInputArguments) {
            putTwoInputDataInternal(file);
        } else {
            putInputDataInternal(file);
        }
    }

    private void putExpectedData(File file) throws IOException {
        if (twoInputArguments) {
            putTwoInputExpectedDataInternal(file);
        } else {
            putExpectedDataInternal(file);
        }
    }

    private void putInputDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestData data = (TestData) mapOfData.getOrDefault(testNumber, new TestData());
        data.setInput(Integer.parseInt(readFileToStringWithTrim(file)));
        mapOfData.put(testNumber, data);
    }

    private void putTwoInputDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestTwoInputData data = (TestTwoInputData) mapOfData.getOrDefault(testNumber, new TestTwoInputData());
        String[] content = readFileToStringWithTrim(file).split(OTUS_FORMAT_FILE_END_LINE_DELIMITER);
        data.setInput1(Double.parseDouble(content[0]));
        data.setInput2(Long.parseLong(content[1]));
        mapOfData.put(testNumber, data);
    }

    private void putExpectedDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestData data = (TestData) mapOfData.getOrDefault(testNumber, new TestData());
        data.setExpected(Long.parseLong(readFileToStringWithTrim(file)));
        mapOfData.put(testNumber, data);
    }

    private void putTwoInputExpectedDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestTwoInputData data = (TestTwoInputData) mapOfData.getOrDefault(testNumber, new TestTwoInputData());
        data.setExpected(Double.parseDouble(readFileToStringWithTrim(file)));
        mapOfData.put(testNumber, data);
    }

    private String readFileToStringWithTrim(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8).trim();
    }

    private int getTestNumberFromFile(File file) {
        String[] fileNameSplit = file.getName().split(OTUS_FORMAT_FILE_DELIMITER);
        return Integer.parseInt(fileNameSplit[1]);
    }

    private sealed interface Data permits TestData, TestTwoInputData {
    }

    private static final class TestData implements Data {
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

    private static final class TestTwoInputData implements Data {
        private double input1;
        private Long input2;
        private double expected;

        public double getInput1() {
            return input1;
        }

        public void setInput1(double input1) {
            this.input1 = input1;
        }

        public Long getInput2() {
            return input2;
        }

        public void setInput2(Long input2) {
            this.input2 = input2;
        }

        public double getExpected() {
            return expected;
        }

        public void setExpected(double expected) {
            this.expected = expected;
        }
    }
}

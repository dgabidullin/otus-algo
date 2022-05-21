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

    private boolean twoOutputArguments;

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
        twoOutputArguments = otusAlgoDataSource.twoOutputStringArguments();
    }

    @Override
    public Consumer<OtusAlgoDataSource> andThen(Consumer<? super OtusAlgoDataSource> after) {
        return AnnotationConsumer.super.andThen(after);
    }

    private Stream<? extends Arguments> toStream(Map<Integer, Data> mapOfData) {
        if (twoInputArguments) {
            return mapOfData.values().stream().map(TestTwoInputData.class::cast).map(testTwoInputData -> Arguments.of(testTwoInputData.input1, testTwoInputData.input2, testTwoInputData.expected));
        }
        if (twoOutputArguments) {
            return mapOfData.values().stream().map(StringTwoOutputData.class::cast).map(stringTwoOutputData -> Arguments.of(stringTwoOutputData.input, stringTwoOutputData.expected1, stringTwoOutputData.expected2));
        }
        return mapOfData.values().stream().map(TestData.class::cast).map(testData -> Arguments.of(testData.input, testData.expected));
    }

    private void putInputData(File file) throws IOException {
        if (twoInputArguments) {
            putTwoInputDataInternal(file);
        } else if (twoOutputArguments) {
            putInputStringDataInternal(file);
        } else {
            putInputDataInternal(file);
        }
    }

    private void putExpectedData(File file) throws IOException {
        if (twoInputArguments) {
            putTwoInputExpectedDataInternal(file);
        } else if (twoOutputArguments) {
            putTwoOutputStringExpectedDataInternal(file);
        } else {
            putExpectedDataInternal(file);
        }
    }

    private void putInputDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestData data = (TestData) mapOfData.getOrDefault(testNumber, new TestData());
        data.input = Integer.parseInt(readFileToStringWithTrim(file));
        mapOfData.put(testNumber, data);
    }

    private void putInputStringDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        StringTwoOutputData data = (StringTwoOutputData) mapOfData.getOrDefault(testNumber, new StringTwoOutputData());
        data.input = readFileToStringWithTrim(file);
        mapOfData.put(testNumber, data);
    }

    private void putTwoInputDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestTwoInputData data = (TestTwoInputData) mapOfData.getOrDefault(testNumber, new TestTwoInputData());
        String[] content = readFileToStringWithTrim(file).split(OTUS_FORMAT_FILE_END_LINE_DELIMITER);
        data.input1 = Double.parseDouble(content[0]);
        data.input2 = Long.parseLong(content[1]);
        mapOfData.put(testNumber, data);
    }

    private void putExpectedDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestData data = (TestData) mapOfData.getOrDefault(testNumber, new TestData());
        data.expected = Long.parseLong(readFileToStringWithTrim(file));
        mapOfData.put(testNumber, data);
    }

    private void putTwoInputExpectedDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        TestTwoInputData data = (TestTwoInputData) mapOfData.getOrDefault(testNumber, new TestTwoInputData());
        data.expected = Double.parseDouble(readFileToStringWithTrim(file));
        mapOfData.put(testNumber, data);
    }

    private void putTwoOutputStringExpectedDataInternal(File file) throws IOException {
        int testNumber = getTestNumberFromFile(file);
        StringTwoOutputData data = (StringTwoOutputData) mapOfData.getOrDefault(testNumber, new StringTwoOutputData());
        String[] content = readFileToStringWithTrim(file).split(OTUS_FORMAT_FILE_END_LINE_DELIMITER);
        data.expected1 = content[0];
        data.expected2 = content[1];
        mapOfData.put(testNumber, data);
    }

    private String readFileToStringWithTrim(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8).trim();
    }

    private int getTestNumberFromFile(File file) {
        String[] fileNameSplit = file.getName().split(OTUS_FORMAT_FILE_DELIMITER);
        return Integer.parseInt(fileNameSplit[1]);
    }

    private sealed interface Data permits TestData, TestTwoInputData, StringTwoOutputData {
    }

    private static final class TestData implements Data {
        public Integer input;
        public Long expected;
    }

    private static final class TestTwoInputData implements Data {
        public double input1;
        public Long input2;
        public double expected;
    }

    private static final class StringTwoOutputData implements Data {
        public String input;
        public String expected1;
        public String expected2;
    }
}

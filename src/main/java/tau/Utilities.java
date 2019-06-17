package tau;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class Utilities {

    public static Stream<Arguments> dataForZipCodePlace() {
        return Stream.of(
                Arguments.of("us", "90210", "Beverly Hills"),
                Arguments.of("us", "12345", "Schenectady"),
                Arguments.of("ca","B2R","Waverley"),
                Arguments.of("gu","96910","Hagatna")
        );
    }
}

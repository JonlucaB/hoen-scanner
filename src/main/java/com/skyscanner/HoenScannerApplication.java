package com.skyscanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;

public class HoenScannerApplication extends Application<HoenScannerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HoenScannerApplication().run(args);
    }

    @Override
    public String getName() {
        return "hoen-scanner";
    }

    @Override
    public void initialize(final Bootstrap<HoenScannerConfiguration> bootstrap) {

    }

    private List<SearchResult> parseResource(@NotNull String fileName, String kind) {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(SearchResult.class, new SearchResultDeserializer(kind));
        mapper.registerModule(module);

        try {
            return Arrays.asList(
                    mapper.readValue(
                            getClass().getClassLoader().getResource(fileName),
                            SearchResult[].class
                    )
            );
        } catch (IOException e) {
            SearchResult errorResult = new SearchResult("error message");
            errorResult.setErrorMsg(e.getMessage());
            return Collections.singletonList(errorResult);
        }
    }

    @Override
    public void run(final HoenScannerConfiguration configuration, final Environment environment) throws IOException {
        List<SearchResult> searchResults = new ArrayList<>();

        List<SearchResult> carResults = parseResource("rental_cars.json", "car rental");
        searchResults.addAll(carResults);

        List<SearchResult> hotelResults = parseResource("hotels.json", "hotel");
        searchResults.addAll(hotelResults);

        final SearchResource resource = new SearchResource(searchResults);
        environment.jersey().register(resource);
    }
}
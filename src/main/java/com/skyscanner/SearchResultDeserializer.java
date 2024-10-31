package com.skyscanner;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;

public class SearchResultDeserializer extends JsonDeserializer<SearchResult> {
    private final String kind;

    public SearchResultDeserializer(@NotNull String kind) {
        this.kind = kind;
    }

    @Override
    public SearchResult deserialize(JsonParser p, DeserializationContext ctxt) {
        SearchResult result;
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = null;

        try {
            node = mapper.readTree(p);
            result = new SearchResult(kind);
            result.setCity(node.get("city").asText());
            result.setTitle(node.get("title").asText());
        } catch (Exception e) {
            result = new SearchResult("error");
            result.setErrorMsg(e.getMessage());
        }

        return result;
    }
}

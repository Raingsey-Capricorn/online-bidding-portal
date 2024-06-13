package com.system.bidding.infrastructure.utilities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.bidding.infrastructure.web.response.record.Announcement;
import com.system.bidding.infrastructure.web.response.record.BiddingHistory;
import com.system.bidding.infrastructure.web.response.record.Item;
import com.system.bidding.infrastructure.web.response.record.ItemBiddingDetails;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

/**
 * LinkedIn : <a href="https://www.linkedin.com/in/piseth-raingsey-jr-a26308a1">Piseth Raingsey Jr.</a>
 * Owner   : pisethraingsey@yahoo.com
 * Project : BiddingSystem
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class JsonLoader {

    @Data
    @NoArgsConstructor
    public static class Keyword {
        @JsonProperty(value = "javaKeywords")
        private List<String> javaKeywords = new ArrayList<>();
        @JsonProperty(value = "sqlKeywords")
        private List<String> sqlKeywords = new ArrayList<>();
    }

    /**
     * @param <T>
     */
    public interface ResourceAsJSON<T> {

        /**
         * Load data from JSON file based on generic authorizationProvider
         * T : authorizationProvider of data represent the data from JSON
         *
         * @return List of data from file
         */
        List<T> load();
    }

    @Data
    @NoArgsConstructor
    public static class Items implements ResourceAsJSON<Item> {
        @JsonProperty(value = "data")
        private List<Item> data = new ArrayList<>();

        @Override
        @SneakyThrows
        public List<Item> load() {
            return new ObjectMapper().readValue(
                    Items.class.getResourceAsStream("/sample-data/item-list.json"),
                    Items.class
            ).getData();
        }
    }

    @Data
    @NoArgsConstructor
    public static class History implements ResourceAsJSON<BiddingHistory> {
        @JsonProperty(value = "data")
        private List<BiddingHistory> data = new ArrayList<>();

        @Override
        @SneakyThrows
        public List<BiddingHistory> load() {
            return new ObjectMapper().readValue(
                    History.class.getResourceAsStream("/sample-data/bidding-history.json"),
                    History.class
            ).getData();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Details implements ResourceAsJSON<ItemBiddingDetails> {
        @JsonProperty(value = "data")
        private List<ItemBiddingDetails> data = new ArrayList<>();

        @Override
        @SneakyThrows
        public List<ItemBiddingDetails> load() {
            return new ObjectMapper().readValue(
                    Details.class.getResourceAsStream("/sample-data/item-details.json"),
                    Details.class
            ).getData();
        }
    }

    @Data
    @NoArgsConstructor
    public static class BiddingAnnouncement implements ResourceAsJSON<Announcement> {
        @JsonProperty(value = "data")
        private List<Announcement> data = new ArrayList<>();

        @Override
        @SneakyThrows
        public List<Announcement> load() {
            return new ObjectMapper().readValue(
                    BiddingAnnouncement.class.getResourceAsStream("/sample-data/announcement.json"),
                    BiddingAnnouncement.class
            ).getData();
        }
    }

}

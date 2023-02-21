package uz.olmossoft.raw.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uz.olmossoft.raw.domain.ExchangeRate;
import uz.olmossoft.raw.repository.ExchangeRateRepository;

/**
 * @author Muhammadkomil Murodillayev, вс 01:05. 1/22/23
 */
@Component
public class UtilService {

    private final ExchangeRateRepository exchangeRateRepository;

    public UtilService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Scheduled(cron = "${cron.expression}")
    private void refreshExchangeRates() {

        JsonNode root = getDataByUrl();

        assert root != null;
        root.forEach((rate) -> {
            ExchangeRate exchangeRate = new ExchangeRate();

            exchangeRate.setId(rate.get("id").asLong());
            exchangeRate.setCode(rate.get("Code").asInt());
            exchangeRate.setCurrency(rate.get("Ccy").asText().toUpperCase());
            exchangeRate.setId(rate.get("id").asLong());
            exchangeRate.setRate(rate.get("Rate").asDouble());

            exchangeRateRepository.save(exchangeRate);

        });
    }

    private JsonNode getDataByUrl() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity("https://cbu.uz/oz/arkhiv-kursov-valyut/json/", String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root;
        try {
            root = mapper.readTree(response.getBody());

        } catch (JsonProcessingException e) {

            System.out.print(e.getMessage());
            return null;
        }

        return root;
    }
}

package co.corp.linktic.inventory.service.inventory;

import co.corp.linktic.inventory.dto.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static co.corp.linktic.inventory.utils.Constants.PRODUCT_API_KEY;
import static co.corp.linktic.inventory.utils.Constants.PRODUCT_SERVICE_URL;

@Service
@Slf4j
public class InventoryServiceImpl implements InventorySerivce {
    @Override
    public Integer getProductQuantityById(Long productId) {

        String url = PRODUCT_SERVICE_URL + "/"+productId+"/quantity";
        log.info("Obteniendo cantidad de producto para la URL: " + url);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", PRODUCT_API_KEY);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            ResponseEntity<ResponseService<Integer>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<ResponseService<Integer>>() {}
            );

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody().getData();
            } else {
                throw new RuntimeException("Error al obtener la cantidad, código de estado: " + response.getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

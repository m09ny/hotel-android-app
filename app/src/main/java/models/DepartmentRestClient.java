package models;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import entities.Department;

public class DepartmentRestClient {

    private String BASE_URL = "http://localhost:4200/";
    private RestTemplate restTemplate = new RestTemplate();

    public Department find(int id) {
        try {
            return restTemplate.getForObject(BASE_URL + "showDepartment/" + id, Department.class);
        } catch (Exception e){
            return null;
        }
    }

    public List<Department> findAll() {
        try {
            return restTemplate.exchange(
                        BASE_URL + "departments/",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Department>>() {}).getBody();
        } catch (Exception e){
            return null;
        }
    }
}

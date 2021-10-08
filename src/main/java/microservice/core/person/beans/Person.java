package microservice.core.person.beans;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Document
@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    @Id
    private String id;

    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Age is mandatory")
    @DecimalMin(value = "0", message = "Height should not be less than 0")
    @DecimalMax(value = "200", message = "Height should not be greater than 200")
    private Integer age;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @NotNull(message = "Height is mandatory")
    @DecimalMin(value = "0", message = "Height should not be less than 0")
    @DecimalMax(value = "1.6", message = "Height should not be greater than 1.6")
    private Double height;

    @NotNull(message = "Weight is mandatory")
    @DecimalMin(value = "0", message = "Height should not be less than 0")
    @DecimalMax(value = "73.5", message = "Height should not be greater than 73.5")
    private Double weight;

    @NotNull(message = "Address is mandatory")
    @Valid
    private Address address;
}

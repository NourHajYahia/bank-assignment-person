package microservice.core.person.beans;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @NotNull(message = "State is mandatory")
    private State state;

    @Size(min = 3, max = 20, message = "City must be between 3 and 20 characters")
    @NotBlank(message = "City is mandatory")
    private String city;

    @Size(min = 3, max = 50, message = "Street must be between 3 and 20 characters")
    @NotBlank(message = "Street is mandatory")
    private String street;

    @Pattern(regexp = "^[0-9]*", message = "Zipcode Only numbers")
    @NotBlank(message = "Zipcode is mandatory")
    private String zipcode;

    @NotNull(message = "containsAnimals is mandatory")
    private Boolean containsAnimals;
}

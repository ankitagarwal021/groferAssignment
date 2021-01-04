package dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created By: Ankit Agarwal
 **/

@Setter
@Getter
@NoArgsConstructor
public class UserDetailsDto {

    private Integer id;

    private String name;

    private String username;

    private String email;

    private AddressDto address;

    private String phone;

    private String website;

    private CompanyDto company;

}

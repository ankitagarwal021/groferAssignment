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
public class TaskPerUserDto {

    public Integer userId;

    public Integer id;

    public String title;

    public Boolean completed;

}

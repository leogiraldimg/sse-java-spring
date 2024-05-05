package giraldi.dev.com.ssejavaspring.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListTaskDto {

    private Long id;
    private String name;
    private String description;
}

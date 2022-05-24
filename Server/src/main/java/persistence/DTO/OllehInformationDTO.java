package persistence.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class OllehInformationDTO implements Serializable {
    private int spotkey;
    private String ollehname;
    private float ollehlength;
    private String ollehspendtime;
}

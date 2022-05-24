package persistence.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class SpotInformationDTO implements Serializable {
    private int spotkey;
    private String spotname;
    private String spotaddress;
    private String spottype;
}

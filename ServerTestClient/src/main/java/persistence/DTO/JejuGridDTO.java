package persistence.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class JejuGridDTO implements Serializable{
    int jejugridkey;
    int jejugridx;
    int jejugridy;
    String jejugridprovince;
    String jejugridcity;
    String jejugridlocal;
}

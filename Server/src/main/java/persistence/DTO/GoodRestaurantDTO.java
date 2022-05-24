package persistence.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class GoodRestaurantDTO implements Serializable {
    private int goodrestaurantkey;
    private String goodrestaurantname;
    private String goodrestaurantaddress;
    private String goodrestaurantphone;
    private String goodrestaurantmainmenu;
    private String goodrestaurantstate;
}

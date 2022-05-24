package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.GoodRestaurantDTO;

import java.util.List;

public interface GoodRestaurantMapper {
    @Select("SELECT * FROM GOOD_RESTAURANT WHERE GOOD_RESTAURANT_ADDRESS LIKE CONCAT ('%', #{address}, '%'))")
    @Results(id="goodRestaurantSet", value={
            @Result(property = "goodrestaurantkey", column = "good_restaurant_key"),
            @Result(property = "goodrestaurantname", column = "good_restaurant_name"),
            @Result(property = "goodrestaurantaddress", column = "good_restaurant_address"),
            @Result(property = "goodrestaurantphone", column = "good_restaurant_phone"),
            @Result(property = "goodrestaurantmainmenu", column = "good_restaurant_main_menu"),
            @Result(property = "goodrestaurantstate", column = "good_restaurant_state")
    })
    List<GoodRestaurantDTO> get_all_good_restaurant(@Param("address")String address);


}

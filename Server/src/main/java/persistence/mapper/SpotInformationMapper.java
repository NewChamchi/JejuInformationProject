package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.GoodRestaurantDTO;
import persistence.DTO.SpotInformationDTO;

import java.util.List;

public interface SpotInformationMapper {
    @Select("SELECT * FROM SPOT_INFORMATION")
    @Results(id="spotInformationSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotname", column = "spot_name"),
            @Result(property = "spotaddress", column = "spot_address"),
            @Result(property = "spottype", column = "spot_type")
    })
    List<SpotInformationDTO> get_all_spot_information();

    @Select("SELECT * FROM SPOT_INFORMATION WHERE SPOT_ADDRESS LIKE CONCAT('%', #{address}, '%')" +
            "SPOT_TYPE LIKE CONCAT('%', #{type}, '%')")
    @ResultMap("spotInformationSet")
    List<SpotInformationDTO> get_spot_information_by_type(@Param("address")String address, @Param("type")String type);

    @Select("SELECT * FROM SPOT_INFORMATION WHERE SPOT_ADDRESS LIKE CONCAT('%', #{address}, '%')")
    @ResultMap("spotInformationSet")
    List<SpotInformationDTO> get_spot_information_by_address(@Param("address")String address);

    @Select("SELECT * FROM SPOT_INFORMATION WHERE SPOT_ADDRESS LIKE CONCAT('%', #{address}, '%') ORDER BY RAND() LIMIT #{need_amount}" )
    @ResultMap("spotInformationSet")
    List<SpotInformationDTO> get_spot_information_random_by_address(@Param("address")String address, @Param("need_amount")int needAmount);

}

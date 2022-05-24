package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.NatureSightDTO;
import persistence.DTO.SpotInformationDTO;

import java.util.List;

public interface NatureSightMapper {
    @Select("SELECT * FROM NATURE_SIGHT" +
            "WHERE EXISTS (SELECT SPOT_KEY FROM SPOT_INFORMATION " +
            "WHERE SPOT_KEY = NATURE_SIGHT.SPOT_KEY" +
            "AND SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @Results(id="natureSightSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotvision", column = "spot_vision")
    })
    List<NatureSightDTO> get_all_nature_sight_details(@Param("address")String address);

    @Select("SELECT * FROM SPOT_INFORMATION")
    @Results(id="spotInformationSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotname", column = "spot_name"),
            @Result(property = "spotaddress", column = "spot_address"),
            @Result(property = "spottype", column = "spottype")
    })
    List<SpotInformationDTO> get_all_spot_information();

    @Select("SELECT * FROM SPOT_INFORMATION " +
            "WHERE EXISTS (SELECT SPOT_KEY FROM NATURE_SIGHT " +
            "WHERE SPOT_KEY = SPOT_INFORMATION.SPOT_KEY" +
            "AND SPOT_INFORMATION.SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @ResultMap("spotInformationSet")
    List<SpotInformationDTO> get_all_nature_sight_basics(@Param("address")String address);
}

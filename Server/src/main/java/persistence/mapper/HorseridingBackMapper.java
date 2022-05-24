package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.HorseridingBackDTO;
import persistence.DTO.SpotInformationDTO;

import java.util.List;

public interface HorseridingBackMapper {
    @Select("SELECT * FROM HORSEBACK_RIDING" +
            "WHERE EXISTS (SELECT SPOT_KEY FROM SPOT_INFORMATION " +
            "WHERE SPOT_KEY = HORSEBACK_RIDING.SPOT_KEY" +
            "AND SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @Results(id="horsebackRidingSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotbusiness", column = "spot_business")
    })
    List<HorseridingBackDTO> get_all_horseback_riding_details(@Param("address")String address);

    @Select("SELECT * FROM SPOT_INFORMATION")
    @Results(id="spotInformationSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotname", column = "spot_name"),
            @Result(property = "spotaddress", column = "spot_address"),
            @Result(property = "spottype", column = "spottype")
    })
    List<SpotInformationDTO> get_all_spot_information();

    @Select("SELECT * FROM SPOT_INFORMATION " +
            "WHERE EXISTS (SELECT SPOT_KEY FROM HORSEBACK_RIDING " +
            "WHERE SPOT_KEY = SPOT_INFORMATION.SPOT_KEY" +
            "AND SPOT_INFORMATION.SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @ResultMap("spotInformationSet")
    List<SpotInformationDTO> get_all_horseback_riding_basics(@Param("address")String address);
}

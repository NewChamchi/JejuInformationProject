package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.OllehInformationDTO;
import persistence.DTO.SpotInformationDTO;

import java.util.List;

public interface OllehInformationMapper {
    @Select("SELECT * FROM OLLEH_INFORMATION WHERE EXISTS (SELECT SPOT_KEY FROM SPOT_INFORMATION WHERE SPOT_KEY = OLLEH_INFORMATION.SPOT_KEY AND SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @Results(id="ollehInformationSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "ollehname", column = "olleh_name"),
            @Result(property = "ollehlength", column = "olleh_length"),
            @Result(property = "ollehspendtime", column = "olleh_spend_time")
    })
    List<OllehInformationDTO> get_all_olleh_information_details(@Param("address")String address);

    @Select("SELECT * FROM SPOT_INFORMATION")
    @Results(id="spotInformationSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotname", column = "spot_name"),
            @Result(property = "spotaddress", column = "spot_address"),
            @Result(property = "spottype", column = "spot_type")
    })
    List<SpotInformationDTO> get_all_spot_information();

    @Select("SELECT * FROM SPOT_INFORMATION WHERE EXISTS (SELECT SPOT_KEY FROM OLLEH_INFORMATION WHERE SPOT_KEY = SPOT_INFORMATION.SPOT_KEY AND SPOT_INFORMATION.SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @ResultMap("spotInformationSet")
    List<SpotInformationDTO> get_all_olleh_information_basics(@Param("address")String address);
}

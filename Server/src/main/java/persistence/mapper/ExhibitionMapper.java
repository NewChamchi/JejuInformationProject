package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.ExhibitionDTO;
import persistence.DTO.SpotInformationDTO;

import java.util.List;

public interface ExhibitionMapper {

    /* 전시관 테이블 전체 READ */
    @Select("SELECT * FROM EXHIBITION " +
            "WHERE EXISTS (SELECT SPOT_KEY FROM SPOT_INFORMATION " +
            "WHERE SPOT_KEY = EXHIBITION.SPOT_KEY" +
            "AND SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @Results(id="exhibitionSet", value= {
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotcollection", column = "spot_collection")
    })
    List<ExhibitionDTO> get_all_exhibition_details(@Param("address")String address);

    @Select("SELECT * FROM SPOT_INFORMATION")
    @Results(id="spotInformationSet", value={
            @Result(property = "spotkey", column = "spot_key"),
            @Result(property = "spotname", column = "spot_name"),
            @Result(property = "spotaddress", column = "spot_address"),
            @Result(property = "spottype", column = "spottype")
    })
    List<SpotInformationDTO> get_all_spot_information();

    @Select("SELECT * FROM SPOT_INFORMATION " +
            "WHERE EXISTS (SELECT SPOT_KEY FROM EXHIBITION " +
            "WHERE SPOT_KEY = SPOT_INFORMATION.SPOT_KEY" +
            "AND SPOT_INFORMATION.SPOT_ADDRESS LIKE CONCAT ('%', #{address}, '%')) ")
    @ResultMap("spotInformationSet")
    List<SpotInformationDTO> get_all_exhibition_basics(@Param("address")String address);

}

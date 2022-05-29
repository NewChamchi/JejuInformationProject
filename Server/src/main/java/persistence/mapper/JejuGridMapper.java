package persistence.mapper;

import org.apache.ibatis.annotations.*;
import persistence.DTO.JejuGridDTO;

import java.util.List;

public interface JejuGridMapper {
    @Select("SELECT JEJU_GRID_X, JEJU_GRID_Y FROM JEJU_GRID")
    @Results(id="jejuGridSet", value={
            @Result(property = "jejugridx", column = "jeju_grid_x"),
            @Result(property = "jejugridy", column = "jeju_grid_y"),
            @Result(property = "jejugridlocal", column = "jeju_grid_local")

    })
    List<JejuGridDTO> get_jeju_grid_all();

    @Select("SELECT JEJU_GRID_LOCAL FROM JEJU_GRID" +
            "WHERE JEJU_GRID_X = #{jeju_grid_x} AND JEJU_GRID_Y = #{jeju_grid_y}")
    @ResultMap("jejuGridSet")
    List<JejuGridDTO> get_local_jeju_grid(@Param("jeju_grid_x")Integer jeju_grid_x, @Param("jeju_grid_y")Integer jeju_grid_y);

}

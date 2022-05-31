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

    @Select("SELECT JEJU_GRID_LOCAL FROM JEJU_GRID WHERE JEJU_GRID_X = #{jejugridx} AND JEJU_GRID_Y = #{jejugridy}")
    @ResultMap("jejuGridSet")
    List<JejuGridDTO> get_local_jeju_grid(@Param("jejugridx")Integer jeju_grid_x, @Param("jejugridy")Integer jeju_grid_y);

}

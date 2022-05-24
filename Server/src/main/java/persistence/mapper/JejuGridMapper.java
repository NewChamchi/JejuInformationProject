package persistence.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import persistence.DTO.JejuGridDTO;

import java.util.List;

public interface JejuGridMapper {
    @Select("SELECT JEJU_GRID_X, JEJU_GRID_Y FROM JEJU_GRID")
    @Results(id="jejuGridSet", value={
            @Result(property = "jejugridx", column = "jeju_grid_x"),
            @Result(property = "jejugridy", column = "jeju_grid_y")

    })
    List<JejuGridDTO> get_jeju_grid_all();
}

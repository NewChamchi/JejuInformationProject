package persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SpotInformationSQL {
    public String s_select_by_address_randomly(@Param("address")String address,  @Param("needAmount")int needAmount) {
        SQL sql = new SQL() {{

            SELECT("*");
            FROM("SPOT_INFORMATION");
            WHERE("REGEXP_LIKE(SPOT_ADDRESS, #{address})");
            ORDER_BY("RAND()");
            LIMIT("#{needAmount}");
        }};
        return sql.toString();
    }
}

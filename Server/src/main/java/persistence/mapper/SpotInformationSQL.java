package persistence.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SpotInformationSQL {
    public String s_select_by_address_randomly(@Param("address")String[] address, @Param("i")int i, @Param("needAmount")int needAmount) {
        SQL sql = new SQL() {{
            int count = address.length;
            SELECT("*");
            FROM("SPOT_INFORMATION");
            while(count > 0) {
                count = count - 1;
                WHERE("SPOT_ADDRESS LIKE CONCAT ('%', '${address[i]}', '%')");
                if (count > 0) {
                    OR();
                }
            }
            ORDER_BY("RAND()");
            LIMIT("#{needAmount}");
        }};
        return sql.toString();
    }
}

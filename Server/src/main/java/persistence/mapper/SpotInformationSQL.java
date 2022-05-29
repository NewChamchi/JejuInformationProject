package persistence.mapper;

import org.apache.ibatis.jdbc.SQL;

public class SpotInformationSQL {
    public String s_select_by_address_randomly(String[] address, int needAmount) {
        SQL sql = new SQL() {{
            SELECT("*");
        }}
    }
}

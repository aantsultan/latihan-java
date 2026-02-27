package com.latihan.java.logic.general;

import org.junit.jupiter.api.Test;

public class PrintTextTest {

    @Test
    void print(){
        String sql = "select " +
                "'' as act, " +
                "mcc.customer_channel_id as customer_channel_id, " +
                "mcc.customer_id, " +
                "mc.code as customer_code, " +
                "mc.name as customer_name, " +
                "mcc.customer_type, " +
                "mcc.customer_status, " +
                "mc2.channel_id as channel_id, " +
                "mc2.code as channel_code, " +
                "mcc.channel_name, " +
                "iif(mcc.main = 1, 'Yes', 'No') as str_main, " +
                "mjg.journey_group_id as journey_group_id, " +
                "mjg.code as journey_group_code, " +
                "mcc.journey_group_name, " +
                "mcc.sales_group_name, " +
                "iif(mcc.valid_from is null, '', replace(convert(NVARCHAR, mcc.valid_from, 103), ' ', '/')) as str_valid_from, " +
                "iif(mcc.valid_to is null, '', replace(convert(NVARCHAR, mcc.valid_to, 103), ' ', '/')) as str_valid_to, " +
                "mcc.created_by, " +
                "mcc.created_date, " +
                "mcc.modified_by, " +
                "mcc.modified_date " +
                "from m_customer_channel mcc " +
                "left join m_customer mc on " +
                "mcc.customer_id = mc.customer_id " +
                "left join m_channel mc2 on " +
                "mc2.channel_id = mcc.channel_id " +
                "left join m_journey_group mjg on " +
                "mjg.journey_group_id = mcc.journey_group_id ";
        System.out.println(sql);
    }

}

package com.latihan.jpa.datatable.service;

import com.latihan.jpa.datatable.dto.StockTransferDto;
import com.latihan.jpa.datatable.util.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@Slf4j
public class StockTransferServiceImpl implements StockTransferService {

    @Autowired
    private EntityManager entityManager;

    private static final String SQL_SELECT = "SELECT \n" +
            "stock_inbound_id as id,\n" +
            "sales_group_id,\n" +
            "warehouse_id,\n" +
            "'IR' as stock_type,\n" +
            "null as reason\n" +
            "FROM trx_stock_inbound tsi \n" +
            "UNION\n" +
            "SELECT\n" +
            "stock_outbound_id as id,\n" +
            "warehouse_id as sales_group_id,\n" +
            "sales_group_id as warehouse_id,\n" +
            "'DO' as stock_type,\n" +
            "reason\n" +
            "FROM trx_stock_outbound";

    private static final String SQL_COUNT =
            "SELECT COUNT(*) FROM (" + SQL_SELECT + ") un";
    private static final String SQL_FILTERED =
            "SELECT * FROM (" + SQL_SELECT + ") un " +
                    "WHERE (un.stock_type=:stockType or :stockType is null)";

    @Override
    public Page<StockTransferDto> findAll(DataTablesInput input, String stockType) {
        log.info("StockTransfer findAll req : input[{}] ; stockType[{}]", input, stockType);

        int start = input.getStart();
        int length = input.getLength();

        Query querySelect = entityManager.createNativeQuery(SQL_FILTERED)
                .setParameter("stockType", ObjectUtils.isEmpty(stockType) ? null : stockType);
        List<StockTransferDto> result = querySelect
                .setFirstResult(start * length)
                .setMaxResults(length)
                .getResultList();

        Query queryCount = entityManager.createNativeQuery(SQL_COUNT);
        Long totalRecords = Long.valueOf(queryCount.getResultList().get(0).toString());

        Page<StockTransferDto> output = new Page<>(result);
        output.setTotalRecords(totalRecords);
        log.info("StockTransfer findAll res : totalRecords[{}]; totalData[{}]", totalRecords, result.size());
        return output;
    }
}

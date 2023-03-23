package com.demo.utils.excel;

import com.demo.service.Impl.ExcelServiceImpl;
import com.demo.utils.excel.dto.InvoiceCustomerExcel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExportConfig {
    private int sheetIndex;

    private int startRow;

    private Class dataClass;

    private List<CellConfig> cellExportConfigs;

    public static final ExportConfig exportConfig;

    static{
        exportConfig = new ExportConfig();
        exportConfig.setSheetIndex(0);
        exportConfig.setStartRow(1);
        exportConfig.setDataClass(InvoiceCustomerExcel.class);

        List<CellConfig> list = new ArrayList<>();
        list.add(new CellConfig(0, "Id_C_Invoice"));
        list.add(new CellConfig(1, "Id_Payment"));
        list.add(new CellConfig(2, "Id_Customer"));
        list.add(new CellConfig(3, "Total_Of_Money"));
        list.add(new CellConfig(4, "status_Invoice"));
        list.add(new CellConfig(5, "Type_Of_Payment"));
        list.add(new CellConfig(6, "startDate"));
        list.add(new CellConfig(7, "startTime"));
        list.add(new CellConfig(8, "endDate"));
        list.add(new CellConfig(9, "endTime"));

        exportConfig.setCellExportConfigs(list);
    }
}

package pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ExcelTur {
    @ExcelProperty(value = {"租户Id"},index = 12)
        String tenantId;
    @ExcelProperty(value = {"租户名"},index = 11)
        String tenantName;
    @ExcelProperty(value = {"礼品名"},index = 8)
        String itemName;
    @ExcelProperty(value = {"礼品Id"},index = 9)
        String itemId;
    @ExcelProperty(value = {"类型"},index = 10)
        String itemType;
}

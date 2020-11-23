package pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExcelTur implements Cloneable{
    @ExcelProperty(value = {"租户Id"},index = 4)
        String tenantId;
    @ExcelProperty(value = {"租户名"},index = 3)
        String tenantName;
    @ExcelProperty(value = {"礼品名"},index = 0)
        String itemName;
    @ExcelProperty(value = {"礼品Id"},index = 1)
        String itemId;
    @ExcelProperty(value = {"类型"},index = 2)
        String itemType;

    @Override
    public ExcelTur clone()  {
        return new ExcelTur(this);
    }

    private ExcelTur(ExcelTur a) {
        this.tenantId = a.tenantId;
        this.tenantName = a.tenantName;
        this.itemName = a.itemName;
        this.itemId = a.itemId;
        this.itemType = a.itemType;
    }
}

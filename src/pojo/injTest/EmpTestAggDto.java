package pojo.injTest;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpTestAggDto {
    private String name;
    private String tenantId;
    private String code;
    private Date createTime;
    private String actName;
    private String account;
    private String email;
    private List<EmpTestVo> subs;

}

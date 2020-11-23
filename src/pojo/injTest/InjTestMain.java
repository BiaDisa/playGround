package pojo.injTest;

import pojo.copyMapper.EmpMapper;

import java.util.Date;
import java.util.WeakHashMap;

public class InjTestMain {

    public static void main(String[] args){
        EmpTestVo vo = EmpTestVo.builder().code("1234").createTime(new Date()).name("test").tenantId("0001").build();
        EmpTestAddInfoVo add = EmpTestAddInfoVo.builder()
                                            .account("account")
                                            .actName("actName")
                                            .email("email").build();
        EmpTestAggDto dto = EmpMapper.INSTANCE.inj(add,vo);
        System.out.println(dto);
    }
}

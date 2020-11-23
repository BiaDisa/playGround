package pojo.copyMapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import pojo.injTest.EmpTestAddInfoVo;
import pojo.injTest.EmpTestAggDto;
import pojo.injTest.EmpTestVo;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmpMapper  {

    EmpMapper INSTANCE = Mappers.getMapper(EmpMapper.class);

    @Mapping(source = "vo.createTime", target = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    EmpTestAggDto inj(EmpTestAddInfoVo infoVo,EmpTestVo vo);

}

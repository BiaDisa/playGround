package test.biz.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class DistinctTestDto {
    String s1;
    String s2;
    Integer s3;


    public DistinctTestDto(String s1, String s2,Integer s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    @Override
    public String toString() {
        return "DistinctTestDto{" +
                "s1='" + s1 + '\'' +
                ", s2='" + s2 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistinctTestDto that = (DistinctTestDto) o;
        return s1.equals(that.s1) &&
                s2.equals(that.s2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s1, s2);
    }

    /*public static void main(String[] args){
        List<DistinctTestDto> test = new ArrayList<>();
        test.add(new DistinctTestDto("1","1",1));
        test.add(new DistinctTestDto("1","1",2));
        test = test.stream().distinct().collect(Collectors.toList());
        test.forEach(System.out::println);
    }*/
}

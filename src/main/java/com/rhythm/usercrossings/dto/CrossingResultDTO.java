package com.rhythm.usercrossings.dto;

import com.rhythm.usercrossings.models.Crossing;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class CrossingResultDTO {

    private Long time;
    private String location;
    private Boolean enter;

    public static CrossingResultDTO from(Crossing crossing) {
        CrossingResultDTO resultDTO = new CrossingResultDTO();
        BeanUtils.copyProperties(crossing, resultDTO);
        return resultDTO;
    }
}

package com.victory.hr.attendance.controller.converter;

import com.victory.hr.attendance.enums.RecordType;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by ajkx
 * Date: 2017/5/12.
 * Time:21:19
 */
public class IntToRecordType implements Converter<Integer,RecordType>{

    @Override
    public RecordType convert(Integer integer) {
        return RecordType.values()[integer];
    }
}

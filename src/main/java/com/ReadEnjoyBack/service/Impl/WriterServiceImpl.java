package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.WriterMapper;
import com.ReadEnjoyBack.pojo.Writer;
import com.ReadEnjoyBack.service.IWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:HB
 * @Description: 关于作者信息简介操作实现
 * @Createdata:Created in  16:14  2019/2/22.
 */
@Service("iWriterService")
public class WriterServiceImpl implements IWriterService {
    @Autowired
    private WriterMapper writerMappler;
    /*
     * @Author:HB
     * @Description: 通过年份得到用户信息
     * @Data:16:15 2019/2/22
     * @param year
     returns:
    */
    @Override
    public ServerResponse<List<Writer>> getWriterByYears(int year) {
        List<Writer> writerList = writerMappler.SelectWriterByYear(year);
        if (writerList.size() == 0){
            return ServerResponse.createByErrorMessage("当前年份暂无相应作者");
        }
        return ServerResponse.createBySuccesse(writerList);
    }
}

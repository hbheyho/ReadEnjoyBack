package com.ReadEnjoyBack.service.Impl;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.dao.WriterMapper;
import com.ReadEnjoyBack.pojo.Writer;
import com.ReadEnjoyBack.service.IWriterService;
import com.ReadEnjoyBack.util.PropertiesUtil;
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
        // 图片服务器地址添加
        for (Writer writer: writerList){
            writer.setImageHost(PropertiesUtil.getProperty("writerImage.server"));
        }
        return ServerResponse.createBySuccesse(writerList);
    }
    /*
     * @Author:HB
     * @Description: 获取年份信息
     * @Data:14:40 2019/3/3
     * @param null
     returns: List<year>
    */
    @Override
    public ServerResponse<List<String>> getYears() {
        List<String> yearList = writerMappler.getAllYear();
        if (yearList.size() == 0){
            return ServerResponse.createByErrorMessage("获取年份信息失败!");
        }
        return ServerResponse.createBySuccesse(yearList);
    }
}

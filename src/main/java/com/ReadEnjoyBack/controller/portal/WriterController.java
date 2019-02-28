package com.ReadEnjoyBack.controller.portal;

import com.ReadEnjoyBack.common.ServerResponse;
import com.ReadEnjoyBack.pojo.Writer;
import com.ReadEnjoyBack.service.IWriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author:HB
 * @Description: 关于作者信息简介
 * @Createdata:Created in  15:58  2019/2/22.
 */
@Controller
@RequestMapping("/writer/")
public class WriterController {
    @Autowired
    private IWriterService iWriterService;
    /*
     * @Author:HB
     * @Description: 通过年份得到相应作者信息
     * @Data:16:02 2019/2/22
     * @param null
     returns:
    */
    @RequestMapping(value = "get_writer_by_years.do")
    @ResponseBody
    public ServerResponse<List<Writer>> getWriterByYears(@RequestParam(value = "year",defaultValue="0") int year, HttpServletRequest request,
                                                         HttpServletResponse response){
        // 解决跨域
        response.addHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        return iWriterService.getWriterByYears(year);
    }

}

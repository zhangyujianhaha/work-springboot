package com.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.domain.Company;
import com.work.domain.Joins;
import com.work.domain.Recruit;
import com.work.domain.User;
import com.work.mapper.JoinMapper;
import com.work.mapper.RecruitMapper;
import com.work.utlis.DataGridView;
import com.work.utlis.RetCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class JoinController {
    @Resource
    private JoinMapper joinMapper;
    @Resource
    private RecruitMapper recruitMapper;
    //
    @RequestMapping("join/join")
    @ResponseBody
    public DataGridView adminUser( @RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                  HttpSession session){
        QueryWrapper<Joins> wrapper=new QueryWrapper();
        User user=(User)session.getAttribute("user");
        wrapper.eq("userid",user.getUserid());
        IPage<Joins> pag=new Page<>(page,limit);
        joinMapper.selectPage(pag,wrapper);
        List<Joins> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }
    @RequestMapping("join/addjoin")
    @ResponseBody
    public RetCode adminAddUser(Joins join){
        QueryWrapper<Joins> wrapper=new QueryWrapper();

            int i=joinMapper.insert(join);
            return new RetCode("1","添加成功");

    }
    @RequestMapping("join/updatejoin")
    @ResponseBody
    public RetCode adminUpdateUser(Joins join){
        int i=joinMapper.updateById(join);
        return new RetCode("1","修改成功");
    }
    @RequestMapping("join/deletejoin")
    @ResponseBody
    public RetCode adminDeleteUser(Joins join){
        int i=joinMapper.deleteById(join.getJoinid());
        return new RetCode("1","删除成功");
    }
    @RequestMapping("company/join")
    @ResponseBody
    public DataGridView CompanyJoin(Joins joins, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                   HttpSession session){
        Company company=(Company)session.getAttribute("user");
        QueryWrapper<Recruit> recruitQueryWrapper=new QueryWrapper<>();
        recruitQueryWrapper.eq("companyid",company.getCompanyid());
        List<Recruit> rlist=recruitMapper.selectList(recruitQueryWrapper);
        session.setAttribute("rlist",rlist);
        QueryWrapper<Joins> wrapper=new QueryWrapper();
        wrapper.like(joins.getJoinwork()!=null&&!joins.getJoinwork().equals(""),"joinwork",joins.getJoinwork())
                .like(joins.getUsername()!=null&&!joins.getUsername().equals(""),"username",joins.getUsername())
                .like(joins.getJoinmoney()!=null&&!joins.getJoinmoney().equals(""),"joinmoney",joins.getJoinmoney());
        IPage<Joins> pag=new Page<>(page,limit);
        joinMapper.selectPage(pag,wrapper);
        List<Joins> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }



}

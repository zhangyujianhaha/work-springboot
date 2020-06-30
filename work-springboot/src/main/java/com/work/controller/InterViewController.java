package com.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.domain.*;
import com.work.mapper.ApplyMapper;
import com.work.mapper.InterViewMapper;
import com.work.mapper.RecruitMapper;
import com.work.utlis.DataGridView;
import com.work.utlis.RetCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class InterViewController {
    @Resource
    private InterViewMapper interViewMapper;
    @Resource
    private ApplyMapper applyMapper;
    @Resource
    private RecruitMapper recruitMapper;
    @RequestMapping("company/addinterview")
    public RetCode companyAddinterview(Interview interView, HttpServletRequest request){
        String applyid=request.getParameter("applyid");
        Apply apply=applyMapper.selectById(new Integer(applyid));
        apply.setStates("已邀请面试");
        int i=applyMapper.updateById(apply);
        System.out.println("aa");
        int j=interViewMapper.insert(interView);
        System.out.println("aaa");
        return new RetCode("1","发送成功");
    }
    @RequestMapping("company/addinterviewto")
    public RetCode companyAddingerviewto(Interview interView){
        Recruit recruit=recruitMapper.selectById(interView.getRecruitid());
        interView.setRecrmajor(recruit.getRecrmajor());
        int i=interViewMapper.insert(interView);
        return new RetCode("1","已发送");
    }
    @RequestMapping("user/interview")
    public DataGridView userInterView(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpSession session){
        User user=(User)session.getAttribute("user");
        QueryWrapper<Interview> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",user.getUserid());
        IPage<Interview> pag=new Page<>(page,limit);
        interViewMapper.selectPage(pag,wrapper);
        List<Interview> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }
    @RequestMapping("company/interview")
    public DataGridView companyInterview(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpSession session){
        Company company=(Company)session.getAttribute("user");
        QueryWrapper<Interview> wrapper=new QueryWrapper<>();
        wrapper.eq("companyid",company.getCompanyid());
        IPage<Interview> pag=new Page<>(page,limit);
        interViewMapper.selectPage(pag,wrapper);
        List<Interview> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }
}

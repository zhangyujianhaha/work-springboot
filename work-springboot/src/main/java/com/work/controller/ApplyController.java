package com.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.domain.Apply;
import com.work.domain.Company;
import com.work.domain.Recruit;
import com.work.domain.User;
import com.work.mapper.ApplyMapper;
import com.work.utlis.DataGridView;
import com.work.utlis.RetCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ApplyController {
    @Resource
    private ApplyMapper applyMapper;
    @RequestMapping("user/addapply")
    public RetCode userAddApply(Recruit recruit, HttpSession session){
        System.out.println(recruit.getRecruitid());
        User user=(User)session.getAttribute("user");
        QueryWrapper<Apply> wrapper=new QueryWrapper();
        wrapper.eq("recruitid",recruit.getRecruitid())
                .eq("userid",user.getUserid());
        int is=applyMapper.selectCount(wrapper);
        if (is!=0){
            return new RetCode("1","您之前已投递过");
        }else {
            Apply apply=new Apply();
            apply.setCompanyid(recruit.getCompanyid());
            apply.setCompanyname(recruit.getCompanyname());
            apply.setRecruitid(recruit.getRecruitid());
            apply.setRecruitmajor(recruit.getRecrmajor());
            apply.setUserid(user.getUserid());
            apply.setUsername(user.getUsername());
            apply.setStates("未处理");
            int i=applyMapper.insert(apply);
            return new RetCode("1","已投递");
        }
    }
    @RequestMapping("user/apply")
    public DataGridView userApply(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpSession session){
        User user=(User)session.getAttribute("user");
        QueryWrapper<Apply> wrapper=new QueryWrapper<>();
        wrapper.eq("userid",user.getUserid());
        IPage<Apply> pag=new Page<>(page,limit);
        applyMapper.selectPage(pag,wrapper);
        List<Apply> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }
    @RequestMapping("company/applywei")
    public DataGridView companyApplywei(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpSession session){
        Company company=(Company)session.getAttribute("user");
        QueryWrapper<Apply> wrapper=new QueryWrapper<>();
        wrapper.eq("companyid",company.getCompanyid())
                .eq("states","未处理");
        IPage<Apply> pag=new Page<>(page,limit);
        applyMapper.selectPage(pag,wrapper);
        List<Apply> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }
    //公司拒绝用户的面试申请
    @RequestMapping("company/jujueuser")
    public RetCode companyJujueUser(Apply apply){
        System.out.println(apply.getApplyid());
        apply.setStates("已拒绝");
        int i=applyMapper.updateById(apply);
        return  new RetCode("1","已拒绝");
    }
    //公司查看所有本公司的apply
    @RequestMapping("company/apply")
    public DataGridView companyApply(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, HttpSession session){
        Company company=(Company)session.getAttribute("user");
        QueryWrapper<Apply> wrapper=new QueryWrapper<>();
        wrapper.eq("companyid",company.getCompanyid());
        IPage<Apply> pag=new Page<>(page,limit);
        applyMapper.selectPage(pag,wrapper);
        List<Apply> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);

    }



}

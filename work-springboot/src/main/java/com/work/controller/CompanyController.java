package com.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.domain.Company;
import com.work.domain.Recruit;
import com.work.domain.User;
import com.work.mapper.CompanyMapper;
import com.work.utlis.DataGridView;
import com.work.utlis.RetCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class CompanyController {
    @Resource
    private CompanyMapper companyMapper;
    @RequestMapping("admin/company")
    @ResponseBody
    public DataGridView adminCompany(Company company, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                  HttpServletRequest request){
        System.out.println("aa");
        System.out.println("aaaaaaaa");
        System.out.println("aaa");
        System.out.println("主干啊");
        System.out.println("分支添加");
        QueryWrapper<Company> wrapper=new QueryWrapper();
        wrapper.like(company.getCompanyid()!=null&&!company.getCompanyid().equals(""),"companyid",company.getCompanyid())
                .like(company.getCompanyname()!=null&&!company.getCompanyname().equals(""),"companyname",company.getCompanyname())
                .like(company.getComleixing()!=null&&!company.getComleixing().equals(""),"comleixing",company.getComleixing());
        IPage<Company> pag=new Page<>(page,limit);
        companyMapper.selectPage(pag,wrapper);
        List<Company> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }
    @RequestMapping("admin/addcompany")
    @ResponseBody
    public RetCode adminAddCompany(Company company){
        QueryWrapper<Company> wrapper=new QueryWrapper();
        wrapper.eq("companytel",company.getCompanytel());
        Company ompany1=companyMapper.selectOne(wrapper);
        if(ompany1==null){
            int i=companyMapper.insert(company);
            return new RetCode("1","添加成功");
        }else {
            return new RetCode("2","该手机号已被注册，请重新输入");
        }
    }
    @RequestMapping("admin/updatecompany")
    @ResponseBody
    public RetCode adminUpdateCompany(Company company){
        int i=companyMapper.updateById(company);
        return new RetCode("1","修改成功");
    }
    @RequestMapping("admin/deletecompany")
    @ResponseBody
    public RetCode adminDeleteCompany(Company company){
        int i=companyMapper.deleteById(company.getCompanyid());
        return new RetCode("1","删除成功");
    }
    @RequestMapping("admin/deleteallcompany")
    @ResponseBody
    public RetCode adminDeleteAllCompany(@RequestParam("ids") String ids){
        String[] companyids=ids.split(",");
        for (String companyid:companyids){
            System.out.println(companyid);
            int i=companyMapper.deleteById(companyid);
        }
        return new RetCode("1","批量删除成功");
    }
    //公司修改信息
    @RequestMapping(value = "/company/updatecompany", method = RequestMethod.POST)
    public String userUpdateUser(Company company, @RequestParam("comp") MultipartFile comphoto, HttpSession session) throws IOException {
        if(comphoto.isEmpty()){
            int i=companyMapper.updateById(company);
            Company user1=companyMapper.selectById(company.getCompanyid());
            session.setAttribute("user",user1);

        }else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
            // 获得文件原始名称
            String fileName = comphoto.getOriginalFilename();
            // 获得文件后缀名称
            System.out.println(fileName);
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            System.out.println(suffixName);
            // 生成最新的uuid文件名称
            String newFileName = uuid + "."+ suffixName;
            System.out.println(newFileName);
            byte[] bytes = comphoto.getBytes();
            FileOutputStream fos=new FileOutputStream("D:/files/"+newFileName);
            fos.write(bytes);
            fos.close();
            company.setComphoto(newFileName);
            int i=companyMapper.updateById(company);
            Company user1=companyMapper.selectById(company.getCompanyid());
            session.setAttribute("user",user1);
        }



        return "company/company";
    }
    //用户查看公司详细信息
    @RequestMapping("user/showcompany")
    @ResponseBody
    public Company userShowCompany(Recruit recruit){
        Company company=companyMapper.selectById(recruit.getCompanyid());
        return company;
    }
}

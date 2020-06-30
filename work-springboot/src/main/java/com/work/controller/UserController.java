package com.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.work.domain.Joins;
import com.work.domain.User;
import com.work.mapper.UserMapper;
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
public class UserController {
    @Resource
    private UserMapper userMapper;
    //
    @RequestMapping("admin/user")
    @ResponseBody
    public DataGridView adminUser(User user, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                  HttpServletRequest request){
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.like(user.getUserid()!=null&&!user.getUserid().equals(""),"userid",user.getUserid())
                .like(user.getUsername()!=null&&!user.getUsername().equals(""),"username",user.getUsername())
                .like(user.getUsertel()!=null&&!user.getUsertel().equals(""),"usertel",user.getUsertel());
        IPage<User> pag=new Page<>(page,limit);
        userMapper.selectPage(pag,wrapper);
        List<User> list=pag.getRecords();
        return new DataGridView(pag.getTotal(),list);
    }
    @RequestMapping("admin/adduser")
    @ResponseBody
    public RetCode adminAddUser(User user){
        QueryWrapper<User> wrapper=new QueryWrapper();
        wrapper.eq("usertel",user.getUsertel());
        User user1=userMapper.selectOne(wrapper);
        if(user1==null){
            int i=userMapper.insert(user);
            return new RetCode("1","添加成功");
        }else {
            return new RetCode("2","该手机号已被注册，请重新输入");
        }
    }
    @RequestMapping("admin/updateuser")
    @ResponseBody
    public RetCode adminUpdateUser(User user){
        System.out.println(user.getUserid());
        System.out.println(user.getUsername());
        int i=userMapper.updateById(user);
        return new RetCode("1","修改成功");
    }
    @RequestMapping("admin/deleteuser")
    @ResponseBody
    public RetCode adminDeleteUser(User user){
        int i=userMapper.deleteById(user.getUserid());
        return new RetCode("1","删除成功");
    }
    @RequestMapping("admin/deletealluser")
    @ResponseBody
    public RetCode adminDeleteAllUser(@RequestParam("ids") String ids){
        String[] userids=ids.split(",");
        for (String userid:userids){
            int i=userMapper.deleteById(userid);
        }
        return new RetCode("1","批量删除成功");
    }
    //用户修改个人信息
    @RequestMapping(value = "/user/updateuser", method = RequestMethod.POST)
    public String userUpdateUser(User user, @RequestParam("userjian") MultipartFile userjianli, HttpSession session) throws IOException {
        if(userjianli.isEmpty()){
            int i=userMapper.updateById(user);
            User user1=userMapper.selectById(user.getUserid());
            session.setAttribute("user",user1);
        }else {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
            // 获得文件原始名称
            String fileName = userjianli.getOriginalFilename();
            // 获得文件后缀名称
            System.out.println(fileName);
            String suffixName = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            System.out.println(suffixName);
            // 生成最新的uuid文件名称
            String newFileName = uuid + "."+ suffixName;
            System.out.println(newFileName);
            byte[] bytes = userjianli.getBytes();
            FileOutputStream fos=new FileOutputStream("D:/files/"+newFileName);
            fos.write(bytes);
            fos.close();
            user.setUserjianli(newFileName);
            int i=userMapper.updateById(user);
            User user1=userMapper.selectById(user.getUserid());
            session.setAttribute("user",user1);

        }

        return "user/user";
    }

    //公司查看用户信息
    @RequestMapping("company/showuser")
    @ResponseBody
    public User companyShowUser(Joins joins, HttpSession session){
        System.out.println(joins.getUsername());
        User user=userMapper.selectById(joins.getUserid());
        return user;
    }

    //进入公司首页准备
    @RequestMapping("company/index")
    public String companyIndex(HttpSession session){

        return "company/join";
    }

}

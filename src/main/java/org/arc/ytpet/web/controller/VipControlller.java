package org.arc.ytpet.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.arc.ytpet.core.dao.AdminMapper;
import org.arc.ytpet.core.model.*;
import org.arc.ytpet.core.model.databind.Result;
import org.arc.ytpet.core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/vip")
public class VipControlller {

    private static final String RS_FALSE = "{\"result\":\"false\"}" ;
    private static final String RS_TRUE = "{\"result\":\"true\"}" ;

    @Autowired
    AdminService adminService;

    @Autowired
    VipService vipService;

    @Autowired
    VipBalanceService   vipBalanceService;

    @Autowired
    VipRechargeService vipRechargeService;

    @Autowired
    VipConsumeService vipConsumeService;
    /**
    * @ method addVip
     * @ param name,phoneNumber,token
     * @ return boolean
     * @ type PUT (暂时使用POST,PUT 注解不认)
     * @ url /add
    * */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addVip(@RequestParam String customname, @RequestParam String phoneNumber
            , @RequestParam String token, HttpServletRequest request)throws JsonProcessingException{
        String pettype = request.getParameter("pettype");
        String petname = request.getParameter("petname");
        String balance = request.getParameter("balance");

        if(!adminService.findAdminByPassword(token)){
            //令牌错误,返回错误
            return RS_FALSE;
        }
        if(vipService.findVipByName(customname)){
            //如果找到了用户则说明不能再添加用户了,已经存在
            return RS_FALSE;
        }
        //test
        System.out.println(pettype + petname + balance);
        System.out.println(customname + phoneNumber + token);
        //将String转换为double
        Double balanceInt = Double.parseDouble(balance);

        //存储客户基本信息,存储宠物信息,存储余额信息,都是一个事务.
        PrimInfo primInfo = new PrimInfo();
        Pet pet = new Pet();
        Balance balance1 = new Balance();
        //设置信息
        primInfo.setCustomname(customname);   primInfo.setCustomphone(phoneNumber);
        if(pettype != null) pet.setPettype(pettype);
        if(petname != null) pet.setPetname(petname);
        if(balance != null) balance1.setBalance(balanceInt);
        //由于priminfo还没创建,因此customid没有,这个步骤交给service处理.
        //返回结果
        if(vipService.addOneVip(primInfo, pet, balance1))
            return RS_TRUE;
        else
            return RS_FALSE;
    }

    /*
    * @ method deleteVip
    * @ param token
    * @ return boolean
    * @ type DELETE (暂时先用POST,后期再处理@RequestParam和DELETE,PUT的问题)
    * @ url /delete/{customId}
    * */
    @RequestMapping(value = "/delete/{customId}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteVip(@PathVariable Integer customId, @RequestParam("token")String token){
        //先判断用户是否合法
        if(!adminService.findAdminByPassword(token))
            return RS_FALSE;
        //System.out.println("customId:" + customId);
        //删除掉用户
        if(vipService.deleteOneVip(customId)){
            return RS_TRUE;
        }else{
            return RS_FALSE;
        }
    }

    /*
    * @ method modifyVip
    * @ param name,phoneNumber,token
    * @ return boolean
    * @ type POST
    * @ url /modify/{customId}
    * */
    @RequestMapping(value = "/modify/{customId}", method = RequestMethod.POST)
    @ResponseBody
    public String modifyVip(@PathVariable Integer customId,@RequestParam String name
            , @RequestParam String phoneNumber, @RequestParam String token){
        //首先判断管理员用户的合法性
        //System.out.println(customId + name + phoneNumber + token);
        if(!adminService.findAdminByPassword(token))
            return RS_FALSE;

        //进行修改操作
        PrimInfo primInfo = new PrimInfo();
        primInfo.setCustomid(customId);
        primInfo.setCustomname(name);
        primInfo.setCustomphone(phoneNumber);
        if(vipService.updateOneVipPrimInfo(primInfo))
            return RS_TRUE;
        else
            return RS_FALSE;
    }

    /*
    * @ method queryVip
    * @ param token
    * @ return [ {primary info}, {balance info}, {pet info}]
    * @ type GET
    * @ url /query/{customId}
    * */
    @RequestMapping(value = "/query/{customId}", method = RequestMethod.GET)
    public String queryVip(@PathVariable Integer customId){
        return null;
    }

    /*
    * @ method queryVips
    * @ param token
    * @ return [ {primary info},....]
    * @ type GET
    * @ url /query
    * */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public String queryVips (@RequestParam String token)throws JsonProcessingException{
        String password = token;
        if(adminService.findAdminByPassword(password)){
            return vipService.allVipByJson();
        }
        return "{\"result\":\"false\"}";
    }

    /*
    * @ method modifyBalance
    * @ param token balance
    * @ return boolean
    * @ type POST
    * @ url /balance/modify/{customId}
    * */
    @RequestMapping(value = "/balance/modify/{customId}", method = RequestMethod.POST)
    @ResponseBody
    public String modifyBalance(@PathVariable Integer customId, @RequestParam String token
            , @RequestParam Double balance){
        //首先验证管理员的合法性
        if(!adminService.findAdminByPassword(token))
            return RS_FALSE;
        //修改balance
        Balance balance1 = new Balance();
        //有且仅有这两个属性就OK了
        balance1.setBalance(balance);
        balance1.setfCustomid(customId);
        if(vipBalanceService.updateBlcByCustomId(balance1)){
            return RS_TRUE;
        }else{
            return RS_FALSE;
        }
    }

    /*
    * @ method queryBalance
    * @ param token
    * @ return balance(JSON)
    * @ type GET
    * @ url /balance/query/{customId}
    * */
    public String queryBalance(@PathVariable Integer customId, @RequestParam String token){
        return null;
    }

    /*
    * @ method queryBalances
    * @ param token
    * @ return balances(JSON)
    * @ type GET
    * @ url /balance/query
    * */
    @RequestMapping(value = "/balance/query", method = RequestMethod.GET)
    @ResponseBody
    public String queryBalances(@RequestParam String token)throws JsonProcessingException{
        if(!adminService.findAdminByPassword(token)){
            return RS_FALSE;
        }else{
            //查找所有的余额信息,并以json数组返回
            return vipBalanceService.findAllBalanceWithPrimInfo();
        }
    }
    /*
    * @ method charge
    * @ param amount token
    * @ return boolean
    * @ type PUT PUT和@RequestParam不兼容
    * @ url /recharge/charge/{customId}
    * */
    @RequestMapping(value = "/recharge/charge", method = RequestMethod.POST)
    @ResponseBody
    public String charge(@RequestParam String token
            , @RequestParam Double amount, @RequestParam String customName){
        //管理员判断
        if(!adminService.findAdminByPassword(token))
            return RS_FALSE;
        /*
        * 写到这里我就非常难受了,接下去都是体力活动
        * 真是的,当个程序员巨JB难,脑力劳动干完接着干体力活动
        * 在这坐了几天了,就写这瞎几把后台管理系统,答应别人的,
        * 我现在腰痛的很,比一夜七次郎还恼火,心里难受也不知道
        * 怎么办,媳妇又不在身边一个人是真的难受.
        *唉不说了,争取在两个小时写完代码
        * */
        //先通过customName找到customId
        PrimInfo primInfo = vipService.getVipByName(customName);
        if(primInfo == null)
            return RS_FALSE;
        Charge charge = new Charge();
        charge.setfCustomid(primInfo.getCustomid());
        charge.setAmount(amount);
        charge.setChargetime(new Date());
        if(vipRechargeService.charge(charge))
            return RS_TRUE;
        else
            return RS_FALSE;

    }

    /*
    * @ method queryRecharge
    * @ param token
    * @ return [{recharge record},...]
    * @ type GET
    * @ url /recharge/query/{customId}
    * */
    @RequestMapping(value = "/recharge/query/{customId}", method = RequestMethod.GET)
    @ResponseBody
    public String queryRecharge(@PathVariable Integer customId, @RequestParam String token){
        return null;
    }

    /*
    * @ method queryRecharges
    * @ param token
    * @ return [{recharge record},...]
    * @ type GET
    * @ url /recharge/query
    * */
    @RequestMapping(value = "/recharge/query", method = RequestMethod.GET)
    @ResponseBody
    public String queryRecharges(@RequestParam String token)throws JsonProcessingException{
        if(!adminService.findAdminByPassword(token))
            return RS_FALSE;
        //查找所有充值信息
        return vipRechargeService.findAllRechargeWithPrimInfo();

    }

    /*
    * @ method addConsume
    * @ param token type amount remark
    * @ return boolean
    * @ type PUT
    * @ url /consume/add/{customId}
    * */
    public String addConsume(@PathVariable Integer customId, @RequestParam String token
        , @RequestParam String type, @RequestParam Double amount, @RequestParam String remark){
        return null;
    }

    /*
    * @ method queryConsume
    * @ param token
    * @ return [{consume record},...]
    * @ type GET
    * @ url /consume/query/{customId}
    * */
    @RequestMapping(value = "/consume/query/{customId}", method = RequestMethod.GET)
    @ResponseBody
    public String queryConsume(@PathVariable Integer customId, @RequestParam String token){
        return null;
    }

    /*
    * @ method queryConsumes
    * @ param token
    * @ return [{consume record},...]
    * @ type GET
    * @ url /consume/query
    * */
    @RequestMapping(value = "/consume/query", method = RequestMethod.GET)
    @ResponseBody
    public String queryConsumes(@RequestParam String token)throws JsonProcessingException{
        //先判断用户的合法性
        if(!adminService.findAdminByPassword(token))
            return RS_FALSE;
        //查询所有的消费信息
        return vipConsumeService.findAllRecordWithPrimInfo();
    }
    /*
    * @ method modifyConsume
    * @ param type amount
    * @ return boolean
    * @ type POST
    * @ url /consume/modify/{customId}
    * */
    @RequestMapping(value = "/consume/modify/{customId}", method = RequestMethod.POST)
    @ResponseBody
    public String modifyConsume(@PathVariable Integer customId, @RequestParam String type
            , @RequestParam Double amount){
        return null;
    }

    /*
    * @ method addConsume
    * @ param customname paywhat amount mark token
    * @ return boolean
    * @ type POST
    * @ url /consume/add
    * */
    @RequestMapping(value = "/consume/add", method = RequestMethod.POST)
    @ResponseBody
    public String addConsume(@RequestParam String customname
            , @RequestParam Double amount,@RequestParam String paywhat,@RequestParam String mark
            , @RequestParam String token){
        //身份验证
        if(!adminService.findAdminByPassword(token))
            return RS_FALSE;
        if(vipService.getVipByName(customname) == null)
            return RS_FALSE;
        //增加消费记录
        Record record = new Record();
        PrimInfo primInfo = vipService.getVipByName(customname);
        record.setfCustomid(primInfo.getCustomid());
        record.setAmount(amount);
        record.setPaytime(new Date());
        record.setPaywhat(paywhat);
        record.setRemark(mark);
        if(vipConsumeService.addOneConsume(record)){
            return RS_TRUE;
        }else{
            return RS_FALSE;
        }
    }


    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public VipService getVipService() {
        return vipService;
    }

    public void setVipService(VipService vipService) {
        this.vipService = vipService;
    }

    public VipBalanceService getVipBalanceService() {
        return vipBalanceService;
    }

    public void setVipBalanceService(VipBalanceService vipBalanceService) {
        this.vipBalanceService = vipBalanceService;
    }

    public VipRechargeService getVipRechargeService() {
        return vipRechargeService;
    }

    public void setVipRechargeService(VipRechargeService vipRechargeService) {
        this.vipRechargeService = vipRechargeService;
    }
}

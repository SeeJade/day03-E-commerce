package com.pinyougou.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Relation;
import java.util.List;

@RequestMapping("/brand")
//@Controller
//组合注解@Controller @ResponseBody 会对该类的所有方法生效
@RestController
public class BrandController {

    //从注册查询brandService对应的地址的代理对象
    @Reference
    private BrandService brandService;

    /**
     * 根据分页信息分页查询品牌列表
     * @param page 页号
     * @param rows 页大小
     * @return 分页结果对象
     */
    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value="page", defaultValue = "1") Integer page,
                               @RequestParam(value="rows", defaultValue = "10")Integer rows){
        return brandService.findPage(page, rows);
    }

    /**
     * 根据分页信息分页查询品牌列表
     * @param page 页号
     * @param rows 页大小
     * @return 品牌列表
     */
    @GetMapping("/testPage")
    public List<TbBrand> testPage(@RequestParam(value="page", defaultValue = "1") Integer page,
                                  @RequestParam(value="rows", defaultValue = "10")Integer rows){
        //return brandService.testPage(page, rows);
        return (List<TbBrand>) brandService.findPage(page, rows).getRows();
    }

    /**
     * 获取品牌列表
     * @return 品牌列表
     */
//    @ResponseBody
//    @RequestMapping(value="/findAll", method = RequestMethod.GET)
    @GetMapping("/findAll")
    public List<TbBrand> findAll(){
        //return brandService.queryAll();
        return brandService.findAll();
    }


    @GetMapping("/findOne")
    public TbBrand findOne(Long id){
        TbBrand one = brandService.findOne(id);
        return one;
    }

    @PostMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand ){
        try{
            brandService.update(tbBrand);
            return Result.ok("修改成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.fail("修改失败");
    }

    @PostMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand ){
        try{
            brandService.add(tbBrand);
            return Result.ok("添加成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.fail("添加失败");
    }

    @GetMapping("/delete")
    public Result delete(Long[] ids){
        try{
            brandService.deleteByIds(ids);
            return Result.ok("删除成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand,
                            @RequestParam(value = "page" ,defaultValue="1") Integer page,
                             @RequestParam(value="rows" ,defaultValue="10") Integer rows
                             ){
        PageResult search = brandService.search(page, rows, tbBrand);

        return search;
    }
}

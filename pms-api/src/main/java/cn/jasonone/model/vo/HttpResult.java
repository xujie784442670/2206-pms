package cn.jasonone.model.vo;

import cn.hutool.core.lang.Assert;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xujie
 * @version 1.0.0
 */
@Data
@Accessors(chain = true)
public class HttpResult {
    private String msg;
    private int code;
    private Object data;


    public HttpResult createMap(){
        Assert.isNull(data,"data必须为null,才能创建Map类型的data");
        this.data = new HashMap<>();
        return this;
    }

    public HttpResult put(String key,Object value){
        if(this.data == null){
            createMap();
        }
        Assert.isInstanceOf(Map.class,data,"data必须为Map类型,才能添加数据");
        ((HashMap) data).put(key,value);
        return this;
    }


    public static HttpResult ok(){
        return ok("OK");
    }

    public static HttpResult ok(Object data){
        return ok("OK",data);
    }

    public static HttpResult ok(String msg){
        return new HttpResult().setCode(200).setMsg(msg);
    }

    public static HttpResult ok(String msg, Object data){
        return new HttpResult().setCode(200).setMsg(msg).setData(data);
    }

    public static HttpResult error(int code,String msg){
        return new HttpResult().setCode(code).setMsg(msg);
    }


}

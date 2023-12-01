package com.iotplatform.backend.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test2/")
public class Test2Controller {

    @RequestMapping("getinfo/")
    public Map<String, String> getBotInfo3() {
        Map<String, String> res = new HashMap<>();
        res.put("school", "山东科技大学");
        res.put("class", "物联网云平台");
        return res;
    }

}

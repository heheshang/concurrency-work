package com.wangwenjun.concurrency.second.concurrent.chapter18

import java.util.concurrent.TimeUnit

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody, RestController}

/**
  * Created by ssk on 2019/1/8 0008.
  * desc:
  */
@RestController
@RequestMapping(Array("api"))
class ApiController {


  @RequestMapping(value = Array("hello"), method = Array(RequestMethod.GET))
  @ResponseBody
  def hello(): Message = {

    TimeUnit.SECONDS.sleep(6)

    val message = new Message();

    message.value = "hello "

    message
  }
}

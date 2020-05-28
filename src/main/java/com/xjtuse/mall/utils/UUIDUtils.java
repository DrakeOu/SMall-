package com.xjtuse.mall.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author 失了秩
 * @date 2020/3/14 19:53
 * @description
 */
public class UUIDUtils implements Serializable {
   public static String getUUID() {
      return UUID.randomUUID().toString().replace("-","");
   }
}

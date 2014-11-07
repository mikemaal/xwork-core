package com.opensymphony.xwork2.ognl;

import com.opensymphony.xwork2.util.reflection.ReflectionContextFactory;
import ognl.Ognl;
import java.util.Map;
import java.io.Serializable;

public class OgnlReflectionContextFactory implements ReflectionContextFactory, Serializable {

   public Map createDefaultContext(Object root) {
      return Ognl.createDefaultContext(root);
   }
}

package com.opensymphony.xwork2.util.reflection;

import com.opensymphony.xwork2.ActionContext;
import java.io.Serializable;

public class ReflectionProviderFactory implements Serializable {

   public static ReflectionProvider getInstance() {
      return ActionContext.getContext().getContainer().getInstance(ReflectionProvider.class);
   }
}

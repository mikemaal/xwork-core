package com.opensymphony.xwork2.factory;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.inject.Inject;
import java.util.Map;
import java.io.Serializable;

/**
 * Default implementation
 */
public class DefaultActionFactory implements ActionFactory, Serializable {

   private ObjectFactory objectFactory;

   @Inject
   public void setObjectFactory(ObjectFactory objectFactory) {
      this.objectFactory = objectFactory;
   }

   public Object buildAction(String actionName, String namespace, ActionConfig config, Map<String, Object> extraContext)
         throws Exception {
      return objectFactory.buildBean(config.getClassName(), extraContext);
   }
}
